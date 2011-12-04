myLast :: [a] -> a
myLast [c] = c
myLast (_:xs) = myLast xs

myButLast :: [a] -> a
myButLast c = (last (init c))

elementAt :: [a] -> Int -> a
elementAt c i = c !! (i - 1)

myLength :: [a] -> Int
myLength [c] = 1
myLength (_:xs) = 1 + myLength xs

myReverse :: [a] -> [a]
myReverse [] = []
myReverse [c] = [c]
myReverse (c:xs) = myReverse xs ++ [c]


isPalindrome :: (Eq a) => [a] -> Bool
isPalindrome [] = True
isPalindrome [c] = True
isPalindrome (a:xs) = (if a == (last xs) then True else False) &&
											(isPalindrome (init xs))

compress :: (Eq a) => [a] -> [a]
compress [c] = [c]
compress (a:b:xs) = (if a == b then [] else [a] ) ++ compress ([b] ++ xs)

