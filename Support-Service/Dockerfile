FROM amazoncorretto:21-alpine-jdk
WORKDIR /app

COPY target/Support-Service-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 9080

ENTRYPOINT ["java", "-jar", "app.jar"]