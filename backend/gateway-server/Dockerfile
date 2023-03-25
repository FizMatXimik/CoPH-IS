FROM openjdk:11-slim

LABEL maintainer="Aleksandr Gaplikov <cool.gaplikov2014@yandex.ru>"

COPY target/gateway-server-2.7.8.jar /app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]