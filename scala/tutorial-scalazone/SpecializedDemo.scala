import scala.specialized

class SpecializedDemo {
	def invokeReduce[@specialized T](x: Array[T], fn: (T, T) => T)  = 
		x reduceLeft { fn(_,_)}
}

object SpecializedMain {
	def main(args: Array[String]) = {
		val specializedDemo = new SpecializedDemo
		
		println(specializedDemo.invokeReduce(
			Array(4, 5, 6, 7, 8),
			(x: Int, y: Int) => x + y ))

		println(specializedDemo.invokeReduce(
			Array(1.0, 2.0, 3.0, 4.0, 5.0),
			(x: Double, y:Double) => x * y)
		)
	}
}
