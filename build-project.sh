#!/bin/bash
echo "Init"
mvn dependency:resolve
echo "Build"
mvn clean compile
echo "RUN JUnit Tests"
mvn clean test
