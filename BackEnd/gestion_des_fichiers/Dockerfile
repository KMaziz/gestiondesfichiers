FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE=gestion_des_fichiers-0.0.1-SNAPSHOT.jar
COPY ./target/${JAR_FILE} app.jar
EXPOSE 8090
ENTRYPOINT ["java","-jar","crud.jar"]
