import static groovyx.gpars.dataflow.Dataflow.task
import groovyx.gpars.dataflow.DataflowVariable

final def a = new DataflowVariable()
final def b = new DataflowVariable()

task {
	println a.val
	b << 'Hi there'
}

task {
	println b.val
	a << 'Hi there'
}.join()

println "This message will never be printed because the dead lock will ALLWAYS happen. In java this message may be printed."
