FROM openjdk:11-slim

COPY build/libs/server-1.0.0-SNAPSHOT.jar /opt/server/server.jar

CMD ["java", "-Djavax.net.ssl.trustStore=/opt/server/jks/ca.jks", "-Djavax.net.ssl.keyStore=/opt/server/jks/server.jks", "-Djava.net.ssl.trustStorePassword=secret", "-Djava.net.ssl.keyStorePassword=secret", "-jar", "/opt/server/server.jar"]
