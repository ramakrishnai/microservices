FROM openjdk:8
ADD target/currency-exchange-service-0.0.1-SNAPSHOT.jar currency-exchange-service.jar
EXPOSE 8000
ENTRYPOINT ["java", "-jar", "currency-exchange-service.jar"]