FROM amazoncorretto:21-alpine-jdk

WORKDIR /app

ADD target/project-service.jar app.jar

EXPOSE 8083

ENTRYPOINT ["java", "-jar", "app.jar"]