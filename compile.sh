#!/bin/bash

SRC_DIR="src/main/java"
OUT_DIR="out"
JAR_FILE="uniquehands.jar"
MAIN_CLASS="edu.canisius.csc213.project1.UniqueHands"

echo "Cleaning last build"
rm -rf $OUT_DIR $JAR_FILE

echo "Compiling Java files"
mkdir -p $OUT_DIR
javac -d $OUT_DIR $(find $SRC_DIR -name "*.java")

echo "Creating JAR file."
jar cfe $JAR_FILE $MAIN_CLASS -C $OUT_DIR .

echo "Build complete. Run with java -jar $JAR_FILE"