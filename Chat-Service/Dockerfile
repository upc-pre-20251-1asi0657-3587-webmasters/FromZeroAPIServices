FROM amazoncorretto:21-alpine-jdk

WORKDIR /app

COPY target/Chat-Service-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 9087

ENTRYPOINT ["java", "-jar", "app.jar"]