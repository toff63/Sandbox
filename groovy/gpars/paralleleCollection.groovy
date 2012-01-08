@Grab(group='org.codehaus.gpars', module='gpars', version='0.12')
import groovyx.gpars.GParsPool

GParsPool.withPool {
	def animals = ['dog', 'ant', 'cat', 'whale']
	println(animals.anyParallel {it ==~ /ant/} ? 'Found an ant' : 'No ants found')
	println(animals.everyParallel {it.contains('a')} ? 'All animal cotains a' : 'Some animal doesn\'t have a')
}
