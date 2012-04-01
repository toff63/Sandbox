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
  val b = a.toBuffer
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
val a = Array(1,2,3,4,5,6,7,8,9,10).toBuffer
a.reverse

7.
def distinct(a:Array[Int]):Array[Int] = {
  import collection.mutable.ArrayBuffer
  val b = new ArrayBuffer[Int]()
  b ++= a
  return b.distinct.toArray
}

8.
def removeAllNegativeExceptFirst(a:Array[Int]):Array[Int] = {
  import collection.mutable.ArrayBuffer
  val b = a.toBuffer
  val indexes = (for (i <- 0 until b.size if b(i) < 0) yield i).drop(1)
  for(i <- (0 until indexes.size).reverse ) b.remove(indexes(i))
  return b.toArray
}

9.
java.util.TimeZone.getAvailableIDs() filter {_ startsWith "America" } map {_ stripPrefix "America/"}

10.
import collection.mutable._
import collection.JavaConversions._
def playWithJavaInterop():Buffer[String]={
  import java.awt.datatransfer._
  val flavors = SystemFlavorMap.getDefaultFlavorMap().asInstanceOf[SystemFlavorMap]
  val res:Buffer[String] = flavors.getNativesForFlavor(DataFlavor.imageFlavor)
  res
}
