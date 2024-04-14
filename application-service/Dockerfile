FROM openjdk:17-jdk-slim
RUN apt-get -y update; apt-get -y install curl
MAINTAINER batuhanakbas
COPY target/application-service-0.0.1-SNAPSHOT.jar application-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "application-service-0.0.1-SNAPSHOT.jar"]