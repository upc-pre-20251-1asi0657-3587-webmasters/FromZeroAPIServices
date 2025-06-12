FROM amazoncorretto:21-alpine-jdk

WORKDIR /app

ADD target/Candidates-Service.jar app.jar

EXPOSE 8085

ENTRYPOINT ["java", "-jar", "app.jar"]