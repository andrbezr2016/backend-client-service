FROM openjdk:21-slim
COPY ./target/*.jar /app/backend-client-service.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/backend-client-service.jar"]