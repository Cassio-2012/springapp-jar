FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY database-connection-0.0.1-SNAPSHOT.jar app.jar
COPY keystore.p12 keystore.p12
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app.jar"]
