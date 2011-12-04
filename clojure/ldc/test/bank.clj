(ns ldc.test.bank
	(:use clojure.contrib.test-is)
	(:use ldc.bank))

(defstruct account :name :value)

(deftest bank-deposit-10
  (def ac1 (ref (struct account "Christophe" 1000)))
  (bank-deposit ac1 10)
  (is (= 1010 (:value @ac1))))

(deftest bank-deposit-10-withdraw-10
  (def ac1 (ref (struct account "Christophe" 1000)))
  (bank-deposit ac1 10)
  (bank-withdraw ac1 10)
  (is (= (struct account "Christophe" 1000) @ac1)))

(deftest test-bank-assessment
  (def ac1 (struct account "Christophe" 1000))
  (is (= "Account: Christophe Amount: 1000" (bank-assessment ac1))))

(deftest test-bank-transfert
  (def ac1 (ref (struct account "Christophe" 1000)))
  (def ac2 (ref (struct account "Bill" 110000)))
  (bank-transfert ac2 ac1 10000)
  (is (= (struct account "Christophe" 11000) @ac1))
  (is (= (struct account "Bill" 100000) @ac2)))
