#!/bin/bash
echo "Build"
mvn clean compile
echo "RUN JUnit Tests"
mvn clean test
