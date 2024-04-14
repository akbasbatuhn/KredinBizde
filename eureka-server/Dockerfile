FROM openjdk:17-jdk-slim
RUN apt-get -y update; apt-get -y install curl
MAINTAINER batuhanakbas
COPY target/eureka-server-0.0.1-SNAPSHOT.jar eureka-server-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "eureka-server-0.0.1-SNAPSHOT.jar"]