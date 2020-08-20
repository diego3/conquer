FROM maven:3.5.2-jdk-8-alpine as MAVEN_BUILD

COPY pom.xml /build/
COPY src /build/src/
COPY src/main/resources/application-docker.properties /build/src/main/resources/application.properties

WORKDIR /build/

RUN mvn package -DskipTests

FROM openjdk:8-jre-alpine
WORKDIR /app
COPY --from=MAVEN_BUILD /build/target/conquer-0.0.1-SNAPSHOT.jar /app/conquer.jar

EXPOSE 8182

ENTRYPOINT ["java","-jar","/app/conquer.jar"]