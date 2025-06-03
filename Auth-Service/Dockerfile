FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app

COPY target/Auth-Service-1.0.0-RELEASE.jar app.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "app.jar"]