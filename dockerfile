# Step 1: Use a base image with Java installed
FROM openjdk:17-jdk-slim

# Step 2: Set the working directory inside the container
WORKDIR /registrationservices

# Step 3: Copy the packaged .jar file into the container
COPY target/registrationservices-0.0.1-SNAPSHOT.jar app.jar

# Step 4: Expose the application port (default Spring Boot port is 8080)
EXPOSE 8080

# Step 5: Command to run the application
ENTRYPOINT ["java", "-jar", "/registrationservices/app.jar"]




