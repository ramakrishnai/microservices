FROM openjdk:8
ADD target/netflix-eureka-naming-server-0.0.1-SNAPSHOT.jar eureka-naming-server.jar
EXPOSE 8761
ENTRYPOINT ["java", "-jar", "eureka-naming-server.jar"]