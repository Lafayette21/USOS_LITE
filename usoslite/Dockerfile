FROM maven:3.8.3-openjdk-17 AS builder

WORKDIR /app

# Copy the pom.xml file and download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy the project source code
COPY src ./src

# Build the application
RUN mvn clean package -DskipTests

# Stage 2: Create the final lightweight image
FROM openjdk:17-jdk-slim-buster

WORKDIR /app

# Copy the built JAR file from the builder stage
COPY --from=builder /app/target/*.jar app.jar

# Set the entry point to run the Spring Boot application
CMD ["java", "-jar", "app.jar"]
