import scala.annotation.tailrec

class RecursionDemo {
	@tailrec
	final def fact(n: Int, acc: Long = 1): Long = {
		if(n < 1) acc else fact(n -1, n * acc)
	}

}

object RecursionMain {
	def main(args: Array[String]){
		val recursionDemo = new RecursionDemo
		recursionDemo.fact(10)
	}

}
