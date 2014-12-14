(ns clj.ascii
  (require [clojure.math.numeric-tower :as math])
  (:import (java.awt.image BufferedImage)
           (java.awt Color)
           (javax.imageio ImageIO)
           (java.net URL))
  (:use hiccup.core))

(def ascii-chars [\# \A \@ \% \$ \+ \= \* \: \, \. \space])

(defn scale-image [uri new-width]
  (let  [image (ImageIO/read (URL. uri))
         [width height] [(.getWidth image) (.getHeight image)]
         new-height (* (/ new-width width) height)
         scaled-image (BufferedImage. new-width new-height BufferedImage/TYPE_INT_RGB)
         gfx2d (doto (.createGraphics scaled-image)
                 (.drawImage image 0 0 new-width new-height nil)
                 .dispose)]
    scaled-image))

(defn ascii [img x y color?]
  (let [{:keys [red green blue]} (bean (Color. (.getRGB img x y)))
        peak (max red green blue)
        ascii-count (dec (count ascii-chars))
        idx (math/round (* ascii-count (/ peak 255)))
        output (nth ascii-chars idx)]
    (if color?
      (html [:span {:style (format "color: rgb(%s, %s, %s);" red green blue)} output])
      output)))

(defn convert-image [uri w color?]
  (let [raw-image (scale-image uri w)
        ascii-image (->> (for [y (range (.getHeight raw-image))
                               x (range (.getWidth raw-image))]
                           (ascii raw-image x y color?))
                         (partition w))
        output (->> ascii-image
                    (interpose (if color? "<br/>" \newline))
                    flatten)]
    (if color?
      (html [:pre {:style "font-size: 5pt; letter-spacing: 1px;
                          line-height: 4pt; font-weight: bold"}
             output])
      (println output))))
