FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package

FROM openjdk:17-jdk-alpine
COPY --from=build /app/target/WeatherApplication-0.0.1-SNAPSHOT.jar /app.jar
COPY .env /app/.env
ENTRYPOINT ["java", "-jar", "/app.jar"]