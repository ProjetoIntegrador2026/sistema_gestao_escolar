# Estágio 1: Build usando Ant com Java 21
FROM maven:3.9.6-eclipse-temurin-21 AS build
COPY . /app
WORKDIR /app

# Instala o Ant no container
RUN apt-get update && apt-get install -y ant

# Mapeia o Classpath com os nomes exatos da sua pasta lib local
RUN echo "javac.classpath=/app/lib/AbsoluteLayout-RELEASE210.jar:/app/lib/flatlaf-3.5.1.jar:/app/lib/postgresql-42.7.11.jar" >> /app/nbproject/project.properties

# Compila o projeto gerando o jar final
RUN ant jar

# Estágio 2: Execução estável (Ajuste de caminhos)
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app

# Copia a pasta dist inteira (onde está o seu jar executável)
COPY --from=build /app/dist /app/dist

# Copia as bibliotecas físicas EXATAMENTE para dentro de dist/lib
# É aqui que o manifesto do seu JAR espera encontrar os arquivos em tempo de execução!
COPY --from=build /app/lib /app/dist/lib

# Proteção de memória para o Render
ENV JAVA_OPTS="-Xms128m -Xmx256m -XX:+UseSerialGC"

EXPOSE 8080

# Executa o JAR direto de onde ele foi gerado
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar /app/dist/sistema_gestao_escolar.jar"]