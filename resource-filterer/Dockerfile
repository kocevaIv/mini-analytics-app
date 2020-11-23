FROM openjdk:8

RUN mkdir /resource-filterer/

COPY target/resource-filterer-*[0-9T].jar /resource-filterer/resource-filterer.jar

WORKDIR /resource-filterer

CMD ["java", "-jar", "resource-filterer.jar"]

