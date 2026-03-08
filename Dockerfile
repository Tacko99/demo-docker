# Utiliser l'image Java avec Eclipse Temurin JDK 17 sur Ubuntu Jammy
FROM eclipse-temurin:17-jdk-jammy

# Définir le répertoire de travail dans le conteneur
WORKDIR /app

# Copier le fichier JAR généré par Maven dans le conteneur
COPY target/*.jar app.jar

# Exposer le port 8080 pour l'application Spring Boot
EXPOSE 8080

# Commande pour lancer le JAR
ENTRYPOINT ["java", "-jar", "app.jar"]
