IMAGE_NAME=fizmatximik/execute-service
VERSION=1.0.0

docker rmi $IMAGE_NAME:$VERSION

docker build -t $IMAGE_NAME:$VERSION .

docker image ls | grep $IMAGE_NAME

docker push $IMAGE_NAME:$VERSION