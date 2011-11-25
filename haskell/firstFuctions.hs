doubleMe x = x + x
doubleUs x y = doubleMe x  + doubleMe y 

doubleSmallNumbers x = if x < 100 then  doubleMe x else x

-- else is mandatory
-- if is an expression like 1 + 1 so we can write that
doubleSmallNumbers' x = (if x < 100 then doubleMe x else x) + 1
