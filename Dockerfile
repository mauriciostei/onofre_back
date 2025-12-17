# =====================
# Etapa 1: Build
# =====================
FROM eclipse-temurin:25-jdk-alpine AS build

WORKDIR /app

# Instala Maven, bash y git
RUN apk add --no-cache maven bash git

# Copiamos proyecto completo
COPY pom.xml .
COPY src ./src

# Build del proyecto con perfil prod y sin tests
RUN mvn clean package -DskipTests -Pprod

# =====================
# Etapa 2: Imagen mínima
# =====================
FROM eclipse-temurin:25-jdk-alpine AS runtime

WORKDIR /app

# Copiamos jar del build
COPY --from=build /app/target/*.jar app.jar

# Exponemos el puerto de la app
EXPOSE 8080

# Comando de ejecución
ENTRYPOINT ["java", "-jar", "app.jar"]