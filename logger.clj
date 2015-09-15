;;Наиболее интересные примеры по мере прочтения "Clojure Programming" Chas Emerick 
;;=============================================================================== 

;;Аналог println, выводящий текст в stdout\
;;(def out (print-logger *out*))  
;;(out "hello")

(defn print-logger
[writer]
#(binding [*out* writer]
(println %)))  

;;Вывод в файл
;;(def log->file (file-logger "messages.log"))
;;(log->file "hello")

(require 'clojure.java.io)

(defn file-logger
	[file] 
	#(with-open [f (clojure.java.io/writer file :append true)] 
	((print-logger f) %)))

;;Функция для логирования в несколько мест
;;(log "hello again”)

(defn multi-logger
	[& logger-fns] 
	#(doseq [f logger-fns] 
	(f %)))

(def log (multi-logger 
	(print-logger *out*)
	(file-logger "messages.log")))

;;Добавление времени в лог
;;(log-timestamped "goodbye, now")

(defn timestamped-logger
	[logger]
	#(logger (format "[%1$tY-%1$tm-%1$te %1$tH:%1$tM:%1$tS] %2$s"
	(java.util.Date.) %)))

(def log-timestamped (timestamped-logger
	(multi-logger
	(print-logger *out*)
	(file-logger "messages.log"))))
