**Hello World - Spring WebMvc**

**Tecnologias**

- Java 11
- Docker


**Docker-Compose**

docker-compose build

docker-compose up



**Docker**

docker build -t ramimar/web .

docker run -p 8080:8080 --env-file ./env.list  ramimar/web

docker run -p 8080:8080 -e "JAVA_OPTS=-Ddebug" ramimar/web

docker run --entrypoint /bin/sh -p 8080:8080 -it --env-file ./env.list  ramimar/web

docker exec -it 51a9284ea755 /bin/bash


**Wiremock**


java -jar .\wiremock-jre8-standalone-2.31.0.jar --port=9999 --print-all-network-traffic --verbose



