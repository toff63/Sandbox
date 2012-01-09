import groovyx.gpars.actor.*

final Actor myActor = new DynamicDispatchActor().become{
	when {String msg -> println 'Received string: ' + msg; reply 'Thanks'}
	when {Double msg -> println 'Received double: ' + msg; reply 'Thanks'}
	when {msg -> println 'Received this thing: ' + msg; reply 'What was that?'; stop()}
}

myActor.start()
Actors.actor{
	myActor 'Hello'
	myActor 1.0d
	myActor 10 as BigDecimal
	myActor.join()
}.join()
