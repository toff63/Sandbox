(defun fact (n)
  (loop for i from 2 to n
        for fact = 1 then (* fact i)
        finally (return fact)))

(format t "Factorial of 6 is ~A~%" (fact 6))
