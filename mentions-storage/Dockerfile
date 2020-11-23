FROM openjdk:8

RUN mkdir /mentions-storage/

COPY target/mentions-storage-*[0-9T].jar /mentions-storage/mentions-storage.jar

WORKDIR /mentions-storage

CMD ["java", "-jar", "mentions-storage.jar"]