
Group project
to run the project locally 
#On linux based:
1. navigate to project home directory.
run this command: ./mvnw spring-boot:run
run mvnw.cmd spring-boot:run


To run the AngularJS app:
On the command window
1. Go to the TimeApp directory under the frontend folder
2. Run this commad: npm start
3. Once that is running, go to localhost:8000/ 

setup keykloak server with mysql.
	1. Download mysql. 
	2. Log in as root. 
	Mysql -u root -P <enter password>
	
	3. Create keycloak database
	4. create keycloak user
	5. grate access to keycloak user. 
	
	mysql> CREATE USER 'keycloak'@'%' IDENTIFIED BY 'keycloak';
Query OK, 0 rows affected (0.01 sec)
	mysql> CREATE DATABASE keycloak CHARACTER SET utf8 COLLATE utf8_unicode_ci;
Query OK, 1 row affected (0.00 sec)
	mysql> GRANT ALL PRIVILEGES ON keycloak.* TO 'keycloak'@'%';
Query OK, 0 rows affected (0.00 sec)
	
	6. download mysql workbench(Optional )
	7. Download connector  user the platform independent one: https://dev.mysql.com/downloads/connector/j/ 
	
	8. $ sudo mkdir -p {keycloak installtionlocation}/modules/system/layers/keycloak/com/mysql/main
$ sudo cd {keycloak installtionlocation}/modules/system/layers/keycloak/com/mysql/main
$ sudo cp mysql-connector-java-8.0.19/mysql-connector-java-8.0.19-bin.jar .
$ sudo touch module.xml
	9. Add the driver to {keycloak install dir}/standalone/configuration/standalone.xml inside <drivers> tag.
	
	
	<driver name="mysql" module="com.mysql">
	<driver-class>com.mysql.jdbc.Driver</driver-class>
	</driver>
	<driver name="h2" module="com.h2database.h2">
	<xa-datasource-class>org.h2.jdbcx.JdbcDataSource</xa-datasource-class>
	</driver>
	
	10. Add under <datasources>
	
	<datasource jndi-name="java:/jboss/datasources/KeycloakDS" pool-name="KeycloakDS" enabled="true">
	<connection-url>jdbc:mysql://localhost:3306/keycloak?useSSL=false&amp;characterEncoding=UTF-8</connection-url>
	<driver>mysql</driver>
	<pool>
	<min-pool-size>5</min-pool-size>
	<max-pool-size>15</max-pool-size>
	</pool>
	<security>
	<user-name>keycloak</user-name>
	<password>keycloak</password>
	</security>
	</datasource>
	
	11. Commentout the h2 datasource with the name KeycloakDS otherwise it will create problem.
	12. Start keykloak server in standalone mode sh {keycloak install dir}/bin/standalone.sh
13. Navigate to 80080 
