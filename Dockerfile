FROM openjdk:17-jdk
WORKDIR /app
COPY target/watchlist-service-0.0.1-SNAPSHOT.jar /app/watchlist-service.jar
ENTRYPOINT ["java", "-jar", "watchlist-service.jar"]
