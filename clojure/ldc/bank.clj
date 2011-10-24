(ns ldc.bank)

(defn bank-deposit [account amount]
  (dosync (alter account assoc :value (+ amount (:value @account)))))

(defn bank-withdraw [account amount]
  (dosync (alter account assoc :value (- (:value @account) amount))))

(defn bank-transfert [accountFrom accountTo amount]
  (dosync
    (alter accountFrom assoc :value (- (:value @accountFrom) amount))
    (alter accountTo assoc :value (+ (:value @accountTo) amount))))

(defn bank-assessment [account]
  (format "Account: %s Amount: %d" (:name account) (:value account)))

