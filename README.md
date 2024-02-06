# Library Management System

Este es un sistema de gestión de biblioteca que proporciona funcionalidades para buscar libros, añadir nuevos libros, eliminar libros, ordenar libros y devolver libros prestados.

## Descripción del Proyecto
El sistema de gestión de biblioteca permite a los usuarios realizar diversas operaciones relacionadas con la gestión de libros en una biblioteca. Proporciona una API REST que permite a los usuarios buscar libros por título, autor o ISBN, añadir nuevos libros, eliminar libros, ordenar libros para préstamo y devolver libros prestados.

## Tecnologías Utilizadas
Java
H2
Spring Boot
Swagger
Mockito
JUnit

## Prerrequisitos

Antes de ejecutar el proyecto, asegúrate de tener instalado lo siguiente:

Java Development Kit (JDK) 17
Maven
IDE (por ejemplo, IntelliJ IDEA, Eclipse) o cualquier editor de texto de tu elección.

## Ejecución deL proyecto

Clona este repositorio en tu máquina local usando el siguiente comando:

bash
git clone <URL_DEL_REPOSITORIO>
Abre el proyecto en tu IDE o editor de texto.

En la raíz del proyecto, ejecuta el siguiente comando para compilar y ejecutar las pruebas:

mvn clean install
Después de que las pruebas se ejecuten con éxito, puedes ejecutar la aplicación Spring Boot ejecutando el siguiente comando:

arduino
mvn spring-boot:run
La aplicación se ejecutará en http://localhost:8080.

## Documentación con Swagger

Este proyecto incluye Swagger para documentar la API REST. Después de ejecutar la aplicación, puedes acceder a la documentación de Swagger en:

http://localhost:8080/doc/swagger-ui

La documentación Swagger te proporciona una descripción detallada de los endpoints disponibles en la API REST, incluyendo detalles sobre los parámetros, códigos de respuesta y modelos de datos.

## Contribuciones

Las contribuciones son bienvenidas. Si encuentras algún problema o quieres mejorar las pruebas, no dudes en crear un pull request o abrir un issue en el repositorio.