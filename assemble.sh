#!/bin/bash
mvn clean install -DskipTests
docker image build -t albo-marvel-image .  
