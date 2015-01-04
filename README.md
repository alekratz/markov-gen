markov-gen
=
This is a markov chain generator project written in Java as practice for writing them as MapReduce jobs for a hadoop cluster. You are welcome to mess around with the code as much as you please - this is really just a small side project.

License
=
BSD 3-clause license. You are free to do whatever you want with it - I am not responsible for what you decide to do with the source.

Text licenses
=
Although am a little fuzzy on the details of the licenses, I have chosen to distribute some of the texts that I used during testing of this software. All texts included are public domain, and no longer hold their copyrights. These are located in the text/ directory.

Using
=
I use Eclipse when writing the software, but you can directly compile this using

`javac -classpath lib/*.jar:.:bin/ src/*.java -d bin/`

I've included a nice script for you to use if you so please, to run the project. `./run.sh` will do the trick. I feel like usage instructions for the software itself are fairly obvious after you run it, so I won't detail it here.
