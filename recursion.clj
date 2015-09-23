(defn my-map [f a-seq]
   "Рекурсивная реализация функции map"
  (if (empty? a-seq)
    a-seq
    (cons (f (first a-seq))
          (my-map f (rest a-seq)))))

(defn my-filter [pred a-seq]
  "Рекурсивная реализация функции filter"
  (if (empty? a-seq)
    a-seq
    (if (pred (first a-seq))
    	(cons (first a-seq) (my-filter pred (rest a-seq)))
    	(my-filter pred (rest a-seq))
    )))

(defn only-numbers? [coll]
  "Проверка последовательности на нечисловые элементы"
  (cond
   (empty? coll)
     true                        
   (number? (first coll))
     (only-numbers? (rest coll)) 
   :else
     false))                    

(defn sequence-contains? [elem coll]
  "Поиск элемента в последовательности"
  (cond
   (empty? coll)
     false                        
   (= elem (first coll))
     true
   :else
     (sequence-contains? elem (rest coll))))                    

(defn recursive-factorial [n]
  "Linear recursion"
  (if (zero? n)
      1
      (* n (recursive-factorial (dec n)))))

(defn helper [acc n]
  (if (zero? n)
    acc
    (helper (* acc n) (dec n))))

(defn recur-factorial [number]
  "tail recursion"
  (let [helper (fn [acc n]
                 (if (zero? n)
                   acc
                   (recur (* acc n) (dec n))))]
    (helper 1 number)))

(defn power [base exp]
  (let [helper (fn [acc exp]
                 (if (zero? exp)
                   acc
                   (recur (* acc base) (dec exp))))]
    (helper 1 exp)))