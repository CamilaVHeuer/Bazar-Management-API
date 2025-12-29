# 🏪 Bazar Management API

**Sistema de Gestión de Bazar desarrollado con Spring Boot**

> **Proyecto Integrador** desarrollado como parte del curso "Desarrollo de APIs con Spring Boot" de **TodoCode Academy**. La implementación y diseño arquitectónico son completamente originales, orientados a demostrar competencias profesionales en desarrollo backend con Java.

---

## 📋 **Descripción del Proyecto**

**Bazar Management** es una API REST completa para la gestión de un bazar, que permite administrar productos, clientes y ventas. El sistema implementa operaciones CRUD completas y funcionalidades avanzadas de negocio, siguiendo las mejores prácticas de desarrollo con Spring Boot.

### 🎯 **Propósito**

Este proyecto forma parte de mi **portfolio profesional** como desarrollador backend Java, demostrando competencias en:

- Desarrollo de APIs REST robustas
- Implementación de arquitectura en capas
- Aplicación de patrones de diseño
- Programación funcional con Java 8+
- Manejo profesional de excepciones

---

## 🚀 **Funcionalidades Principales**

### **👥 Gestión de Clientes**

- ✅ CRUD completo de clientes
- ✅ Validaciones de campos obligatorios
- ✅ Validación de DNI único (con método `existsByDni()`)
- ✅ Búsqueda por ID con manejo de excepciones
- ✅ Actualizaciones parciales inteligentes

### **📦 Gestión de Productos**

- ✅ CRUD completo de productos
- ✅ Control de inventario (stock)
- ✅ Consulta de productos con stock bajo (con método `findByStockLessThanEqual()`)
- ✅ Actualizaciones parciales que preservan datos existentes

### **🛒 Gestión de Ventas**

- ✅ Creación de ventas multi-producto
- ✅ Validación automática de stock disponible
- ✅ Actualización automática de inventario
- ✅ Cálculo automático de subtotales y total
- ✅ Consultas avanzadas (venta mayor, resumen por fecha)
- ✅ Obtener productos de una venta específica

### **📊 Diagrama UML del Sistema**

El proyecto incluye un **diagrama UML completo** que muestra las relaciones entre entidades, DTOs y la arquitectura del sistema.

📄 **Archivo**: [`UML Bazar-Management API.png`](./UML%20Bazar-Management%20API.png)

**Modelo de Datos - 4 entidades principales:**

- **👤 Customer**: Gestión de clientes (customerId, firstName, lastName, dni)
- **📦 Product**: Gestión de productos (productId, name, brand, unitPrice, stock)
- **🛒 Sale**: Gestión de ventas (saleId, dateSale, customerId, total)
- **🧾 SalesDetail**: Detalles de venta (productId, quantity, unitPrice, subTotal)

**Relaciones Principales:**

- **Customer** `makes` **Sale** (1:N)
- **Product** `appears in` **SalesDetail** (1:N)
- **Sale** `contains` **SalesDetail** (1:N)
- **Mapeo DTO ↔ Entity** para todas las capas

> El diagrama ilustra la arquitectura completa incluyendo entidades JPA, DTOs, y las relaciones entre todos los componentes del sistema.

---

## 🏗️ **Arquitectura y Patrones Implementados**

### **Arquitectura MVC - Capas**

```
📦 Bazar-Management/
├── 🎮 Controller/     # Capa de presentación (REST Controllers)
├── 💼 Service/        # Capa de lógica de negocio
├── 🗃️ Repository/     # Capa de acceso a datos (JPA)
├── 🏗️ Model/          # Entidades JPA
├── 📦 DTO/            # Data Transfer Objects
└── 🔄 Mapper/         # Conversores Entity ↔ DTO
```

### **Patrones de Diseño Implementados**

- **🔄 DTO Pattern**: Transferencia segura de datos entre capas
- **📋 Repository Pattern**: Abstracción de acceso a datos
- **🏭 Service Layer Pattern**: Encapsulación de lógica de negocio
- **🗂️ Mapper Pattern**: Conversión entre entidades y DTOs

### **Programación Funcional**

- **λ Expresiones Lambda**: Para operaciones de filtrado y mapeo
- **🌊 Stream API**: Procesamiento de colecciones
- **📎 Method References**: Código más limpio y legible

```java
// Ejemplo de programación funcional implementada
return saleRepo.findAll()
    .stream()
    .max(Comparator.comparingDouble(Sale::getTotal))
    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No sales found"));
```

### **ResponseEntity para Respuestas HTTP**

Todas las respuestas implementan **ResponseEntity** para un control granular de códigos de estado HTTP:

```java
// Ejemplos de respuestas estructuradas
return ResponseEntity.ok(productDTO);                           // 200 OK
return ResponseEntity.created(location).body(productDTO);       // 201 Created
return ResponseEntity.notFound().build();                       // 404 Not Found
return ResponseEntity.badRequest().body("Error message");       // 400 Bad Request
return ResponseEntity.status(HttpStatus.CONFLICT)
    .body("DNI already exists");                                // 409 Conflict
return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
    .body("Internal error");                                    // 500 Internal Server Error
```

---

## ️ **Stack Tecnológico**

| Tecnología          | Versión | Propósito                          |
| ------------------- | ------- | ---------------------------------- |
| **Java**            | 17      | Lenguaje de programación           |
| **Spring Boot**     | 3.x     | Framework principal                |
| **Spring Data JPA** | -       | ORM y acceso a datos               |
| **MySQL**           | 8.x     | Base de datos (producción)         |
| **H2 Database**     | -       | Base de datos (desarrollo/testing) |
| **Maven**           | -       | Gestión de dependencias            |
| **Lombok**          | -       | Reducción de código boilerplate    |
| **Postman**         | -       | Testing de endpoints               |

---

## **Endpoints de la API**

**Base URL**: `http://localhost:8080/api`

### **👥 Clientes**

```http
GET    /customers           # Listar todos los clientes
POST   /customers           # Crear nuevo cliente
GET    /customers/{id}      # Obtener cliente por ID
PUT    /customers/{id}      # Actualizar cliente (parcial)
DELETE /customers/{id}      # Eliminar cliente
```

### **📦 Productos**

```http
GET    /products            # Listar todos los productos
POST   /products            # Crear nuevo producto
GET    /products/{id}       # Obtener producto por ID
PUT    /products/{id}       # Actualizar producto (parcial)
DELETE /products/{id}       # Eliminar producto
GET    /products/low-stock  # Productos con stock bajo (≤ 5)
```

### **🛒 Ventas**

```http
GET    /sales               # Listar todas las ventas
POST   /sales               # Crear nueva venta
GET    /sales/{id}          # Obtener venta por ID
PUT    /sales/{id}          # Actualizar venta (solo cliente y fecha)
DELETE /sales/{id}          # Eliminar venta
GET    /sales/products/{id} # Productos de una venta específica
GET    /sales/date/{date}   # Resumen de ventas por fecha
GET    /sales/greatest-total-amount    # Venta con mayor monto
```

---

## 💡 **Ejemplos de Uso**

### **Crear un Producto**

```json
POST /api/products
Content-Type: application/json

{
    "name": "Vaso de vidrio transparente 250ml",
    "brand": "Luminarc",
    "unitPrice": 8.50,
    "stock": 24
}

Response: 201 Created
{
    "productId": 1,
    "name": "Vaso de vidrio transparente 250ml",
    "brand": "Luminarc",
    "unitPrice": 8.50,
    "stock": 24
}
```

### **Crear una Venta**

```json
POST /api/sales
Content-Type: application/json

{
    "dateSale": "2025-12-27",
    "customerId": 1,
    "items": [
        {
            "productId": 1,
            "quantity": 4
        },
        {
            "productId": 2,
            "quantity": 1
        }
    ]
}

Response: 201 Created
{
    "saleId": 1,
    "dateSale": "2025-12-27",
    "customerId": 1,
    "total": 59.00,
    "items": [
        {
            "saleDetailId": 1,
            "productId": 1,
            "productName": "Vaso de vidrio transparente 250ml",
            "quantity": 4,
            "unitPrice": 8.50,
            "subTotal": 34.00
        },
        {
            "saleDetailId": 2,
            "productId": 2,
            "productName": "Plato hondo 22cm",
            "quantity": 1,
            "unitPrice": 25.00,
            "subTotal": 25.00
        }
    ]
}
```

> ✨ **Nota**: Para más ejemplos detallados, consultar la **colección de Postman** incluida en el proyecto.

### **Consultas Especiales**

#### **Productos con Stock Bajo**

```http
GET /api/products/low-stock

Response: 200 OK
[
    {
        "productId": 3,
        "name": "Set de cubiertos 24pcs",
        "brand": "Tramontina",
        "unitPrice": 45.00,
        "stock": 2
    }
]
```

---

## ⚡ **Características Técnicas Destacadas**

### **🔒 Validaciones Robustas**

El sistema implementa validaciones exhaustivas en todas las capas:

- ✅ **Campos obligatorios**: Verificación de datos nulos y strings vacíos
- ✅ **Unicidad de datos**: Prevención de DNI duplicados y recursos existentes
- ✅ **Reglas de negocio**: Validación de valores negativos, stock insuficiente
- ✅ **Integridad temporal**: Verificación de fechas futuras y coherencia de datos
- ✅ **Códigos HTTP apropiados**: 400 Bad Request, 409 Conflict, 404 Not Found

### **🗃️ Integridad de Datos y Cascade**

El sistema implementa **operaciones en cascada** para mantener la integridad referencial:

- ✅ **CascadeType.ALL**: Las operaciones de venta afectan automáticamente a sus detalles
- ✅ **Prevención de registros huérfanos**: Al eliminar una venta, se eliminan todos los SalesDetail asociados
- ✅ **Integridad referencial**: Garantiza consistencia entre entidades relacionadas
- ✅ **Transacciones automáticas**: Operaciones atómicas para mantener la coherencia de datos

### **🛡️ Manejo Profesional de Excepciones**

El sistema implementa un manejo robusto de errores con **ResponseStatusException** y códigos HTTP semánticos:

```java
// Ejemplo de manejo de excepciones personalizado
Customer customer = customerRepo.findById(id)
    .orElseThrow(() -> new ResponseStatusException(
        HttpStatus.NOT_FOUND,
        "Customer not found"
    ));
```

## 🚀 **Instalación y Ejecución**

### **Prerrequisitos**

- ☕ Java 17 o superior
- 📦 Maven 3.6+
- 🗄️ MySQL 8.x (opcional, incluye H2 para desarrollo)

### **Configuración**

1. **Clonar el repositorio**

```bash
git clone https://github.com/CamilaVHeuer/Bazar-Management-API.git
cd Bazar-Management-API
```

2. **Configurar base de datos**

**Para H2 (Recomendado para desarrollo):**

```properties
# application.properties
spring.datasource.url=jdbc:h2:mem:bazar_db
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=

spring.h2.console.enabled=true
spring.h2.console.path=/h2-console

spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.show-sql=true
```

**Para MySQL (Producción):**

```properties
# application.properties
spring.datasource.url=jdbc:mysql://localhost:3306/bazar_db
spring.datasource.username=tu_usuario
spring.datasource.password=tu_password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

3. **Ejecutar la aplicación**

```bash
# Compilar el proyecto
mvn clean compile

# Ejecutar la aplicación
mvn spring-boot:run
```

4. **Acceder a los servicios**

- **API Base**: `http://localhost:8080/api`
- **H2 Console** (si está habilitado): `http://localhost:8080/h2-console`

---

## 🧪 **Testing**

### **Colección de Postman**

El proyecto incluye una colección de Postman con:

- ✅ Todos los endpoints para Customers, Products y Sales
- ✅ Ejemplos de requests con datos de prueba
- ✅ Requests para todos los métodos HTTP (GET, POST, PUT, DELETE)

**Para importar la colección:**

1. Abrir Postman
2. Click en "Import"
3. Navegar a la carpeta `postman/` del proyecto
4. Seleccionar el archivo `Bazar-Management-API.postman_collection.json`

> 📝 **Nota**: La colección contiene todas las requests necesarias para probar la funcionalidad completa de la API.

## 🔧 **Estructura del Proyecto**

### **Estructura General**

```
Bazar-Management/
├── 📄 README.md                     # Documentación principal del proyecto
├── 📊 UML Bazar-Management API.png  # Diagrama UML completo del sistema
├── 📄 HELP.md                       # Guía de ayuda de Spring Boot
├── 📄 pom.xml                       # Configuración Maven y dependencias
├── 📁 postman/                      # Colección de Postman para testing
│   └── Bazar-Management-API.postman_collection.json
├── 📁 src/main/java/com/camicompany/BazarManagement/
│   ├── 📁 controller/               # REST endpoints
│   │   ├── CustomerController.java  # Endpoints para clientes
│   │   ├── ProductController.java   # Endpoints para productos
│   │   └── SaleController.java      # Endpoints para ventas
│   ├── 📁 dto/                      # Data Transfer Objects
│   │   ├── CustomerDTO.java         # DTO de cliente
│   │   ├── ProductDTO.java          # DTO de producto
│   │   ├── SaleDTO.java             # DTO de venta
│   │   ├── SalesDetailDTO.java      # DTO de detalle de venta
│   │   └── SalesSummaryDTO.java     # DTO de resumen de ventas
│   ├── 📁 mapper/
│   │   └── Mapper.java              # Conversiones Entity ↔ DTO
│   ├── 📁 model/                    # Entidades JPA
│   │   ├── Customer.java            # Entidad de cliente
│   │   ├── Product.java             # Entidad de producto
│   │   ├── Sale.java                # Entidad de venta
│   │   └── SalesDetail.java         # Entidad de detalle de venta
│   ├── 📁 repository/               # Capa de acceso a datos
│   │   ├── ICustomerRepository.java # Repositorio de clientes
│   │   ├── IProductRepository.java  # Repositorio de productos
│   │   └── ISaleRepository.java     # Repositorio de ventas
│   ├── 📁 service/                  # Capa de lógica de negocio
│   │   ├── ICustomerService.java    # Interfaz servicio cliente
│   │   ├── CustomerService.java     # Implementación servicio cliente
│   │   ├── IProductService.java     # Interfaz servicio producto
│   │   ├── ProductService.java      # Implementación servicio producto
│   │   ├── ISaleService.java        # Interfaz servicio venta
│   │   └── SaleService.java         # Implementación servicio venta
│   └── BazarManagementApplication.java # Clase principal Spring Boot
├── 📁 src/main/resources/
│   ├── application.properties       # Configuración de la aplicación
│   ├── 📁 static/                   # Recursos estáticos
│   └── 📁 templates/                # Plantillas (si las hubiera)
└── 📁 src/test/java/               # Tests unitarios
    └── com/camicompany/BazarManagement/
        └── BazarManagementApplicationTests.java
```

## 🎯 **Competencias Técnicas Demostradas**

### **Backend Development**

- ✅ Desarrollo de APIs REST completas
- ✅ Implementación de arquitectura en capas (MVC)
- ✅ Manejo de relaciones complejas en JPA
- ✅ Implementación de patrones de diseño

### **Spring Framework**

- ✅ Spring Boot para configuración automática
- ✅ Spring Data JPA para persistencia
- ✅ Spring Web para controladores REST
- ✅ Dependency Injection con @Autowired

### **Java Moderno**

- ✅ Programación funcional (Streams, Lambda, Method References)
- ✅ Optional para manejo seguro de nulos
- ✅ Lombok para reducción de boilerplate
- ✅ Manejo de fechas con LocalDate

### **Bases de Datos**

- ✅ Diseño de esquemas relacionales
- ✅ Implementación de relaciones 1:N
- ✅ Consultas derivadas de JPA
- ✅ Métodos personalizados en repositorios (`existsByDni`, `findByStockLessThanEqual`)
- ✅ Transacciones automáticas

### **Buenas Prácticas**

- ✅ Separation of Concerns
- ✅ Defensive Programming
- ✅ Clean Code principles
- ✅ RESTful API design
- ✅ Proper error handling

---

## 🛡️ **Manejo de Errores**

El sistema implementa un manejo robusto de errores con códigos HTTP apropiados:

### **Códigos de Estado Implementados**

| Código  | Descripción           | Ejemplo                            |
| ------- | --------------------- | ---------------------------------- |
| **200** | OK                    | Operación exitosa                  |
| **201** | Created               | Entidad creada correctamente       |
| **400** | Bad Request           | Datos inválidos, valores negativos |
| **404** | Not Found             | Entidad no encontrada              |
| **409** | Conflict              | DNI duplicado, recurso ya existe   |
| **500** | Internal Server Error | Error del sistema                  |

## ‍💻 **Desarrollado por**

**Camila V. Heuer**

- 📧 Email: [camila.vheuer@email.com]
- 💼 LinkedIn: [linkedin.com/in/camila-vheuer]
- 🐙 GitHub: [github.com/CamilaVHeuer]

---

## 🎓 **Contexto Académico**

Este proyecto fue desarrollado como **Proyecto Integrador** para el curso:

- **Curso**: Desarrollo de APIs con Spring Boot
- **Academia**: TodoCode Academy
- **Año**: 2025

**Nota**: Aunque la consigna fue proporcionada por la academia, toda la implementación, diseño arquitectónico y decisiones técnicas fueron desarrolladas de manera completamente independiente.
