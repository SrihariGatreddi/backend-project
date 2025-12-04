# ----------- 1) BUILD STAGE -----------
FROM maven:3.9.9-eclipse-temurin-17 AS build

WORKDIR /app

# Copy pom.xml and download dependencies (cache layer)
COPY pom.xml .
RUN mvn -q -e -B dependency:go-offline

# Copy source code
COPY src ./src

# Build the Spring Boot jar (skip tests for speed)
RUN mvn clean package -DskipTests



# ----------- 2) RUNTIME STAGE -----------
FROM eclipse-temurin:17-jre

WORKDIR /app

# Copy the jar from build stage into runtime image
COPY --from=build /app/target/backend-project-0.0.1-SNAPSHOT.jar app.jar
# If jar name is different, change it above

# Expose Spring Boot port
EXPOSE 8080

# Command to run the app
ENTRYPOINT ["java", "-jar", "app.jar"]
