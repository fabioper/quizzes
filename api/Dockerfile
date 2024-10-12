FROM maven:3.8.6-openjdk-18-slim AS build
WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src

EXPOSE 8080

CMD ["mvn", "spring-boot:run"]
