FROM gradle:7.5.1-jdk17 AS builder
WORKDIR /usr/app/
COPY . .
# running tests when building gradle must be disabled, because it'll try to conntect to the database
# via docker container name (postgres:5432/db-name) which isn't possible yet at this stage
RUN gradle build -x test

FROM openjdk:17-jdk-slim
WORKDIR /usr/app/
# TODO figure out how to set .jar name in build.gradle without breaking the jar
COPY --from=builder /usr/app/build/libs/taste-test-viewer-0.0.1-SNAPSHOT.jar /usr/app/taste-test-viewer.jar
EXPOSE 8080
# TODO figure out how to properly set spring.active.profiles
CMD ["java", "-jar", "/usr/app/taste-test-viewer.jar"]