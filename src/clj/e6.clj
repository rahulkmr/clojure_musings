(ns clj.e6
  (:use [clojure.math.numeric-tower :only (expt)]))

(defn- sum-sqr
  [lower upper]
  (expt 
    (apply + 
           (range lower upper)) 2))

(defn- sqr-sum
  [lower upper]
  (apply +
         (map #(* % %) (range lower upper))))

(defn sum-sqr-diff
  [lower upper]
  (- (sum-sqr lower upper) (sqr-sum lower upper)))

(defn -main [& args]
  (sum-sqr-diff 1 20))
