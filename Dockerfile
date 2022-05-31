#Definimos el fichero Dockerfile, para poder crear nuestro contenedor y después desplegar la app
FROM maven:3.8.4-jdk-11-slim
RUN mkdir /app
WORKDIR /app
COPY pom.xml .
ADD ./src/ ./src
EXPOSE 8080
CMD ["mvn", "jetty:run"]