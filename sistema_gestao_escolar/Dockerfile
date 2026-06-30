# Estágio 1: Build usando Ant e Java 8 (via Eclipse Temurin)
FROM maven:3.8.4-openjdk-8 AS build
COPY . /app
WORKDIR /app
# Instala o Ant manualmente no container de build para compilar seu projeto
RUN apt-get update && apt-get install -y ant
RUN ant jar

# Estágio 2: Execução estável com Java 8 ativo
FROM eclipse-temurin:8-jre
WORKDIR /app
COPY --from=build /app/dist /app/dist
COPY --from=build /app/lib /app/lib

# Gerenciamento rígido de memória para os 512MB do Render
ENV JAVA_OPTS="-Xms128m -Xmx256m -XX:+UseSerialGC"

EXPOSE 8080
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar /app/dist/sistema_gestao_escolar.jar"]