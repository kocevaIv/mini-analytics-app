FROM openjdk:8

RUN mkdir /api/

COPY target/api-*[0-9T].jar /api/api.jar

ENV HTTP_PORT 8080

EXPOSE ${HTTP_PORT}

WORKDIR /api

CMD ["java", "-jar", "api.jar"]