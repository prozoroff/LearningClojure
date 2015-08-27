"http://iloveponies.github.io/120-hour-epic-sax-marathon/p-p-p-pokerface.html"

"poker hands for testing"
(def high-seven                   ["2H" "3S" "4C" "5C" "7D"])
(def pair-hand                    ["2H" "2S" "4C" "5C" "7D"])
(def two-pairs-hand               ["2H" "2S" "4C" "4D" "7D"])
(def three-of-a-kind-hand         ["2H" "2S" "2C" "4D" "7D"])
(def four-of-a-kind-hand          ["2H" "2S" "2C" "2D" "7D"])
(def straight-hand                ["2H" "3S" "6C" "5D" "4D"])
(def low-ace-straight-hand        ["2H" "3S" "4C" "5D" "AD"])
(def high-ace-straight-hand       ["TH" "AS" "QC" "KD" "JD"])
(def flush-hand                   ["2H" "4H" "5H" "9H" "7H"])
(def full-house-hand              ["2H" "5D" "2D" "2C" "5S"])
(def straight-flush-hand          ["2H" "3H" "6H" "5H" "4H"])
(def low-ace-straight-flush-hand  ["2D" "3D" "4D" "5D" "AD"])
(def high-ace-straight-flush-hand ["TS" "AS" "QS" "KS" "JS"])

(def rank-replacements {\T 10, \J 11, \Q 12, \K 13, \A 14})

(defn suit 
	"returns the suit of the card 
	as a one character string"
	[card] 
	(let [[r s] card] (str s)))


(defn rank 
	"returns the rank as a number  
	between 2 and 14"
	[card] 
	(let [[r s] card] 
		( if (Character/isDigit r) (Integer/valueOf (str r)) (rank-replacements r))))

(defn num-of-a-kind? 
	"that returns true if the hand 
	contains a assigned num of a kind."
	[cards num]
	(.contains (into [] (vals (frequencies (map rank cards)))) num))

(defn pair? 
	"returns true if there is a pair in hand 
	and false if there is no pair in hand."
	[cards] 
	(num-of-a-kind? cards 2))

(defn three-of-a-kind?
	"that returns true if the hand 
	contains a three of a kind."
	[cards] 
	(num-of-a-kind? cards 3))

(defn four-of-a-kind?
	"that returns true if the hand 
	contains a four of a kind."
	[cards] 
	(num-of-a-kind? cards 4))