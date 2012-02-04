import Test.HUnit

fizzbuzz :: Num a => a -> [a]
fizzbuzz 0 = [0]
fizzbuzz x = (fizzbuzz (x -1 )) ++ [x]

test0 = TestCase (assertEqual "0 should return 0" [0] (fizzbuzz 0))
test1 = TestCase (assertEqual "1 should return [0, 1]" [0, 1] (fizzbuzz 1))

tests = TestList [TestLabel "0" test0,
									TestLabel "1" test1]
