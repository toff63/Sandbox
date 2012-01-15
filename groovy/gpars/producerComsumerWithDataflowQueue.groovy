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
	for( int i = databaseResult.size(); i > 0; i--)
		output << dataProcess(buffer.val)
	barrier.await()
}

barrier.await()
output.each {println "Output contains: ${it}" }

