# Use an official OpenJDK runtime as the base image
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the Maven wrapper and pom.xml first to leverage Docker cache
COPY mvnw .
COPY .mvn .mvn

# Copy the pom.xml file
COPY pom.xml .

# Download dependencies (cached unless pom.xml changes)
RUN ./mvnw dependency:go-offline

# Copy the rest of the application code
COPY src src

# Package the application (skip tests to speed up build)
RUN ./mvnw clean package -DskipTests

# Expose the port the app runs on (default Spring Boot port)
EXPOSE 8080

# Environment variables for database connection
ENV SPRING_DATASOURCE_URL=jdbc:mysql://mysql:3306/cms
ENV SPRING_DATASOURCE_USERNAME=root
ENV SPRING_DATASOURCE_PASSWORD=root
ENV JWT_SECRET=your-very-secure-secret-key-1234567890

# Run the application
CMD ["java", "-jar", "target/java-database-capstone-0.0.1-SNAPSHOT.jar"]
