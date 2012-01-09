@Grab(group='org.codehaus.gpars', module='gpars', version='0.12')
import groovyx.gpars.GParsPool
import groovyx.gpars.actor.Actors
import groovyx.gpars.group.DefaultPGroup

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

//Simple calculator
def group = new DefaultPGroup(1)

final def console = group.actor{
	loop{
		react{
			println "Result: " + it
		}
	}
}

final def compute = group.actor{
	loop{
	react{a ->
		react{b ->
			react{operator ->
				switch (operator){
					case "+": console.send(a+b); break;
					case "*": console.send(a*b); break;
					case "-": console.send(a-b); break;
					case "/": console.send(a/b); break;
				}
			}
		}
	}
	}
}

compute << 2
compute << 3
compute << "+"
compute << 5
compute << 10
compute << "/"
compute.stop()
compute.join()
group.shutdown()
