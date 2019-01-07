FROM openjdk:12
ADD build/libs/demo-search.jar demo-search.jar
ENTRYPOINT ["java", "-jar", "demo-search.jar"]
EXPOSE 8080:8080
