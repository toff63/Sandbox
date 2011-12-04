(use 'team.core)
(init!)
;; nil
(add-qualities!  "organization" "discipline")
;; nil
(add-team! "john" "bob" "fred" "mathias")
;; nil
(get-all-team-members)
;; ({:name "mathias"} {:name "fred"} {:name "bob"} {:name "john"})
(add-quality-to! "mathias" "organization" )
;; nil
(add-quality-to! "fred" "organization" "good" )
;; nil
(get-all-rel "mathias")
;;(
;; "mathias :hasQuality organization {:level \"bad\"}"
;; "mathias :teammate fred"
;; "mathias :teammate bob"
;; "mathias :teammate john"
;; "fred :teammate mathias"
;; "bob :teammate mathias"
;; "john :teammate mathias"
;; "organization :isQualityOf mathias {:level \"bad\"}"
;; " :member mathias")
(get-all-rel "fred")
;;(
;; "fred :hasQuality organization {:level \"good\"}"
;; "fred :teammate mathias"
;; "fred :teammate bob"
;; "fred :teammate john"
;; "mathias :teammate fred"
;; "bob :teammate fred"
;; "john :teammate fred"
;; "organization :isQualityOf fred {:level \"good\"}"
;; " :member fred") 
(get-team-member-having-quality "organization")
;; ("fred" "mathias")
