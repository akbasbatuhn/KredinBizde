FROM openjdk:17-jdk-slim
RUN apt-get -y update; apt-get -y install curl
MAINTAINER batuhanakbas
COPY target/kredinbizde-gw-0.0.1-SNAPSHOT.jar kredinbizde-gw-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "kredinbizde-gw-0.0.1-SNAPSHOT.jar"]