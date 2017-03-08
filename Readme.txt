===============
Not Amazon Echo
===============

To build and run this project as a jar, run the commands:

mkdir bin
javac -sourcepath src/ -d bin/ src/not/amazon/echo/Main.java
jar cfm TeamHEcho.jar Manifest.txt -C bin/ .
java -jar TeamHEcho.jar
