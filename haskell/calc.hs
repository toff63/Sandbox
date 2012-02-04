import Test.HUnit

calculator :: (a -> a -> a ) -> a -> a -> a
calculator op x y = (op x y)


testPlus = TestCase (assertEqual "Plus with Positive Numbers" 5 (calculator (+) 2 3))
test2Plus = TestCase (assertEqual "Plus with Negative Numbers" (-5) (calculator (+) (-2) (-3)))
testMinus = TestCase (assertEqual "Substraction" 3 (calculator (-) 5 2))
testMult = TestCase (assertEqual "Multiplication" 6 (calculator (*) 3 2))
testDiv = TestCase (assertEqual "Division" 3 (calculator (/) 6 2))
test2Div = TestCase (assertEqual "Division with rational" 0.5 (calculator (/) 1 2))

tests = TestList [TestLabel "Plus" testPlus,
			            TestLabel "Plus" test2Plus,
									TestLabel "Minus" testMinus,
									TestLabel "Mult" testMult,
									TestLabel "Div" testDiv,
									TestLabel "Div" test2Div]
