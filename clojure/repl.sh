#!/bin/bash

CLOJURE_DIR=/home/toff/bin/clojure-1.3.0
CLOJURE_JAR=$CLOJURE_DIR/clojure-1.3.0.jar
CONTRIB_JAR=$CLOJURE_DIR/clojure-contrib-1.2.0/target/clojure-contrib-1.2.0.jar
JLINE_JAR=$CLOJURE_DIR/jline-1.0/jline-1.0.jar
LDC=/home/toff/dev/Sandbox/clojure/ldc

if [ "$1" == "" ]; then
 java -cp .:$JLINE_JAR:$CLOJURE_JAR:$CONTRIB_JAR:$LDC jline.ConsoleRunner clojure.main
else
 java -cp .:$CLOJURE_JAR:$CONTRIB_JAR clojure.main "$1"
fi

