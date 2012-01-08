@Grab(group='org.codehaus.gpars', module='gpars', version='0.12')
import groovyx.gpars.GParsPool
import static java.lang.Math.sqrt;

def bench(funcName, f){
	def start = System.nanoTime()
	f
	def total = System.nanoTime() - start
	println "Time spent for ${funcName}: " + total
}
GParsPool.withPool{
	def withMapReduce = {(1 .. 1000).parallel.filter{it % 2 == 0}.map{Math.sqrt(it)}.collection}
	def withParalleleCollections = {(1 .. 1000).findAllParallel{it % 2 == 0}.collectParallel{Math.sqrt(it)}}

	2.times{
		bench("Map Reduce", withMapReduce)
		bench("Parallel collections", withParalleleCollections)
	}
}


