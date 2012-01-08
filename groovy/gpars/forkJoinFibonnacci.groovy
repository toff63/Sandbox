@Grab(group='org.codehaus.gpars', module='gpars', version='0.12')

import groovyx.gpars.GParsPool
import static groovyx.gpars.GParsPool.runForkJoin
import static groovyx.gpars.GParsPool.withPool

def bench(f){
	def start = System.nanoTime()
	def res = f
	def time = System.nanoTime() - start
	res + "executed in " + time + " nano seconds"
}

withPool{
	Closure fibonnacci = { n ->
		runForkJoin(n) { i ->
			if(i < 2) return 1
			forkOffChild(i-1)
			forkOffChild(i-2)
			childrenResults.sum()
		}
	}
	Closure memoizedFibo = fibonnacci.gmemoize()

	(0 .. 20).each{println it + ": " +  bench( fibonnacci(it))}
	(0 .. 20).each{println it + ": " +  bench (memoizedFibo(it))}
}

