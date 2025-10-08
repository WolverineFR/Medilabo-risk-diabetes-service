FROM eclipse-temurin:17-jdk
VOLUME /tmp
COPY target/risk-diabetes-service-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]