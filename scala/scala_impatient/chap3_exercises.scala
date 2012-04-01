1.
import collection.mutable.ArrayBuffer
import util.Random

def createRandomArray(n:Int):ArrayBuffer[Int] = {
  val a = new ArrayBuffer[Int]()
  for(i <- 0 until n){
    a += Random.nextInt(n)
  }
  return a;
}

2.
import collection.mutable.ArrayBuffer

def swapAdjacent(a:Array[Int]): Array[Int] = {
  val b = new ArrayBuffer[Int]();
  b ++= a
  for(i <- 0 until b.size if i % 2 == 0 ){
    if(i+1 < a.size){
     b.insert(i, b(i+1))
     b.remove(i+2,1)
    } 
  }
  return b.toArray;
}

3.

def immutableSwapAdjacent(a:Array[Int]):Array[Int] = {
    val b = for(elem <- a)  yield elem
    return swapAdjacent(b)
}

4.
def seperatePositiveAndNegative(a:Array[Int]):Array[Int] = {
    import collection.mutable.ArrayBuffer
    val c = new ArrayBuffer[Int]()
    val b = for (elem <-a if elem > 0) yield elem
    c ++= b
    for(elem <- a if elem <= 0) c += elem
    return c.toArray
}

5.
def average(a:Array[Double]):Double = a.sum / a.size

6.
Array(1,2,3,4,5,6,7,8,9,10).reverse
val a = new ArrayBuffer[Int]()
a ++= Array(1,2,3,4,5,6,7,8,9,10)
a.reverse
