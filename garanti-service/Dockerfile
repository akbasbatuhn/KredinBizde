FROM openjdk:17-jdk-slim
RUN apt-get -y update; apt-get -y install curl
MAINTAINER batuhanakbas
COPY target/garanti-service-0.0.1-SNAPSHOT.jar garanti-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "garanti-service-0.0.1-SNAPSHOT.jar"]