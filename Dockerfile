FROM openjdk:17-jdk
WORKDIR /app
COPY target/watchlist-service.jar /app/watchlist-service.jar
ENTRYPOINT ["java", "-jar", "watchlist-service.jar"]
