#!/bin/sh
cd clientGUI
javac *.java 2> ../error.txt
java -Djava.security.manager  -Djava.security.policy=client.policy Client 2> ../error.txt
