FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app

COPY target/Notification-Service-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 9085

ENTRYPOINT ["java", "-jar", "app.jar"]