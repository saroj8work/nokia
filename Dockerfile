FROM maven:3.6-jdk-11-slim

USER root

WORKDIR /var/
ADD /target/nokia-0.0.1-SNAPSHOT.jar nokia-0.0.1-SNAPSHOT.jar
EXPOSE 8080

ENTRYPOINT ["java","-jar","nokia-0.0.1-SNAPSHOT.jar"]
