#!/bin/bash
#build the jar file
mvn clean install
#build the docker container
docker-compose up