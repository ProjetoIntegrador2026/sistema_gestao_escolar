# Estágio 1: Build usando Ant e Java 8
FROM frekele/ant:1.10.3-jdk8 AS build
COPY . /app
WORKDIR /app
RUN ant jar

# Estágio 2: Execução enxuta
FROM openjdk:8-jre-slim
WORKDIR /app
COPY --from=build /app/dist /app/dist
COPY --from=build /app/lib /app/lib

# Limitação agressiva de memória para não estourar os 512MB gratuitos do Render
ENV JAVA_OPTS="-Xms128m -Xmx256m -XX:+UseSerialGC"

EXPOSE 8080
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar /app/dist/sistema_gestao_escolar.jar"]