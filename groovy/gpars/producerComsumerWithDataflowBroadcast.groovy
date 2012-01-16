import static groovyx.gpars.dataflow.Dataflow.task
import groovyx.gpars.dataflow.DataflowQueue
import groovyx.gpars.dataflow.DataflowBroadcast
import groovyx.gpars.dataflow.DataflowReadChannel
import groovyx.gpars.dataflow.DataflowWriteChannel
import java.util.concurrent.CyclicBarrier

DataflowWriteChannel broadcastStream = new DataflowBroadcast()
DataflowReadChannel upperCaseStream = broadcastStream.createReadChannel()
DataflowReadChannel lowerCaseStream = broadcastStream.createReadChannel()
DataflowReadChannel reverseStream = broadcastStream.createReadChannel()
final def output = new DataflowQueue()

def databaseResult = ['This', 'is', 'a', 'sunny', 'day.']
final def barrier = new CyclicBarrier(4)

task{
	for(data in databaseResult){
		broadcastStream << data
	}
}

task {
	for( int i = databaseResult.size(); i > 0; i--)
		output << "From upper case: " + upperCaseStream.val.toUpperCase()
	barrier.await()
}

task {
	for( int i = databaseResult.size(); i > 0; i--)
		output << "From lower case: " + lowerCaseStream.val.toLowerCase()
	barrier.await()
}
task {
	for( int i = databaseResult.size(); i > 0; i--)
		output << "From reverse: " + reverseStream.val.reverse()
	barrier.await()
}
barrier.await()
output.each {println "Output contains: ${it}" }


