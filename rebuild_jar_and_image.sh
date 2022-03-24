mvn clean install -DskipTests
docker-compose down
docker rmi project-manager2
docker build ./  -t project-manager2

#git update-index --chmod=+x rebuild_jar_and_image.sh
#./rebuild_jar_and_image.sh