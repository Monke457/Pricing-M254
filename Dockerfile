FROM openjdk:19-jdk-alpine
EXPOSE 8080
ADD target/pricing.jar pricing.jar
ENTRYPOINT ["java","-jar","/pricing.jar"]