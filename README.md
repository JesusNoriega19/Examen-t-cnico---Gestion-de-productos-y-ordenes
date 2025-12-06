# Proyecto de Gestión de Órdenes y Productos

## Descripción
Este proyecto es una aplicación web para la gestión de productos y órdenes, desarrollada con **Spring Boot** en el backend, **Angular** en el frontend y **Oracle Database** como base de datos. Permite crear productos, registrar órdenes con múltiples productos, consultar órdenes filtrando por cliente y rango de fechas, y visualizar los resultados incluyendo el nombre de los productos.

## Tecnologías Utilizadas
- **Backend:** Spring Boot, Java, Maven
- **Frontend:** Angular, TypeScript, HTML, CSS
- **Base de Datos:** Oracle Database, PL/SQL
- **Herramientas:** Lombok, Postman, Git

## Estructura del Proyecto

### Backend (Spring Boot)
- **Model:** Representa las entidades `Producto`, `Orden`, `OrdenDetalle`.
- **DTO:** Clases para transferencia de datos (`OrdenDTO`, `ProductoDTO`, etc.).
- **Repository:** Interfaces para acceder a los datos.
- **Service:** Lógica de negocio y conexión con la base de datos.
- **Controller:** Endpoints REST para el frontend.
- **PL/SQL:** Procedimientos almacenados para crear y consultar órdenes.

### Frontend (Angular)
- Componentes para:
  - Registro de productos.
  - Creación de órdenes.
  - Consulta de órdenes con filtros por cliente y fechas.
- Visualización de resultados en tablas con Angular Material o HTML simple.

### Base de Datos (Oracle)
- Tablas principales:
  - `PRODUCTO`: Información de los productos.
  - `ORDEN`: Información de las órdenes.
  - `ORDEN_DETALLE`: Relación de órdenes con productos y cantidades.
- **Paquete PL/SQL:** `PKG_ORDENES` con procedimientos:
  - `CREAR_ORDEN`: Inserta una nueva orden con productos.
  - `CONSULTAR_ORDENES`: Devuelve órdenes filtradas por cliente y fechas, incluyendo nombre de producto.

## Funcionalidades
- Crear productos con nombre y precio.
- Crear órdenes asociando múltiples productos y cantidades.
- Consultar órdenes filtrando por cliente y rango de fechas.
- Visualizar información completa de cada orden, incluyendo nombre del producto.
- Integración de backend y frontend mediante endpoints REST.

## Ejecución
1. **Backend**
   - Configurar la conexión a Oracle en `application.properties`.
   - Ejecutar el proyecto Spring Boot (`mvn spring-boot:run`).
2. **Frontend**
   - Navegar a la carpeta del proyecto Angular.
   - Ejecutar `npm install` para instalar dependencias.
   - Ejecutar `ng serve` para levantar la aplicación (`http://localhost:4200`).

## Endpoints Principales
- `POST /ordenes/crear` : Crear una nueva orden.
- `GET /ordenes/consultar` : Consultar órdenes por cliente y rango de fechas.
- `GET /productos` : Listar productos.
- `POST /productos` : Crear producto.

## Notas
- La aplicación utiliza **SYS_REFCURSOR** en Oracle para obtener resultados de las órdenes.
- El frontend permite visualizar los resultados con toda la información, incluyendo nombre de productos asociados a cada orden.

## Autor
Jesus Noriega


