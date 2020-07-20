FROM openjdk:8-alpine

COPY target/*.jar /opt/ms/wallmart-backend.jar

EXPOSE 8080
ENTRYPOINT exec java $JAVA_OPTS -jar /opt/ms/wallmart-backend.jar