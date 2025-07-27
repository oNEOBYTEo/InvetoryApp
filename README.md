# Inventory App - Inventory Management System

Este proyecto es una aplicación de consola desarrollada en Java para la gestión básica de inventario. Permite realizar operaciones CRUD (Crear, Leer, Actualizar y Eliminar) sobre una base de datos MySQL.

---

## Funcionalidades

- Agregar productos con nombre, cantidad y precio.
- Listar todos los productos registrados.
- Actualizar productos existentes por su ID.
- Eliminar productos por su ID.
- Base de datos persistente con MySQL.

---

## Estructura del Proyecto

El código fuente está organizado en los siguientes paquetes:

- `com.inventory.model`: contiene la clase `Product`.
- `com.inventory.service`: lógica de negocio (`ProductService`).
- `com.inventory.db`: conexión a base de datos y verificación de tabla (`DBConnection`, `DatabaseInitializer`).
- `com.inventory`: contiene el archivo `Main.java` con el flujo principal de la aplicación.

---

## Conexión a la Base de Datos

La conexión se realiza a un contenedor de MySQL que debe estar previamente ejecutándose en Docker o la base de datos ya instalada.

### Datos de conexión

- **Host**: `localhost`
- **Puerto**: `3306`
- **Base de datos**: `inventory`
- **Usuario**: `root`
- **Contraseña**: `1234`

La clase encargada de la conexión es:

```java
package com.inventory.db;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/inventory";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";
    ...
}