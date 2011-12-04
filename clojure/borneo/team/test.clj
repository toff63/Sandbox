(use 'team.core)
(init!)
;; nil
(add-qualities! "code" "test" "methodology" "coaching" "sharedDecision" "organization" "discipline")
;; nil
(add-team! "Christophe" "Diego" "Alexandre" "Jeferson" "Jackson" "José" "Andre")
;; nil
(get-all-team-members)
;; ({:name "Andre"} {:name "José"} {:name "Jackson"} {:name "Jeferson"} {:name "Alexandre"} {:name "Diego"} {:name "Christophe"})
(add-quality-to! "Alexandre" "organization" )
;; nil
(add-quality-to! "Diego" "organization" "good" )
;; nil
(get-all-rel "Alexandre")
;;(
;;  "Alexandre :hasQuality organization {:level \"bad\"}" 
;;  "organization :isQualityOf Alexandre {:level \"bad\"}" 
;;  "Alexandre :teammate Andre" 
;;  "Alexandre :teammate José"
;;  "Alexandre :teammate Jackson" 
;;  "Alexandre :teammate Jeferson" 
;;  "Alexandre :teammate Diego" 
;;  "Alexandre :teammate Christophe" 
;;  "Andre :teammate Alexandre" 
;;  "José :teammate Alexandre" 
;;  "Jackson :teammate Alexandre" 
;;  "Jeferson :teammate Alexandre" 
;;  "Diego :teammate Alexandre" 
;;  "Christophe :teammate Alexandre" 
;;  " :member Alexandre")
(get-all-rel "Diego")
;;(
;;  "Diego :hasQuality organization {:level \"good\"}" 
;;  "organization :isQualityOf Diego {:level \"good\"}" 
;;  "Diego :teammate Andre" 
;;  "Diego :teammate José" 
;;  "Diego :teammate Jackson" 
;;  "Diego :teammate Jeferson" 
;;  "Diego :teammate Alexandre" 
;;  "Diego :teammate Christophe" 
;;  "Andre :teammate Diego" 
;;  "José :teammate Diego" 
;;  "Jackson :teammate Diego" 
;;  "Jeferson :teammate Diego" 
;;  "Alexandre :teammate Diego" 
;;  "Christophe :teammate Diego" 
;;  " :member Diego")
(get-team-member-having-quality "organization")
;; ("Diego" "Alexandre")
