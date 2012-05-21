from libcloud.compute.types import Provider
from libcloud.compute.providers import get_driver
from libcloud.compute.deployment import ScriptDeployment

EC2_ACESS_ID = 'access_id'
EC2_SECRET_KEY = 'access_key'

conn = get_driver(Provider.EC2)(EC2_ACESS_ID, EC2_SECRET_KEY)

key_path='/media/home_backup/SparkleShare/toffHome'
key_name='US-EAST'

# a simple script to install puppet post boot, can be much more complicated.
script = ScriptDeployment("apt-get -y install puppet")

# Ubuntu 10.04
image_ec2 = [i for i in conn.list_images() if i.id =='ami-a29943cb' ][0]
size_ec2 = [s for s in conn.list_sizes() if s.id == 't1.micro'][0]

# deploy_node takes the same base keyword arguments as create_node.
node = conn.deploy_node(name='testLibcloud', image=image, size=size, deploy=script,
												ssh_username='ubuntu', ssh_key=key_path, ex_keyname=key_name)
