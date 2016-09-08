#!/bin/bash

javac -Xlint:unchecked -cp .:../External_Libraries/gson-2.3.1.jar display/*.java
javac -Xlint:unchecked -cp .:../External_Libraries/gson-2.3.1.jar controller/*.java
javac -Xlint:unchecked -cp .:../External_Libraries/gson-2.3.1.jar data/*.java
javac -Xlint:unchecked Searcher.java