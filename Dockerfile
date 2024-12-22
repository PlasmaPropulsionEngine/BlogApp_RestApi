# Use the official OpenJDK 17 base image
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the jar file to the container
COPY target/*.jar app.jar

# Expose the port the app runs on
EXPOSE 9092

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
