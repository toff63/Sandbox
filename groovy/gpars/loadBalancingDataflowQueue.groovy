import static groovyx.gpars.dataflow.Dataflow.task
import groovyx.gpars.dataflow.DataflowQueue
import java.util.concurrent.CyclicBarrier

final def buffer = new DataflowQueue()
final def output = new DataflowQueue()
final def barrier = new CyclicBarrier(2)

def databaseResult = ['a', 'b', 'c', 'd', 'e', 'f']

final def start = System.nanoTime()
def dataProcess = {data -> data.toUpperCase()}

task{
	for(data in databaseResult){
		buffer << data
	}
}

task {
	while(true)
		output << "Process by wk1: " + dataProcess(buffer.val)
}
task {
	while(true)
		 output << "Process by wk2: " + dataProcess(buffer.val)
}
task {
	while(true)
		output << "Process by wk3: " + dataProcess(buffer.val)
}

task {
	while(true)
		println output.val
	barrier.await()
}

barrier.await()
