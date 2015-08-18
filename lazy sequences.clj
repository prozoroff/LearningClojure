(defn random-ints
;;Возвращает ленивую последовательность случайных целых чисел в диапазоне О:limit
[limit]
(lazy-seq
(println "realizing random number")
(cons (rand-int limit) 
(random-ints limit))))  

(def rands (take 10 (random-ints 50)))

;;(first rands) 
;;(count rands)
;;(nth rands 3) 