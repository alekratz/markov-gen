#!/bin/sh

args=$*

java $JAVA_ARGS -classpath "bin/:lib/json-simple-1.1.1.jar:." MarkovGen $args
