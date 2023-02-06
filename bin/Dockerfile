# For Java 11
FROM adoptopenjdk/openjdk11:alpine-jre

#changed the working directory to /opt/app
WORKDIR /opt/app

ARG DEPENDENCY=target/dependency

ARG JAR_FILE=target/DeGuzmanStuffAnywhere-0.0.1-SNAPSHOT.jar

COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]

