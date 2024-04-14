# Use OpenJDK 11 image from Docker Hub
FROM openjdk:21-jdk

# Set the working directory in the container
WORKDIR /app

# Copy the packaged jar file into the container
COPY target/airport-spring-boot-project-0.0.1-SNAPSHOT.jar /app

# Expose the port your application runs on
EXPOSE 8090

# Command to run your application
CMD ["java", "-jar", "airport-spring-boot-project-0.0.1-SNAPSHOT.jar"]