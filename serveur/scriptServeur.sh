#!/bin/sh
cd groupChatServer
javac *.java 2> ../error.txt
java -Djava.security.manager  -Djava.security.policy=serveur.policy -Djava.rmi.server.codebase=file:${pwd}/ Serveur 2> ../error.txt
