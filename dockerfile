FROM openjdk:17-jdk-slim

WORKDIR /app
COPY . /app


RUN ./gradlew build
EXPOSE 8080
CMD ["java", "-jar", "/app/build/libs/todo-0.0.1-SNAPSHOT.jar"]