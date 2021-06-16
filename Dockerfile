FROM openjdk:8-jdk-alpine
MAINTAINER "Cesar Colli"
WORKDIR /app

COPY ./target/*.jar ./app.jar
ENTRYPOINT ["java", "-jar", "/app/app.jar"]

EXPOSE 8080