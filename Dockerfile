FROM openjdk:11
#COPY ./src/main/java/com/pq/gtfs/ /opt
#WORKDIR /opt
#CMD java *

#CMD java com.pq.gtfs.DemoApplication
#java -jar demo-0sha.jar

FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} demo-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/demo-0.0.1-SNAPSHOT.jar"]
