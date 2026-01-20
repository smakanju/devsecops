FROM eclipse-temurin:21-jre

WORKDIR /app

ARG JAR_FILE=target/catalog-service.jar
COPY ${JAR_FILE} app.jar

ENV JAVA_OPTS="-XX:+UseContainerSupport -XX:MaxRAMPercentage=75.0"

USER 1001

EXPOSE 8080

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
