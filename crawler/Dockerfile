FROM openjdk:8

RUN mkdir /crawler/

COPY target/crawler-*[0-9T].jar /crawler/crawler.jar

WORKDIR /crawler

CMD ["java", "-jar", "crawler.jar"]