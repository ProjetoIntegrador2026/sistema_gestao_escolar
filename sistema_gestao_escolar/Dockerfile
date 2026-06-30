# Estágio 1: Build usando Ant com Java 21
FROM maven:3.9.6-eclipse-temurin-21 AS build
COPY . /app
WORKDIR /app

# Instala o Ant no container
RUN apt-get update && apt-get install -y ant

# Mapeia o Classpath com os nomes EXATOS que aparecem no seu print da pasta lib
RUN echo "javac.classpath=/app/lib/AbsoluteLayout-RELEASE210.jar:/app/lib/flatlaf-3.5.1.jar:/app/lib/postgresql-42.7.11.jar" >> /app/nbproject/project.properties

# Compila o projeto gerando o jar final
RUN ant jar

# Estágio 2: Execução estável
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app
COPY --from=build /app/dist /app/dist
COPY --from=build /app/lib /app/lib

# Proteção de memória para o limite gratuito de 512MB do Render
ENV JAVA_OPTS="-Xms128m -Xmx256m -XX:+UseSerialGC"

EXPOSE 8080
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar /app/dist/sistema_gestao_escolar.jar"]