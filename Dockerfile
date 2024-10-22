# First stage: Build the "app" Gradle subproject using shadowJar
FROM gradle:8.10.2-jdk17 AS build
# Set the working directory
WORKDIR /app
# Copy the Gradle project files
COPY . /app/

# Build the project using the shadowJar task
RUN gradle shadowJar

# Second stage: Create the final image
FROM amazoncorretto:17-alpine

# Set the working directory
WORKDIR /app
# Copy the resulting jar from the first stage
COPY --from=build /app/app/build/libs/app-all.jar /app/app-all.jar
COPY --from=build /app/codec-server/build/libs/codec-server-all.jar /app/codec-server-all.jar