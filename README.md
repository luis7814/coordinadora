# Ejecución del proyecto
Para lograr correr el proyecto, se debe tener instalado lo siguiente:
##### - JDK 17
##### - POSTGRESQL - PGADMIN
##### - Apache Maven
##### - Puerto 8089 libre

Instalado y configurado lo anterior, procedemos a crear la base de datos con la información que esta en el archivo  
#### application.properties.

Procedemos a levantar la aplicación con 
#### mvn spring-boot:run
Este se debe ejecutar en la raiz del proyecto, ya que se está iniciando directamente desde el código fuente sin la necesidad de empaquetarlo en un archivo JAR. O también podemos ejecutarlo desde nuestro IDE sea Eclipse o IntelliJ.

En caso de querer ejecutar desde el archivo JAR, debemos empaquetar el proyecto con el siguiente comando maven
#### mvn clean package
Luego vamos a la carpeta target del proyecto y encontraremos el archivo
#### events-0.0.1-SNAPSHOT.jar
y lo ejecutaremos con 
#### jar -jar events-0.0.1-SNAPSHOT.jar

Levantado el proyecto, consultaremos y probaremos los servicios ingresando a 
#### http://localhost:8089/swagger-ui/index.html



