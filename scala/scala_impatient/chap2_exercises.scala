1.
def getSignum(a:Int) = if(a > 0) 1 else if (a < 0) -1 else 0

2. {} returns no value and its type is Unit

3. 
val y = 1
val x:Unit = y = 1

4. 
for( i <- 10.to(0,-1)) println(i)

5.
def countdown(n:Int) {for(i <- n.to(0,-1))print(i + " ")}

6.
def unicodeProdcut(s:String) = {
  var prod = 1;
  for(i <- s) prod *= i
  prod
}

7.
def unicodeProdcutEnhanced(s:String) = s.foldLeft(1)((a:Int,b:Char) => a*b)


8.
def product(s:String) = s.foldLeft(1)((a:Int,b:Char) => a*b)

9.
def recursiveProduct(s:String):Int = if("" == s) 1 else s.head * recursiveProduct(s.tail)

10.
def toPowerN(x:Int, n:Int):BigInt = {
    println("x: " + x + " n: " + n)
    if(n > 0 && n%2 == 0) BigInt(x).pow(n/2) * BigInt(x).pow(n/2)
    else if(n > 0 && n%2 == 1) x * toPowerN(x, n-1)
    else if(n == 0) 1
    else  1/toPowerN(x,-n)
}
