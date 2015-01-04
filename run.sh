#!/bin/sh

args=$*

java $JAVA_ARGS -classpath "bin/:." MarkovGen $args
