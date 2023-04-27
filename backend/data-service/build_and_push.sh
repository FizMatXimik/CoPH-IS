IMAGE_NAME=fizmatximik/data-service
VERSION=1.0.0

./mvnw clean install package -DskipTests=true

docker rmi $IMAGE_NAME:$VERSION

docker build -t $IMAGE_NAME:$VERSION .

docker image ls | grep $IMAGE_NAME

#docker push $IMAGE_NAME:$VERSION