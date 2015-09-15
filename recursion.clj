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


