# Estágio 1: Build usando Ant com Java 21 e download automático de dependências
FROM maven:3.9.6-eclipse-temurin-21 AS build
COPY . /app
WORKDIR /app

# Instala o Ant no container
RUN apt-get update && apt-get install -y ant

# Cria a pasta lib se ela não existir e baixa as dependências visuais usando links estáveis
RUN mkdir -p /app/lib && \
    curl -L -o /app/lib/AbsoluteLayout.jar https://repo1.maven.org/maven2/org/netbeans/external/AbsoluteLayout/RELEASE210/AbsoluteLayout-RELEASE210.jar && \
    curl -L -o /app/lib/flatlaf.jar https://github.com/FormDev/FlatLaf/releases/download/3.4.1/flatlaf-3.4.1.jar

# Configura o Classpath dinamicamente para o Ant enxergar os JARs baixados e o Driver do Postgres
RUN echo "javac.classpath=/app/lib/AbsoluteLayout.jar:/app/lib/flatlaf.jar:/app/lib/postgresql-42.7.12.jar" >> /app/nbproject/project.properties

# Compila o projeto de forma limpa
RUN ant jar

# Estágio 2: Execução estável com Java 21
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app
COPY --from=build /app/dist /app/dist
COPY --from=build /app/lib /app/lib

# Proteção de memória para o limite de 512MB do plano Free do Render
ENV JAVA_OPTS="-Xms128m -Xmx256m -XX:+UseSerialGC"

EXPOSE 8080
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar /app/dist/sistema_gestao_escolar.jar"]