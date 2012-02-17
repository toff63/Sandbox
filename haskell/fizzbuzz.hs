import Test.HUnit

fizzbuzz a = [fizzbuzzElement b | b <- [0 .. a]]

fizzbuzzElement x
  | x == 0 = show 0
	| isMultiple3 x &&  isMultiple5 x  = "FizzBuzz"
	| isMultiple3 x  = "Fizz"
	| isMultiple5 x  =   "Buzz"
	| otherwise  = show x

isMultiple a b = rem a b == 0

isMultiple3 a = isMultiple a 3
isMultiple5 a = isMultiple a 5

test0 = TestCase (assertEqual "0 should return 0" ["0"] (fizzbuzz 0))
test1 = TestCase (assertEqual "1 should return  [\"0\", \"1\"]" ["0", "1"] (fizzbuzz 1))
test3 = TestCase (assertEqual "3 should return  [\"0\", \"1\", \"2\", \"Fizz\"]" ["0", "1", "2", "Fizz"] (fizzbuzz 3))
test5 = TestCase (assertEqual "5 should return  [\"0\", \"1\", \"2\", \"Fizz\", \"4\", \"Buzz\"]" ["0", "1", "2", "Fizz", "4", "Buzz"] (fizzbuzz 5))
test15 = TestCase (assertEqual "15 failed  " ["0", "1", "2", "Fizz", "4", "Buzz", "Fizz", "7", "8", "Fizz", "Buzz", "11", "Fizz", "13", "14", "FizzBuzz"] (fizzbuzz 15))

tests = TestList [TestLabel "0" test0,
									TestLabel "1" test1,
									TestLabel "3" test3,
									TestLabel "5" test5,
									TestLabel "15" test15]
