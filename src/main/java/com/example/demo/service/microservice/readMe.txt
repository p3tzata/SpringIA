=== Kafka ==========================

edit ../zookeeper.properties {}

>zookeeper-server-start.bat ..\..\config\zookeeper.properties
>kafka-server-start.bat ..\..\config\server.properties
>kafka-topics.bat --create --topic cloud.ingredient --bootstrap-server localhost:9092


spring.kafka.bootstrap-servers=127.0.0.1:9092
<artifactId>spring-kafka</artifactId>




http://127.0.0.1:[port]/commands

https://www.youtube.com/watch?v=B5j3uNBH8X4
https://thepracticaldeveloper.com/spring-boot-kafka-config/
https://reflectoring.io/spring-boot-kafka/



=== Artemis ActiveMQ ===

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-artemis</artifactId>
</dependency>

spring.artemis.host=127.0.0.1
spring.artemis.port=8161
spring.artemis.user=admin
spring.artemis.password=123456

On Server:
.\artemis create myJmsBroker --user=admin --password=123456
cd .\myJmsBroker\
.\artemis run
Artemis Console available at http://localhost:8161/console

=== Artemis ActiveMQ END ===

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-amqp</artifactId>
</dependency>

spring.rabbitmq.host=127.0.0.1
spring.rabbitmq.port=5672
spring.rabbitmq.username=admin
spring.rabbitmq.password=123456
spring.rabbitmq.template.exchange="defaultExchange"
spring.rabbitmq.template.routing-key="defaultRoutingKey"


--Due to authentication fail from executing rabbitmqctl.bat 
If the Windows service is used, the cookie should be copied from C:\Windows\system32\config\systemprofile\.erlang.cookie 
to the expected location(%HOMEDRIVE%%HOMEPATH%\.erlang.cookie) for users running commands like rabbitmqctl.bat.

rabbitmqctl add_user "admin"
rabbitmqctl set_permissions -p "/" "admin" ".*" ".*" ".*" //Grand All permission
rabbitmqctl start_app
rabbitmqctl stop


--Dtue to enable web interface
rabbitmq-plugins enable rabbitmq_management

add queue from web 
rabbitmqctl list_queues --vhost /
Timeout: 60.0 seconds ...
Listing queues for vhost / ...
name    messages
sampleQueue     0








