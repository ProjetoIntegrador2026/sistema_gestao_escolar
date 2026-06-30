# Estágio 1: Build usando Ant com Java 21
FROM maven:3.9.6-eclipse-temurin-21 AS build
COPY . /app
WORKDIR /app

# Instala o Ant no container para processar o build do NetBeans
RUN apt-get update && apt-get install -y ant

# Como o AbsoluteLayout costuma ficar embutido no NetBeans, garantimos ele aqui por segurança
RUN curl -L -o /app/lib/AbsoluteLayout.jar https://repo1.maven.org/maven2/org/netbeans/external/AbsoluteLayout/RELEASE210/AbsoluteLayout-RELEASE210.jar

# Configura o Classpath apontando para os arquivos REAIS que estão na sua pasta lib
RUN echo "javac.classpath=/app/lib/AbsoluteLayout.jar:/app/lib/flatlaf.jar:/app/lib/postgresql-42.7.12.jar" >> /app/nbproject/project.properties

# Compila o projeto gerando o arquivo final de forma limpa
RUN ant jar

# Estágio 2: Execução estável em ambiente enxuto
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app
COPY --from=build /app/dist /app/dist
COPY --from=build /app/lib /app/lib

# Controle rígido de memória para não estourar o limite gratuito de 512MB do Render
ENV JAVA_OPTS="-Xms128m -Xmx256m -XX:+UseSerialGC"

EXPOSE 8080
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar /app/dist/sistema_gestao_escolar.jar"]