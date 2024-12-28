# Use the official OpenJDK as a parent image
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the Spring Boot application JAR file to the container
COPY target/parking-lot-0.0.1-SNAPSHOT.jar parking-slot.jar

# Expose the port that your application runs on
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "parking-slot.jar"]
