# Galah Event Publisher

This service helps consumers to publish messages to any topic. We need to make sure that topic already exists then only message will be published.

## Getting Started

This project is using spring-boot so no need to install local application server like tomcat to run this project.

```
1. Clone git repository https://github.com/siva583/galah-event-consumer-cache.git
2. Import project as maven project
3. Run project using "mvn spring-boot:run" command or run GalahEventPublisherApplication.java class as Java Application.
4. Great, now you are ready to play with service. Type http://localhost:8090/swagger-ui.htmlin browser and start playing with service. 
```

### Prerequisites

Service is using Kafka as messaging system between UI and redis. So both should be installed in local machine and use below ports.

```
server.port=8090
kafka.bootstrapAddress=localhost:9092
```

##### Kafka Instructions:
```
1. Download and install Kafka.
2. Start zookeeper server using "kafka_2.11-1.0.0/bin/zookeeper-server-start.sh kafka_2.11-1.0.0/config/zookeeper.properties" command
3. Start kafka server using "kafka_2.11-1.0.0/bin/kafka-server-start.sh kafka_2.11-1.0.0/config/server.properties" command
4. Create needed topics using these commands
	kafka_2.11-1.0.0/bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic USER_CREATE
	kafka_2.11-1.0.0/bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic USER_UPDATE
	kafka_2.11-1.0.0/bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic USER_DELETE
	kafka_2.11-1.0.0/bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic USER_FOLLOW_REQUEST
	kafka_2.11-1.0.0/bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic USER_FOLLOW_ACCEPT
	kafka_2.11-1.0.0/bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic USER_UNFOLLOW
	kafka_2.11-1.0.0/bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic POST_CREATE
	kafka_2.11-1.0.0/bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic POST_UPDATE
	kafka_2.11-1.0.0/bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic POST_DELETE
5. Create a consumer using below command
	kafka_2.11-1.0.0/bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic USER_CREATE --from-beginning  --consumer-property group.id=fanout-consumer-group

```

## Built With

* [Spring Boot](https://projects.spring.io/spring-boot/) - The framework used
* [Maven](https://maven.apache.org/) - Dependency Management
* [Kafka](https://kafka.apache.org/) - Kafka distributed streaming

## Swagger Page - End points available

```
Method: Post
End point: /publish/{topicName}
Use case: publish message
```


