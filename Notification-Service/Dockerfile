FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app

COPY target/Notification-Service-1.0.0-RELEASE.jar app.jar

EXPOSE 8089

ENTRYPOINT ["java", "-jar", "app.jar"]