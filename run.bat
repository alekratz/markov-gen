@echo off

rem Honestly, I don't feel like figuring out how to deal with command-line environment variables with Windows
java -classpath "bin\;lib\;." MarkovGen %*
