FROM payara/server-full:6.2023.3-jdk17
#COPY postgresql-42.3.7.jar /opt/payara/appserver/glassfish/lib/postgresql-42.3.7.jar
COPY target/Delivery.war $DEPLOY_DIR
COPY src/main/resources/META-INF/persistence.xml $DEPLOY_DIR/META-INF/

USER root
#WORKDIR /pruebas
#RUN touch hola.txt
RUN apt update
RUN apt install -y curl
RUN cd /opt/payara/appserver/glassfish/lib/ && curl -O https://jdbc.postgresql.org/download/postgresql-42.3.7.jar

RUN echo "#Crear jdbc connectionpool">> /opt/payara/config/post-boot-commands-final.asadmin
RUN echo "create-jdbc-connection-pool --datasourceclassname org.postgresql.ds.PGSimpleDataSource --restype javax.sql.DataSource --property servername=db:user=postgres:password=postgres:DatabaseName=deliverydb:port=5432:driverClass=org.postgresql.Driver pg_conpoolpostgres" >> /opt/payara/config/post-boot-commands-final.asadmin
RUN echo "#Crear jdbc resource">> /opt/payara/config/post-boot-commands-final.asadmin
RUN echo "create-jdbc-resource --connectionpoolid pg_conpoolpostgres jdbc/delivery">>/opt/payara/config/post-boot-commands-final.asadmin
#Recordar que el nombre del jdbc resource debe ser el mismo que el de la persistencia del proyecto, mi caso es jdbc/delivery, sino falla en el deploy

RUN echo "#deploy delivery" >> /opt/payara/config/post-boot-commands-final.asadmin
RUN echo "deploy /opt/payara/deployments/Delivery.war" >> /opt/payara/config/post-boot-commands-final.asadmin

#RUN asadmin create-jdbc-connection-pool --datasourceclassname org.postgresql.ds.PGSimpleDataSource --restype javax.sql.DataSource --property servername=localhost:user=postgres:password=postgres:DatabaseName=deliverydb:port=5432:driverClass=org.postgresql.Driver pg_conpoolpostgres
#RUN asadmin create-jdbc-resource --connectionpoolid pg_conpoolpostgres jdbc/delivery