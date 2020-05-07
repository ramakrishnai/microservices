FROM openjdk:8
ADD target/eureka-exchange-server.jar currency-exchange-service.jar
EXPOSE 8084
ENTRYPOINT ["java", "-jar", "currency-exchange-service.jar"]