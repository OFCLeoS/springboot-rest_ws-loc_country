# Used this build due to maven, as well as Java 17
FROM maven:3.10.2-eclipse-temurin-17 AS builder

# We will create a "program" folder and set it to current dir
WORKDIR /program

# Copying our program and maven file
COPY pom.xml .
COPY src ./src

# We run this inside the program dir
RUN mvn clean package


FROM gcr.io/distroless/java17-debian13 AS prod

WORKDIR /program

COPY --from=builder /program/target/*.jar program.jar

EXPOSE 8080

CMD ["program.jar"]