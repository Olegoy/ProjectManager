FROM openjdk:11

COPY ./target/ProjectManager-0.1.0-SNAPSHOT.jar /opt/ProjectManager/app.jar

ENTRYPOINT ["java","-Xmx1024m","-jar","/opt/ProjectManager/app.jar"]