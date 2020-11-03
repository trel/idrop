.PHONY : default theapp clean

SHELL = /bin/bash

default: theapp

# this docker command uses the host user's .m2 directory
# to cache the maven artifacts for repeated builds
#
theapp:
	docker run -it --rm \
		-v "$$PWD":/usr/src \
		-v "$$HOME/.m2":/root/.m2 \
		-w /usr/src \
		maven:3-jdk-11 \
		mvn package -Dmaven.test.skip=true

# it does not clean maven's .m2 cache
clean:
	docker run -it --rm \
		-v "$$PWD":/usr/src \
		-v "$$HOME/.m2":/root/.m2 \
		-w /usr/src \
		maven:3-jdk-11 \
		mvn clean
