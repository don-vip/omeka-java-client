#!/bin/sh
mvn install -Dmaven.test.skip=true && cd omeka-java-client-app && mvn spring-boot:run -Dspring-boot.run.profiles=fr-uca -Dspring-boot.run.arguments=45

