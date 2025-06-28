FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app

COPY target/User-Service-1.0.0-RELEASE.jar app.jar

EXPOSE 9082

ENTRYPOINT ["java", "-jar", "app.jar"]