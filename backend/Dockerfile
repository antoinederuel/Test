# 1. On part d'une base Java 17 légère (Alpine Linux)
FROM eclipse-temurin:17-jdk-alpine

# 2. On ajoute un volume pour les fichiers temporaires de Tomcat
VOLUME /tmp

# 3. La variable qui cible le fichier .jar généré
ARG JAR_FILE=target/*.jar

# 4. On copie le .jar de ton PC (ou de la CI) vers l'intérieur du conteneur sous le nom app.jar
COPY ${JAR_FILE} app.jar

# 5. La commande magique qui se lance au démarrage du conteneur
ENTRYPOINT ["java","-jar","/app.jar"]