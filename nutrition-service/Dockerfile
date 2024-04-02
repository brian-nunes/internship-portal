# Use uma imagem base do OpenJDK 17
FROM openjdk:17
# Configurar o diretório de trabalho dentro do contêiner
WORKDIR /app

# Copiar o JAR da aplicação para o contêiner
COPY ./target/nutrition-service.jar app.jar

# Expor a porta 8080 (ou a porta que sua aplicação Spring Boot está configurada para usar)
EXPOSE 8080

# Comando para executar a aplicação quando o contêiner iniciar
CMD ["java", "-jar", "app.jar"]
