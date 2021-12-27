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

docker exec -it 51a9284ea755 /bin/bash


**Wiremock**


java -jar .\wiremock-jre8-standalone-2.31.0.jar --port=9999 --print-all-network-traffic --verbose



**Apache Kafka**

*kafka-topics*

kafka-topics --topic first_topic --create --partitions 3 --replication-factor 1 --bootstrap-server kafka-1:9092

kafka-topics --bootstrap-server kafka-1:9092 --list

kafka-topics --bootstrap-server kafka-1:9092 --topic first_topic --describe

kafka-topics --bootstrap-server kafka-1:9092 --topic first_topic --delete


*kafka-console-producer*

kafka-console-producer --bootstrap-server kafka-1:9092 --topic first_topic

kafka-console-producer --bootstrap-server kafka-1:9092 --topic first_topic --request-required-acks all


*kafka-console-consumer *

kafka-console-consumer --bootstrap-server kafka-1:9092 --topic first_topic --from-beginning --group my-new-app

kafka-console-consumer --bootstrap-server kafka-1:9092 --topic first_topic --group my-new-app --from-beginning

kafka-console-consumer --bootstrap-server kafka-1:9092 --topic first_topic --partition 0 --from-beginning


*kafka-consumer-groups*

kafka-consumer-groups --bootstrap-server localhost:9092 --describe --group my-new-app

