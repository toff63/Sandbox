from libcloud.compute.types import Provider
from libcloud.compute.providers import get_driver

EC2_ACESS_ID = 'access_id'
EC2_SECRET_KEY = 'access_key'

conn = get_driver(Provider.EC2)(EC2_ACESS_ID, EC2_SECRET_KEY)

node = conn.list_nodes()[1].destroy()
