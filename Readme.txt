===============
Not Amazon Echo
===============

To build and run this project as a jar, run the commands:

mkdir bin
javac -sourcepath src/ -d bin/ src/not/amazon/echo/Main.java
jar cfm TeamHEcho.jar Manifest.txt -C bin/ .
java -jar TeamHEcho.jar

Or run the script with:

./build.sh

--------
Features
--------

This virtual Amazon Echo representation has a few basic features:

* An on/off button (the echo cannot be turned off when it is talking).
* Volume controls.
* Automatic speech detection.

Turn the echo on and try asking some simple questions such as:

* What is 2 + 2?
* What time is it?
* What is the melting point of silver?
* Does Santa Claus exist?
