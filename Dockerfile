FROM amazoncorretto:11-alpine

ARG JAR_FILE=target/*.jar

RUN mkdir /app

WORKDIR /app

COPY ${JAR_FILE} app.jar

COPY entrypoint.sh entrypoint.sh

EXPOSE 8080

ENTRYPOINT ["/app/entrypoint.sh"]
CMD ["ola mundo padrao"]