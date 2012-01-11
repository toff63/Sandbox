import static groovyx.gpars.dataflow.Dataflow.task
import groovyx.gpars.dataflow.Dataflows

final def df = new Dataflows()

// sumReduce(1, infinity) = Pi*Pi/6
def sumReduce = {start, end ->
	(start .. end).inject(0, {acc, val -> acc + 1/(val * val)})
}

task {
	df.z = Math.sqrt((df.y + df.x) * 6)
}

task {
	df.y = sumReduce(1,10000)
}

task {
	df.x = sumReduce(10001,20000)
}

println "Pi is more or less: ${df.z}"
