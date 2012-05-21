import subprocess
from datetime import datetime

from libcloud.storage.types import Provider, ContainerDoesNotExistError
from libcloud.storage.providers import get_driver

EC2_ACESS_ID = 'access_id'
EC2_SECRET_KEY = 'access_key'

driver = get_driver(Provider.S3_EU_WEST)
conn = driver(EC2_ACCESS_ID, EC2_SECRET_KEY)

directory = '/home/toff/log'
cmd = 'tar cvzpf - %s' % (directory)

object_name = 'backup-%s.tar.gz' % (datetime.now().strftime('%Y-%m-%d'))
container_name = 'libcloudtests'

# Create a container if it doesn't already exist
try:
	container = conn.get_container(container_name)
except ContainerDoesNotExistError:
	container = conn.create_container(container_name)

pipe = subprocess.Popen(cmd, bufsize=0, shell=True, stdout=subprocess.PIPE)
return_code = pipe.poll()

print 'Uploading object...'

while return_code is None:
# Compress data in our directory and stream it directly to CF
	obj = container.upload_object_via_stream(iterator=pipe.stdout,object_name=object_name)
	return_code = pipe.poll()

print 'Upload complete, transferred: %s KB' % ((obj.size / 1024))
