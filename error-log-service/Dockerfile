FROM openjdk:17-jdk-slim
RUN apt-get -y update; apt-get -y install curl
MAINTAINER batuhanakbas
COPY target/error-log-service-0.0.1-SNAPSHOT.jar error-log-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "error-log-service-0.0.1-SNAPSHOT.jar"]