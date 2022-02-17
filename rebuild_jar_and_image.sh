mvn clean install -DskipTests
docker rmi pm
docker build ./  -t pm
