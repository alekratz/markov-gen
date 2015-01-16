markov-gen
=
This is a markov chain generator project written in Java as practice for writing them as MapReduce jobs for a hadoop cluster. You are welcome to mess around with the code as much as you please - this is really just a small side project.

Installing
=

Using
=
Markov-gen depends on org.json.simple, a Java JSON library. This can be found [here](https://code.google.com/p/json-simple/downloads/detail?name=json-simple-1.1.1.jar&can=2&q=). Just make a directory called "lib" and put the jar in there.

I use Eclipse when writing the software, but you can directly compile this using

`javac -classpath lib/*.jar:.:bin/ src/*.java -d bin/`

I've included a nice script for you to use if you so please, to run the project. `./run.sh` will do the trick. I feel like usage instructions for the software itself are fairly obvious after you run it, so I won't detail it here.

If you're using a Windows box, I've included a 'run.bat' file. **As of this writing, I have not yet tested to confirm that it works. It *should* work, but any fixes would be appreciated.**

License
=
BSD 3-clause license. You are free to do whatever you want with it - I am not responsible for what you decide to do with the source.

Text licenses
=
Although am a little fuzzy on the details of the licenses, I have chosen to distribute some of the texts that I used during testing of this software. Most texts included are public domain, and no longer hold their copyrights. These are located in the text/ directory. Texts not under public domain are detailed below.

Other sources
=
As mentioned previously, *most* of the texts are public domain. The works of Lovecraft are included, however, there is some minor controversy surrounding the status of the copyright for some of his post-1923 works (including the Call of Cthlulhu). It's a long, legal mess, but I have decided to include them because Lovecraft was known to be very generous with his work, and encouraged people to derive and draw inspiration from his writing. I am not distributing his writing for entertainment purposes, and rather, for constructive, educational purposes. I do not own the works of H.P. Lovecraft, and I am not taking credit for it.
