# --- STAGE 1: Build jar ---
FROM eclipse-temurin:25-jdk-alpine AS build

WORKDIR /app

# Copiamos pom.xml y descargamos dependencias
COPY pom.xml .
RUN ./mvnw dependency:go-offline -B

# Copiamos el resto del proyecto
COPY src ./src

# Construimos el jar
RUN ./mvnw clean package -DskipTests -Pprod

# --- STAGE 2: Crear runtime mínimo con jlink ---
FROM eclipse-temurin:25-jdk-alpine AS jlink

WORKDIR /app

# Copiamos jar
COPY --from=build /app/target/*.jar app.jar

# Creamos un runtime mínimo con jlink
RUN jlink \
    --add-modules java.base,java.logging,java.sql,java.naming \
    --output /app/runtime \
    --compress 2 \
    --no-header-files \
    --no-man-pages

# --- STAGE 3: Imagen final ---
FROM alpine:3.18

WORKDIR /app

# Copiamos runtime y jar
COPY --from=jlink /app/runtime /opt/java
COPY --from=jlink /app/app.jar .

# Configuramos PATH
ENV PATH="/opt/java/bin:$PATH"

# Exponemos puerto
EXPOSE 8080

# Entry point
ENTRYPOINT ["java", "-jar", "app.jar"]