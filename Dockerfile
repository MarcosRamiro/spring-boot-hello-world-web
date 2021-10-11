FROM amazoncorretto:11-alpine as build

WORKDIR /workspace/app

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src

RUN ./mvnw install -DskipTests

FROM amazoncorretto:11-alpine

ARG DEPENDENCY=/workspace/app/target

WORKDIR /app

COPY google.cer /usr/local/share/ca-certificates/google.crt

RUN apk --no-cache update && \
    apk add ca-certificates && \
    update-ca-certificates && \
    rm -rf /var/cache/apk/*

COPY --from=build ${DEPENDENCY}/*.jar app.jar

COPY entrypoint.sh entrypoint.sh

EXPOSE 8080

ENTRYPOINT ["/app/entrypoint.sh"]
CMD ["ola mundo"]