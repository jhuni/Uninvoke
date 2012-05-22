(defprotocol Uninvokable
  (uninvoke [this args]))

(defmacro definvokerecord
  "This is a necessary hack to deal with implementation limitations."
  [name slots & body]
  (let [invoke-fn (gensym)]
      `(defrecord ~name
	 [~invoke-fn ~@slots]
	 ~@body
	 clojure.lang.IFn
	 ~@(map (fn [n]
		  (let [args (for [i (range n)] (symbol (str "arg" i)))]
		    (if (empty? args)
		      `(~'invoke [this#]
				 (~invoke-fn))
		      `(~'invoke [this# ~@args]
				 (~invoke-fn ~@args)))))
		(range 21))
	 (~'applyTo [this# args#]
		    (apply ~invoke-fn args#)))))

(definvokerecord bijection
  [inv*]

  Uninvokable
  (uninvoke [this args] (apply inv* args)))

(defn inv
  "This takes a bijection from A to B and it returns its inverse from B to A."
  [reversible-fn]

  (bijection.
   (fn [& args]
     (uninvoke reversible-fn args))
   (fn [& args]
     (apply reversible-fn args))))


(def destructure-num
 (bijection.
  (fn [num]
    {:signed? (neg? num)
     :np (Math/floor (Math/abs num))
     :fp (- (Math/abs num) (Math/floor (Math/abs num)))})
  (fn [num]
    (* ({false 1, true -1} (:signed? num))
       (+ (:np num) :fp num)))))













