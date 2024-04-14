FROM openjdk:17-jdk-slim
RUN apt-get -y update; apt-get -y install curl
MAINTAINER batuhanakbas
COPY target/bank-service-0.0.1-SNAPSHOT.jar bank-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "bank-service-0.0.1-SNAPSHOT.jar"]