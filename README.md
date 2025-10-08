# Sprint_0_Ferran_Sansaloni_Prats

Repositorio correspondiente al **Sprint 0** del proyecto.  
En este sprint se sientan las bases técnicas del sistema, haciendo ingeniería inversa de todo el código dado y dando los primeros pasos con la API Rest y la base de datos.

---

# Objetivo del Sprint 0

El objetivo principal de este sprint es establecer la **base del sistema completo**, garantizando la correcta comunicación entre todos los componentes:

- Sensor (Arduino - nRF52840)
- Servidor (API Rest en PHP)
- Android Studio (teléfono)
- Base de datos (phpMyAdmin)

---

# API Rest

En esta parte se implementan los endpoints básicos:
- **GET /api_get.php** → Devuelve todas las mediciones registradas o filtradas por sensor a partir del id del sensor.
- **POST /api_post.php** → Recibe mediciones desde el Android Studio y las guarda en la base de datos.

Toda la lógica de negocio se gestiona mediante la clase **LogicaMediciones.php**, encargada de insertar y recuperar datos.

Consulta la documentación de la API en:
**/doc/Documentación_API.pdf**

---

# Lógica de negocio

- La clase **LogicaMediciones** incluye:
    - Un **constructor** que inicializa la conexión con la base de datos.
    - Métodos para **guardar** y **recuperar** mediciones.

Consulta la documentación de la Lógica de Negocio en:
**/doc/Documentación_Logica_Negocio.pdf**

---

# Test

Dentro de la carpeta **test/** se incluye el script para validar que cada proceso funciona correctamente:
- Conexión con la base de datos.
- Inserción de una nueva medición **POST**.
- Lectura de la medición **GET**.
- Eliminación de la medición.

Consulta la documentación de los Test en:
**/doc/Documentación_Test.pdf**

---

# Documentación adicional

Toda la documentación y los diagramas se encuentran en la carpeta **/doc**
Incluye:
- Diseños de todos los métodos y clases de cada parte del proyecto.
- Documentación de endpoints y ejemplos de uso
- Explicación paso a paso del funcionamiento de la API y de la lógica de negocio.

---

# Autor

**Ferran Sansaloni Prats**
**Fecha de creación: 30/09/2025**
**Fecha de modificación: 08/10/2025**

---

**Nota Importante:**
Todos los archivos y diseños están disponibles en el repositorio de GitHub para una mejor visualización.