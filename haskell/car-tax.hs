import Test.HUnit

data Car = Car { owner :: String,
							   speed :: Int,
								 carType :: String,
								 licensePlate :: String
							 } deriving (Show, Eq)

data TaxedCar = TaxedCar {car :: Car,
											    tax :: Int
                         } deriving (Show, Eq)

carTax :: [Car] -> [TaxedCar]
carTax cars = [TaxedCar c (calculateTax c) | c <- cars]

calculateTax :: Car -> Int
calculateTax car
					| s < 100 = 0
					| otherwise = 10 + 20 * (100 - s)
	        where s = speed car


carNoTax1 = Car "Bob" 50  "Byke" "none"
expectedCarNoTax1 = TaxedCar carNoTax1 0
carNoTax2 = Car "Bob" 99  "Renault" "abcd"
expectedCarNoTax2 = TaxedCar carNoTax2 0
carWithTax = Car "Bob" 100 "Renault" "abcd"
expectedCarWithTax = TaxedCar carWithTax 10

testNoTax = TestCase (assertEqual "No tax for speed below 100" [expectedCarNoTax1, expectedCarNoTax2] (carTax [carNoTax1,carNoTax2]))
testWithTax = TestCase (assertEqual "10 Tax for speed equal 100" [expectedCarWithTax] (carTax [carWithTax]))

tests = TestList [TestLabel "No tax" testNoTax , TestLabel "10 Tax" testWithTax]
