FROM openjdk:8
ADD target/netflix-zuul-api-gateway-server-0.0.1-SNAPSHOT.jar zuul-api-gateway-service.jar
EXPOSE 8765
ENTRYPOINT ["java", "-jar", "zuul-api-gateway-service.jar"]