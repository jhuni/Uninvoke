(load-file "uninvoke.clj")

(defn line
  "This returns a line of the form y = mx + b."
  [m b]

  (bijection.
   (fn [x]
     (+ (* m x) b))
   (fn [y]
     (- (/ y m) (/ b m)))))

(defn compose-lines
  "This describes the group of lines."
  ([] (line 1 0))
  ([a] a)
  ([[m1 b1] [m2 b2]]
     (line (* m1 m2) (+ (* m1 b2) b1)))
  ([a b & more]
     (reduce compose-lines (compose-lines a b) more)))

(def inc* (line 1 1))
(def add #(line 1 %))
(def mul #(line % 0))




