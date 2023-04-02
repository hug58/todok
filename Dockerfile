
FROM openjdk:17-jdk-slim

VOLUME /tmp
RUN mkdir /app

COPY . /app

WORKDIR /app

RUN  chmod +x gradlew
RUN ./gradlew build
RUN mv build/libs/*.jar /app.jar


ENTRYPOINT ["java", "-jar", "app.jar"]