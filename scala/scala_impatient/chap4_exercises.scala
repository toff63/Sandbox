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

5.
def wordFrequencyTreeMap(file:String){
    import collection.JavaConversions.mapAsScalaMap

    val in = new java.util.Scanner(new java.io.File(file))
    val freq : collection.mutable.Map[String,Int] = new java.util.TreeMap[String,Int]
    while(in.hasNext()){
        val word = in.next()
        val f = 1 + freq.getOrElse(word,0)
        freq += (word -> f)
    }  
    print(freq)
}

6.
val days = collection.mutable.LinkedHashMap("Monday" -> java.util.Calendar.MONDAY,
                                              "Tuesday" ->java.util.Calendar.TUESDAY,
                                              "Wednesday" ->java.util.Calendar.WEDNESDAY,
                                              "Thursday" ->java.util.Calendar.THURSDAY,
                                              "Friday" ->java.util.Calendar.FRIDAY,
                                              "Saturday" ->java.util.Calendar.SATURDAY,
                                              "Sunday" ->java.util.Calendar.SUNDAY)

for((day, calendarDay) <- days) print(day + " is " + calendarDay + "\n")

7.
def printSystemProperties() {
  import collection.JavaConversions.propertiesAsScalaMap

  val props: collection.Map[String,String] = System.getProperties()
  val maxKeyLength = props.keySet.map(_ length).max
  for((key,value) <- props)  print(key + " " * (maxKeyLength - key.length + 1) + "| " + value + "\n")
}

8.
def minmax(values: Array[Int]) : Tuple2[Int,Int] = (values.min , values.max)

9.
def lteqgt(values: Array[Int], v:Int) : Tuple3[Int,Int,Int] = (values.filter( _ < v).length, values.filter(_ == v).length, values.filter(_ > v).length)
