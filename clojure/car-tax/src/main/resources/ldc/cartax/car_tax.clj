(ns ldc.cartax.car-tax)


(defn car-tax [cars]
 	(letfn [(compute-speed
					 [speed]
					 (if (> 100 speed) 
						0 
						(+ 10 (* 20 (- speed 100)))))]
	 (map 
    #(assoc % :tax 
            (compute-speed (:speed %))) 
    cars)))

