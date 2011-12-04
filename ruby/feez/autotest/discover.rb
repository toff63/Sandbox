require File.dirname(__FILE__) + '/testunit'

puts "Adding discovery"
Autotest.add_discovery { "testunit" }
