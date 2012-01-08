@Grab(group='org.codehaus.gpars', module='gpars', version='0.12')
import groovyx.gpars.GParsPool
import java.util.concurrent.Future

def double computePi(int i){
	double sum = 0;
	(1 .. i).each{sum += 1/(it * it)}
	Math.sqrt(6 * sum)
}

def double computePi(){
	computePi(100)
}

println "sync call: " + computePi(100)

GParsPool.withPool{
	Closure piComputation = {computePi()}
	Closure asyncPiComputation = piComputation.async()
	Future result = asyncPiComputation()
	println "the call is done in parallel"
	println result.get()
	println "New async call"
	println piComputation.callAsync().get()
}
