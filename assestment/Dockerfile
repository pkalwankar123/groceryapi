# Use the official OpenJDK base image

FROM maven:3.8.3-openjdk-8 AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package
EXPOSE 8081
ENTRYPOINT ["java","-jar","/home/app/target/grocery-0.0.1-SNAPSHOT.jar"]
