# # Use a base image with Java 17 installed
# FROM openjdk:17-alpine

# # Set the working directory in the container
# WORKDIR /jwtoauth2

# # Copy the Maven executable JAR file and the pom.xml file to the container
# COPY target/*.jar jwtoauth2.jar
# COPY pom.xml .

# # Expose the port that the Spring Boot application will run on
# EXPOSE 8080

# # Command to run the Spring Boot application when the container starts
# CMD ["java", "-jar", "jwtoauth2.jar"]

# Use a specific version of Java 17 Alpine
FROM openjdk:17

# Set the working directory in the container
WORKDIR /jwtoauth2

# Copy the Maven executable JAR file and the pom.xml file to the container
COPY target/*.jar jwtoauth2.jar
COPY pom.xml .

# Expose the port that the Spring Boot application will run on
EXPOSE 8080

# Create a non-root user and set permissions
RUN addgroup -S spring && adduser -S spring -G spring
RUN chown -R spring:spring /jwtoauth2

# Switch to the non-root user
USER spring:spring

# Command to run the Spring Boot application when the container starts
CMD ["java", "-jar", "jwtoauth2.jar"]
