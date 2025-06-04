FROM openjdk:17-jdk-alpine

WORKDIR /app

ADD target/Deliverables-Service.jar app.jar

EXPOSE 8084

ENTRYPOINT ["java", "-jar", "app.jar"]