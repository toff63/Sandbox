(defn calc [op val1 val2]
 (cond (= op "+") (+ val1 val2)
			 (= op "-") (- val1 val2)
			 (= op "/") (/ val1 val2)
			 (= op "*") (* val1 val2)
	)
 )

(println (calc "+" 4 5)) ;; Retorno deve ser: 9
(println (calc "-" 5 1)) ;; Retorno deve ser: 4
(println (calc "*" 2 3)) ;; Retorno deve ser: 6
(println (calc "/" 27 3)) ;; Retorno deve ser: 9
