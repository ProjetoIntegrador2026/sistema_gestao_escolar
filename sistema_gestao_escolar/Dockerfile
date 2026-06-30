# Estágio 1: Build usando Ant com Java 21
FROM maven:3.9.6-eclipse-temurin-21 AS build
COPY . /app
WORKDIR /app
# Instala o Ant para compilar o projeto do NetBeans
RUN apt-get update && apt-get install -y ant
RUN ant jar

# Estágio 2: Execução leve com Java 21
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app
COPY --from=build /app/dist /app/dist
COPY --from=build /app/lib /app/lib

# Gerenciamento de memória para manter os 512MB do Render estáveis
ENV JAVA_OPTS="-Xms128m -Xmx256m -XX:+UseSerialGC"

EXPOSE 8080
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar /app/dist/sistema_gestao_escolar.jar"]