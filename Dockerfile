# Stage 1: Build the application
FROM eclipse-temurin:17-jdk as build
WORKDIR /app
COPY . .
RUN ./mvnw clean package -DskipTests

# Stage 2: Package the application
FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY --from=build /app/target/tracking-number-0.0.1-SNAPSHOT.jar app.jar

# Expose the port your app runs on (default 8080)
EXPOSE 8080

# Run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]
