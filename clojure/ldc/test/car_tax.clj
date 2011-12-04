(ns ldc.test.car_tax
	(:use clojure.contrib.test-is)
	(:use ldc.car_tax))

(defstruct car :owner :speed :type :license-plate)
(defstruct taxed_car :owner :speed :type :license-plate :tax)

(deftest test-0-car
 (is (= [] (car-tax []))))

(deftest test-1-car-speed-100
 (def my_car (struct car "Christophe" 100 "Ferrari" "0123456"))
 (def my_taxed_car (car-tax [my_car]))
 (def expected_car [(struct taxed_car "Christophe" 100 "Ferrari" "0123456" 10 )])
 (is (= expected_car my_taxed_car)))


(deftest test-2-cars-speed-110
 (def my_car (struct car "Christophe" 110 "Ferrari" "0123456"))
 (def my_taxed_car (car-tax [my_car my_car]))
 (def expected_car (struct taxed_car "Christophe" 110 "Ferrari" "0123456" 210 ))
 (is (= [expected_car expected_car] my_taxed_car)))

(deftest test-2-cars-speed-10-110
 (def my_car_10 (struct car "Christophe" 10 "Ferrari" "0123456"))
 (def my_car_110 (struct car "Christophe" 110 "Ferrari" "0123456"))
 (def my_taxed_car (car-tax [my_car_10 my_car_110]))
 (def expected_car_10 (struct taxed_car "Christophe" 10 "Ferrari" "0123456" 0 ))
 (def expected_car_110 (struct taxed_car "Christophe" 110 "Ferrari" "0123456" 210 ))
 (is (= [expected_car_10 expected_car_110] my_taxed_car)))
