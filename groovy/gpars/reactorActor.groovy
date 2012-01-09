import groovyx.gpars.group.DefaultPGroup
import groovyx.gpars.actor.Actor
import groovyx.gpars.actor.Actors

final def group = new DefaultPGroup()
final def messageHandler = group.reactor { msg ->
	if(msg instanceof String) return msg + " " + msg
	if(msg instanceof Integer) return 2 * msg
	return "Send me something I understand"
}

group.actor{
	println 'Get my message twice: ' + messageHandler.sendAndWait("Hey ")
}

group.actor{
	println 'Get the double of 10: ' + messageHandler.sendAndWait(10)
}

println "Get results asyncronously ;)"

def end = {-> println "What happend if I send shit to this service?? : " + messageHandler.sendAndWait([]); messageHandler.stop()}
group.actor{
	(1 .. 10).each{messageHandler << it}
	loop(10, end){
		react(1000){ message -> 
			println 'Received then double of something: ' + message	
		}
	}
}

messageHandler.join()


