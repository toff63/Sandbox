#!/bin/bash

expect<<EOF
set timeout 5
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
