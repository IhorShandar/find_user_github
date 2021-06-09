FROM openjdk:11-jre-slim
EXPOSE 8089
ARG JAR_FILE=target/find_user_github-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-Djava.security.agd=file:/dev/./urandom","-jar", "/app.jar"]
