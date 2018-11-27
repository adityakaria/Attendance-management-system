#!/bin/bash

cd srv/files
./test.bash $1
cd ../..
javac *.java
java Combine