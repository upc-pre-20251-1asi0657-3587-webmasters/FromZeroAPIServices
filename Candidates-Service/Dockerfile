FROM amazoncorretto:21-alpine-jdk

WORKDIR /app

ADD target/candidates-service.jar app.jar

EXPOSE 9086

ENTRYPOINT ["java", "-jar", "app.jar"]