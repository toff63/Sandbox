@Grab(group='org.codehaus.gpars', module='gpars', version='0.12')
import groovyx.gpars.GParsPool
import groovyx.gpars.actor.Actors

def passiveActor = Actors.actor{
	loop {
		react {
			msg -> println "received: " + msg
		}
	}
}
def echoActor = Actors.actor{
	loop {
		react {
			msg -> println "received: " + msg
			reply msg
		}
	}
}

passiveActor.send "Message 1"
passiveActor << "Message 2"
passiveActor "Message 3"
println "Waiting 1 seconds to let the actor process the messages"
Thread.sleep(1000)

println "Synchronous call"
def reply = echoActor.sendAndWait('Message 4')
println "Received reply: " + reply


