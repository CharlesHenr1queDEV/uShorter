FROM openjdk:17-jdk-alpine

# Definir o diretório de trabalho dentro do container
WORKDIR /app

# Copiar o arquivo JAR para o diretório de trabalho
COPY target/ushorter-0.0.1-SNAPSHOT.jar app.jar

# Informar o comando para rodar o aplicativo
ENTRYPOINT ["java", "-jar", "app.jar"]
