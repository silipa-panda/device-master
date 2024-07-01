FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/device-master-0.0.1-SNAPSHOT.jar device-master-api.jar
ENTRYPOINT ["java", "-jar", "device-master-api.jar"]