#!/bin/bash

# This script ensure redis is alive in a specific maching using the default
# port 6379

# This script should be invoked passing the server host name: 
# checkRedis.sh localhost

# Get time as a UNIX timestamp (seconds elapsed since Jan 1, 1970 0:00 UTC)
START="$(date +%s)"
expect<<EOF
set timeout 10
spawn telnet $1 6379
send "PING\r"
expect "PONG"

send "SET mykey myvalue\r"
expect "+OK"

send "GET mykey\r"
expect "$7"
expect "myvalue"

send "DEL mykey\r"
expect ":1"

exit
EOF

TIME_SPENT="$(($(date +%s)-START))"

if [ ${TIME_SPENT} -gt  9 ]
then
	echo "FAIL! REDIS isn't alive! FAIL!"
fi
