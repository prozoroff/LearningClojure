;;Наиболее интересные примеры по мере прочтения "Clojure Programming" Chas Emerick
;;===============================================================================

;;Нахождение среднего значения массива чисел

(defn average
	[numbers]
		(/ (apply + numbers) (count numbers)))

;;Развернутая форма этой функции

(def average (fn average
	[numbers]
		(/ (apply + numbers) (count numbers))))

;;Перевод CamelCase -> camel-case

(require '[clojure.string :as str])  
	(def camel->keyword (comp keyword
		str/join
			(partial interpose \-)
			(partial map str/lower-case)
			#(str/split % #"(?<=[a-z])(?=[A-Z])"))) 

;;То же с помощью макросов
(defn camel->keyword
	[s]
	(->> (str/split s #"(?<=[a-z])(?=[A-Z])")
		(map str/lower-case)
		(interpose \-)
		str/join
		keyword))

;; Ассоциативный массив из последовательности пар ключ-значение
;;(camel-pairs->map ["CamelCase" 5 "lowerCamelCase" 3])
;;= {:camel-case 5, :lower-camel-case 3}

(def camel-pairs->map (comp (partial apply hash-map)
							(partial map-indexed (fn [i x]
													(if (odd? i)
													x
													(camel->keyword x))))))

;;Проестейший REPL интерпретатор Clojure

(defn embedded-repl
"A naive Clojure REPL implementation. Enter ':quit'
to exit."
	[]
	(print (str (ns-name *ns*) "»> "))
	(flush)
	(let [expr (read)
		value (eval expr)]
		(when (not= :quit value)
		(println value)
		(recur))))

;;Функции высших порядков
;;============================================================================

;;Классика: adder

(defn adder
	[n]
	(fn [x] (+ n x)))

;;Функция, удваивающая значение переданной ей функции
;;(def double+ (doubler +))
;;(double+ 3 4)
;;=14

(defn doubler
	[f]
	(fn [& args]
	(* 2 (apply f args))))

;;Количество фоловеров в Twitter

(require 'clojure.xml)
(defn twitter-followers
	[username]
	(->> (str "https://api.twitter.com/1.1/users/show.xml?screen_name="
		username)
		clojure.xml/parse
		:content
		(filter (comp #{:followers_count} :tag))
		first
		:content
		first
		Integer/parseInt))

;;Мемоизация

(defn prime?
	[n]
	(cond
		(== 1 n) false
		(== 2 n) true
		(even? n) false
		:else (-» (range 3 (inc (Math/sqrt n)) 2)
		(filter #(zero? (rem n %)))
		empty?)))

(time (prime? 12348))

(let [m-prime? (memoize prime?)]
	(time (m-prime? 12348))
	(time (m-prime? 12348)))

;;"Elapsed time: 2085.029 msecs" 
;;"Elapsed time: 0.042 msecs"



