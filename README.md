# Caso Práctico: API REST de Gestión de Clientes (Spring Boot 3)

## 1. Visión y Requisitos Técnicos de Arquitectura
* **Framework y Lenguaje:** Spring Boot 3.x (Spring Web, Spring Data JPA, Bean Validation) con Java 21.
* **Persistencia Temporaria:** Base de datos H2 en memoria (`jdbc:h2:mem:clientesdb`), configurada con consola web activa (`/h2-console`) para inspección de tablas durante pruebas locales.
* **Manejo Global de Excepciones:** Controlador `@RestControllerAdvice` para capturar errores de validación o entidades no encontradas y retornarlos en formato estandarizado según RFC 7807 (`ProblemDetail`).
* **Documentación:** OpenAPI 3 / Swagger UI habilitado en `/swagger-ui.html`.

---

## 2. Historias de Usuario con Criterios de Aceptación (BDD)

### HU-01: Gestión CRUD de Clientes
**Como** Analista de Datos,  
**quiero** registrar, consultar, actualizar y eliminar clientes,  
**para** mantener actualizada la base de datos operativa.

#### Criterios de Aceptación (Formato BDD - Given / When / Then):
* **Escenario 1 (Creación exitosa):**
  * **Given** que envío una solicitud `POST /api/v1/clientes` con un payload válido (DNI único, Nombre, Email).
  * **When** la API procesa el registro.
  * **Then** responde con HTTP `201 Created`, incluye el header `Location` y el cliente generado con su ID.
* **Escenario 2 (Validación de DNI duplicado):**
  * **Given** que ya existe un cliente registrado con el DNI `"12345678"`.
  * **When** intento registrar otro cliente con el mismo DNI `"12345678"`.
  * **Then** la API responde con HTTP `409 Conflict` indicando el fallo de unicidad.

---

### HU-02: Búsqueda y Filtrado por DNI o Nombre
**Como** Operador del Sistema,  
**quiero** realizar consultas por DNI o por coincidencia de Nombre,  
**para** ubicar rápidamente la información del cliente.

#### Criterios de Aceptación (Formato BDD):
* **Escenario 1 (Búsqueda exacta por DNI):**
  * **Given** que realizo una petición `GET /api/v1/clientes?dni=12345678`.
  * **When** la API ejecuta la consulta en H2.
  * **Then** retorna HTTP `200 OK` con los datos del cliente encontrado.
* **Escenario 2 (Búsqueda parcial por Nombre):**
  * **Given** que realizo una petición `GET /api/v1/clientes?nombre=Carlos`.
  * **When** la API busca coincidencias parciales sin diferenciar mayúsculas/minúsculas (case-insensitive).
  * **Then** retorna HTTP `200 OK` con el listado de clientes coincidentes.

---

## 3. Especificación del Contrato de API (Endpoints REST)

| Método HTTP | Endpoint | Descripción | Parámetros / Body | Código HTTP Esperado |
| :--- | :--- | :--- | :--- | :--- |
| **POST** | `/api/v1/clientes` | Crear cliente | Body: `ClienteRequestDTO` | `201 Created` / `400 Bad Request` / `409 Conflict` |
| **GET** | `/api/v1/clientes` | Listar / Buscar | Query Params opcionales: `dni`, `nombre` | `200 OK` |
| **GET** | `/api/v1/clientes/{id}` | Obtener por ID | Path Param: `id` | `200 OK` / `404 Not Found` |
| **PUT** | `/api/v1/clientes/{id}` | Actualizar cliente | Path Param: `id`, Body: `ClienteRequestDTO` | `200 OK` / `404 Not Found` / `400 Bad Request` |
| **DELETE** | `/api/v1/clientes/{id}` | Eliminar cliente | Path Param: `id` | `204 No Content` / `404 Not Found` |

---

## 4. Listas de Cotejo Técnicas (DoR y DoD)

### Definition of Ready (DoR) - Criterios de Entrada al Sprint
- [x] **Marco INVEST:** La Historia de Usuario cumple el principio INVEST (es independiente, negociable, aporta valor, es estimable, pequeña y testeable).
- [x] **Contrato de API e Interfaces:** La arquitectura, esquemas de base de datos y contratos de integración están pre-diseñados y acordados.
- [x] **Reglas de Validación:** Las reglas de validación (DNI obligatorio de 8 dígitos, formato de email) están documentadas.
- [x] **Estimación Técnica:** La estimación en Story Points fue asignada por el equipo técnico (Planning Poker / Fibonacci).

### Definition of Done (DoD) - Criterios de Salida e Incremento de Calidad
- [x] **Funcionalidad y Persistencia:** Código funcional con la base de datos H2 en memoria configurada.
- [x] **Pruebas y CI/CD:** Suite de pruebas unitarias (JUnit 5 + Mockito) y de integración (`@SpringBootTest`) ejecutadas con éxito en el pipeline.
- [x] **Revisión de Código (PR):** Pull Request (PR) atómico (<300 líneas) revisado y aprobado por al menos un desarrollador senior o par.
- [x] **Análisis Estático:** Herramientas como Codacy o SonarQube confirman ausencia de vulnerabilidades y code smells graves.
- [x] **Documentación Técnica:** Documentación interactiva habilitada en Swagger / OpenAPI y archivo `README.md` actualizado.
