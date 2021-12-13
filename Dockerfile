FROM openjdk:8-jdk-alpine
MAINTAINER atakanburakgungor
VOLUME /tmp
EXPOSE 8080
ADD target/document-management-0.0.1-SNAPSHOT.jar document-management.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/document-management.jar"]
