FROM payara/server-full:6.2023.3-jdk17
COPY postgresql-42.3.7.jar /opt/payara/appserver/glassfish/lib/postgresql-42.3.7.jar
COPY target/Delivery.war $DEPLOY_DIR
COPY src/main/resources/META-INF/persistence.xml $DEPLOY_DIR/META-INF/
#RUN asadmin create-jdbc-connection-pool --datasourceclassname org.postgresql.ds.PGSimpleDataSource --restype javax.sql.DataSource --property servername=localhost:user=postgres:password=postgres:DatabaseName=deliverydb:port=5432:driverClass=org.postgresql.Driver pg_conpoolpostgres
#RUN asadmin create-jdbc-resource --connectionpoolid pg_conpoolpostgres jdbc/delivery