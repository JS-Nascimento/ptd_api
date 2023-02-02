FROM maven:3.8.1-jdk-11 as build
WORKDIR /build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:11 as run
WORKDIR /app
COPY --from=build ./build/target/*.jar ./ptd_apl.jar
EXPOSE 8080

ENTRYPOINT java -jar ptd_apl.jar