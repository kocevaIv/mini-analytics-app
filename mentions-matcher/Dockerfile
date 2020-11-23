FROM openjdk:8

RUN mkdir /mentions-matcher/

COPY target/mentions-matcher-*[0-9T].jar /mentions-matcher/mentions-matcher.jar

WORKDIR /mentions-matcher

CMD ["java", "-jar", "mentions-matcher.jar"]