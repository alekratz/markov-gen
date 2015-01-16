#!/bin/sh

args=$*

java $JAVA_ARGS -classpath "bin/:lib/:." MarkovGen $args
