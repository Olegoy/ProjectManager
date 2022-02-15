FROM openjdk:11

COPY ./target/ProjectManager-0.0.1-SNAPSHOT.jar /opt/ProjectManager/app.jar

ENTRYPOINT ["java","-jar","/opt/ProjectManager/app.jar"]