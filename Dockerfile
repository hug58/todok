FROM openjdk:17-jdk-slim

WORKDIR /app
COPY . /app

RUN  chmod +x gradlew
RUN ./gradlew build

COPY build/libs/*.jar app.jar

EXPOSE 8080
CMD ["java", "-jar", "app.jar"]