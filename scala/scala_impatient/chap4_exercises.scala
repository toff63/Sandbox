1.
val gizmos=Map("SmartPhone" -> 500, "netbook" -> 600, "workstation" -> 1000)
for((gizmo, price) <- gizmos) yield (gizmo, price * 0.9)

2.
def wordFrequencyMutable(file:String) {
  val in = new java.util.Scanner(new java.io.File(file))
  val freq = collection.mutable.Map[String,Int]()
  while(in.hasNext()){
      val word = in.next()
      val f = 1 + freq.getOrElse(word,0)
      freq += (word -> f)
  }  
  print(freq)
}

3.
def wordFrequencyImmutable(file:String) {
  val in = new java.util.Scanner(new java.io.File(file))
  var freq = Map[String,Int]()
  while(in.hasNext()){
      val word = in.next()
      val f = 1 + freq.getOrElse(word,0)
      freq += (word -> f)
  }  
  print(freq)
}

4.
def wordFrequencySortedMap(file:String) {
  val in = new java.util.Scanner(new java.io.File(file))
  var freq = collection.immutable.SortedMap[String,Int]()
  while(in.hasNext()){
      val word = in.next()
      val f = 1 + freq.getOrElse(word,0)
      freq += (word -> f)
  }  
  print(freq)
}

