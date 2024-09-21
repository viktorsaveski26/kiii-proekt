FROM maven:3.8-openjdk-17-slim AS build

WORKDIR /app

COPY pom.xml .

RUN mvn dependency:resolve

COPY src /app/src

RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-alpine
COPY --from=build /app/target/quizapp-0.0.1-SNAPSHOT.jar /quizapp.jar

EXPOSE 8080


ENTRYPOINT ["java","-jar","/quizapp.jar"]
