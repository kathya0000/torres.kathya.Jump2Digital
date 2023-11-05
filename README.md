# torres.kathya.Jump2Digital
# Skins API

Este proyecto es una API REST para la gestión de 'skins' en una aplicación de juegos. Permite a los usuarios registrarse, iniciar sesión y luego realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) en las 'skins' disponibles.

## Comenzando

Para obtener una copia del proyecto en funcionamiento en tu máquina local para propósitos de desarrollo y pruebas, sigue las siguientes instrucciones.

### Prerrequisitos

Antes de comenzar, asegúrate de tener instalado:

- Java JDK 17 
- Maven
- IntelliJ IDEA o tu IDE de preferencia
- Postman para probar los endpoints de la API REST

### Instalación

Sigue estos pasos para configurar tu entorno de desarrollo:

 Clona el repositorio en tu máquina local.

Uso de la API
Para comenzar a usar la API, primero debes registrarte y luego iniciar sesión para obtener un token de acceso. Una vez autenticado, puedes realizar solicitudes a los siguientes endpoints:

Registro
POST /api/auth/register - Registra un nuevo usuario.
Inicio de sesión
POST /api/auth/login - Inicia sesión y devuelve un token de acceso.

Endpoints de Skins (requieren autenticación)
GET /api/skins/available - Devuelve una lista de todas las skins disponibles para comprar.
POST /api/skins/buy - Permite a los usuarios adquirir una skin y guardarla en la base de datos.
GET /api/skins/myskins - Devuelve una lista de las skins compradas por el usuario.
GET /api/skins/{id} - Obtiene la información de una skin específica.
POST /api/skins/add - Añade una nueva skin a la base de datos.
PUT /api/skins/{id}/color - Actualiza el color de una skin existente.
DELETE /api/skins/{id} - Elimina una skin de la base de datos.

Construido con
Spring Boot - El framework utilizado
Maven - Manejador de dependencias
Java - Lenguaje de programación

Autores
KATHYA TORRES SANTAMARIA

   
