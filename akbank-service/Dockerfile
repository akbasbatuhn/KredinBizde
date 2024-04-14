FROM openjdk:17-jdk-slim
RUN apt-get -y update; apt-get -y install curl
MAINTAINER batuhanakbas
COPY target/akbank-service-0.0.1-SNAPSHOT.jar akbank-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "akbank-service-0.0.1-SNAPSHOT.jar"]
