FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY target/drone-API.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
EXPOSE 8080