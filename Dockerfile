FROM openjdk:11
EXPOSE 8090
ADD ./target/*.jar jwt.jar
ENTRYPOINT ["java","-jar","jwt.jar"]
