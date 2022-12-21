FROM openjdk:19

RUN mkdir -p /home/app-bloque10

WORKDIR /home/app-bloque10

COPY /target/*jar ./

EXPOSE 8080

CMD ["java","-jar","block10-dockerize-app-0.0.1-SNAPSHOT.jar"]