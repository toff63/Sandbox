import static groovyx.gpars.dataflow.Dataflow.task
import groovyx.gpars.dataflow.DataflowVariable

final def x = new DataflowVariable()
final def y = new DataflowVariable()
final def z = new DataflowVariable()

// sumReduce(1, infinity) = Pi*Pi/6
def sumReduce = {start, end ->
	(start .. end).inject(0, {acc, val -> acc + 1/(val * val)})
}

task {
	z << Math.sqrt((y.val + x.val) * 6)
}

task {
	y << sumReduce(1,10000)
}

task {
	x << sumReduce(10001,20000)
}

println "Pi is more or less: ${z.val}"
