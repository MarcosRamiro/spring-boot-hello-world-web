**Hello World - Spring WebMvc**

**Tecnologias**

- Java 11
- Docker
- Kafka / Zookeeper



**Docker-Compose**

docker-compose build

docker-compose up



**Docker**

docker build -t ramimar/web .

docker run -p 8080:8080 --env-file ./env.list  ramimar/web

docker run -p 8080:8080 -e "JAVA_OPTS=-Ddebug" ramimar/web

docker run --entrypoint /bin/sh -p 8080:8080 -it --env-file ./env.list  ramimar/web

docker exec -it c188272ee33e /bin/bash


**Wiremock**


java -jar .\wiremock-jre8-standalone-2.31.0.jar --port=9999 --print-all-network-traffic --verbose



**Apache Kafka**

kafka-topics --topic first_topic --create --partitions 2 --replication-factor 1 --bootstrap-server kafka-1:9092
kafka-topics --topic second_topic --create --partitions 2 --replication-factor 1 --bootstrap-server kafka-1:9092

kafka-topics --bootstrap-server kafka-1:9092 --list

kafka-topics --bootstrap-server kafka-1:9092 --topic first_topic --describe

kafka-topics --bootstrap-server kafka-1:9092 --topic second_topic --delete

---

kafka-console-producer --bootstrap-server localhost:9092 --topic first_topic

kafka-console-producer --bootstrap-server kafka-1:9092 --topic first_topic --request-required-acks all

--

kafka-console-consumer --bootstrap-server kafka-1:9092 --topic first_topic

kafka-console-consumer --bootstrap-server kafka-1:9092 --topic first_topic --from-beginning
