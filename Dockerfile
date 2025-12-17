# Etapa 1: Build
FROM eclipse-temurin:25-jdk-alpine AS build

WORKDIR /app

# Instala Maven y bash
RUN apk add --no-cache maven bash git

# Copiamos proyecto completo
COPY pom.xml .
COPY src ./src

# Build del proyecto con perfil prod y sin tests
RUN mvn clean package -DskipTests -Pprod

# Etapa 2: Imagen mínima con jlink
FROM eclipse-temurin:25-jdk-alpine AS runtime

WORKDIR /app

# Copiamos jar del build
COPY --from=build /app/target/*.jar app.jar

# Copiamos variables de entorno (debe existir .env en el host si quieres inyectarlas)
COPY .env .

# Variables de entorno para Spring Boot
ENV SPRING_PROFILES_ACTIVE=prod
ENV SPRING_CONFIG_LOCATION=classpath:/application.properties,file:./.env

# Expone puerto
EXPOSE 8080

# Comando de ejecución
ENTRYPOINT ["java","-jar","app.jar"]