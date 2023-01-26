FROM openjdk:17-jdk-alpine
COPY "./target/block16-eureka-0.0.1-SNAPSHOT.jar" "app.jar"
EXPOSE 8761
ENTRYPOINT ["java", "-jar", "app.jar"]