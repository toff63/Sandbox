lostNumbers = [4, 5, 7, 12, 14]

-- strings are lists
helloWorld = "Hello" ++ " " ++ "World"


-- peformance note: adding something at the end of the list
-- make haskell go throug the whole list
slowInsert = [1, 2, 3, 4] ++ [5]

-- add something at the beginning of the list is instantaneous
quickInsert = 'A' : [' ', 'C', 'A', 'T']

-- index begin at 0
o = helloWorld !! 4


--basic list functions

head [1, 2, 3, 4] -- 1
tail [1, 2, 3, 4] -- [2, 3, 4]
last [1, 2, 3, 4] -- 4
init [1, 2, 3, 4] -- [1, 2, 3]

length [1, 2, 3, 4] -- 4
null [] -- true
null [1, 2] -- false
reverse [1, 2, 3, 4] -- [4, 3, 2, 1]
take 3 [1, 2, 3, 4, 5] -- [1, 2, 3]
drop 3 [1, 2, 3, 4, 5] -- [4, 5]
maximum [1, 2, 5, 4, 3] -- 5
minimum [5, 2, 1, 4, 3] -- 1
sum [1, 2, 3, 4] --10
product [1, 2, 3, 4] --24
4 `elem` [1, 2, 3, 4] -- True
8 `elem` [1, 2, 3, 4] -- False



-- Generate lists
[1..5] -- [1,2,3,4,5]
['a'..'d'] -- "abcd"
['d'..'a'] -- []
['d','c'..'a'] -- "dca"
[2,4..12] -- [2,4,6,8,10,12]
[1,4..9] -- [1,4,7]

-- infinite list
take 5 [1,2..] -- [1,2,3,4,5]
take 3 (cycle [1,2,3]) -- [1,2,3,1,2,3,1,2,3]
take 3 (repeat 4) -- [4,4,4]
replicate 3 4 -- [4,4,4]
