mvn clean install -DskipTests
docker rmi project-manager2
docker build ./  -t project-manager2
