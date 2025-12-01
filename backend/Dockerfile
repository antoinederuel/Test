# --- Étape 1 : Compilation (Builder) ---
FROM eclipse-temurin:17-jdk-alpine as builder
WORKDIR /app

# On copie tout le code source dans le conteneur
COPY . .

# On donne les droits d'exécution au wrapper Maven
RUN chmod +x mvnw

# On lance la compilation DANS Docker (crée le .jar)
RUN ./mvnw clean package -DskipTests

# --- Étape 2 : Exécution (Runner) ---
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app

# On récupère le .jar généré à l'étape 1
# Attention : le chemin target/*.jar peut varier, on prend le premier qu'on trouve
COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar"]