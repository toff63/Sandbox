from libcloud.compute.types import Provider
from libcloud.compute.providers import get_driver

EC2_ACESS_ID = 'access_id'
EC2_SECRET_KEY = 'access_key'

print "Nodes in USA"
datacenterInfo(get_driver(Provider.EC2)(EC2_ACCESS_ID, EC2_SECRET_KEY))
print "Nodes in EU"
datacenterInfo(get_driver(Provider.EC2_EU)(EC2_ACCESS_ID, EC2_SECRET_KEY))

def datacenterInfo(conn):
	"Print information about a datacenter"
	nodes = conn.list_nodes()
	for node in nodes:
		print node

	print "Locations"
	locations = conn.list_locations()
	for location in locations:
		print location

