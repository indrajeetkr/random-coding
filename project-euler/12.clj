;The sequence of triangle numbers is generated by adding the natural numbers. So the 7th triangle number would be 1 + 2 + 3 + 4 + 5 + 6 + 7 = 28. The first ten terms would be:

;1, 3, 6, 10, 15, 21, 28, 36, 45, 55, ...

;Let us list the factors of the first seven triangle numbers:

; 1: 1
; 3: 1,3
; 6: 1,2,3,6
;10: 1,2,5,10
;15: 1,3,5,15
;21: 1,3,7,21
;28: 1,2,4,7,14,28
;We can see that 28 is the first triangle number to have over five divisors.

;What is the value of the first triangle number to have over five hundred divisors?

(defn factorize [n]
  (loop [i 2 num n acc []]
    (if (<= num 1) acc
	(if (zero? (rem num i))
	  (recur i (/ num i) (concat acc [i]))
	  (recur (inc i) num acc)))))

(memoize factorize)

(defn groups [data]
  (loop [acc [] group [(first data)] l (next data)]
    (if (empty? l) (concat acc [group])
	
	(if (== (first group) (first l))
	  (recur acc (concat group [(first l)]) (next l))
	  (recur (concat acc [group]) [(first l)] (next l))))))

(defn divisors [n]
  (apply * (map #(inc (count %1)) (groups (factorize n)))))

(memoize divisors)

(defn answer []
  (loop [i 0 n 0]
    (println n (divisors n))
    (if (>= (divisors n) 500) n
	(recur (inc i) (+ n i)))))

(println (divisors 28))

(println (divisors 2284102666))
;(println (answer))