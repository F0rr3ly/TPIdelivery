FROM payara/server-full:6.2023.3-jdk17
COPY target/Delivery.war $DEPLOY_DIR
COPY src/main/resources/META-INF/persistence.xml $DEPLOY_DIR/META-INF/