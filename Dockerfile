
FROM openjdk:17-jdk-slim

VOLUME /tmp
RUN mkdir /app

COPY . /app

WORKDIR /app

RUN  chmod +x gradlew
RUN ./gradlew build
RUN mv build/libs/*.jar .

ENTRYPOINT ["java", "-jar", "todo-0.0.1-SNAPSHOT.jar"]