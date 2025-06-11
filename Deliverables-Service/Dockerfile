FROM amazoncorretto:21-alpine-jdk

WORKDIR /app

ADD target/Deliverables-Service.jar app.jar

EXPOSE 8084

ENTRYPOINT ["java", "-jar", "app.jar"]