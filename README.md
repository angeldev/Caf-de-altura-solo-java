# Cafetería - Sistema de Gestión de Cafés.

Sistema de gestión para una cafetería que permite administrar variedades de café, clientes y órdenes de forma eficiente utilizando una API REST, con postman y mysql workbench.
Este proyecto esta exento de html y css.


## 📖 Descripción

Este proyecto es una aplicación backend desarrollada con **Spring Boot** para gestionar una cafetería. Permite realizar operaciones CRUD sobre cafés, clientes y órdenes, con soporte para paginación, validaciones y manejo de errores.

## 🚀 Características

- Gestión de variedades de café (crear, leer, actualizar, eliminar).
- Gestión de clientes y sus datos.
- Gestión de órdenes de compra.
- Paginación y filtrado de resultados.
- Validaciones de datos en la API.
- Manejo robusto de errores.

## 📋 Prerrequisitos.

- Java 17 o superior.
- Maven 3.6 o superio.
- Postman.
- MySQL WORKBENCH.
- IDE (Eclipse).

## 🔧 Instalación

1. Clona el repositorio:

git clone https://github.com/tu-usuario/cafeteria.git


2. Configura la base de datos:
   - Crea una base de datos MySQL workbench llamada `cafeteria`
   - Configura las credenciales en `src/main/resources/application.properties`

## ⚙️ Configuración

1. Abre el archivo `src/main/resources/application.properties` y configura:
```properties
# Configuración de la base de datos
spring.datasource.url=jdbc:mysql://localhost:3306/cafeteria
spring.datasource.username=root
spring.datasource.password=tu_contraseña

# Configuración del servidor
server.port=8080
```

2. Ajusta los valores según tu entorno.

## 🚀 Ejecución

1. Ejecuta la aplicación:
```bash
mvn spring-boot:run
```

2. La aplicación estará disponible en: `http://localhost:8080`

## 📚 Endpoints de la API

### Cafés
- `GET /coffee` - Obtiene todas las variedades de café.
- `GET /coffee/{id}` - Obtiene un café específico
- `POST /coffee` - Crea una nueva variedad de café
- `PUT /coffee/{id}` - Actualiza un café existente
- `PATCH /coffee/{id}` - Actualiza parcialmente un café
- `DELETE /coffee/{id}` - Elimina un café

### Clientes
- `GET /customer` - Obtiene todos los clientes
- `GET /customer/{id}` - Obtiene un cliente específico
- `POST /customer` - Crea un nuevo cliente
- `PUT /customer/{id}` - Actualiza un cliente existente
- `DELETE /customer/{id}` - Elimina un cliente

### Órdenes
- `GET /order` - Obtiene todas las órdenes
- `GET /order/{id}` - Obtiene una orden específica
- `POST /order` - Crea una nueva orden
- `PUT /order/{id}` - Actualiza una orden existente
- `DELETE /order/{id}` - Elimina una orden

## 📦 Estructura del Proyecto

```
src/main/java/com/gammatech/cafeteria/
├── controller/     # Controladores REST
├── model/         # Entidades JPA
├── repository/    # Repositorios de datos
└── service/       # Lógica de negocio
```

## 🛠️ Tecnologías Utilizadas

- Java 17
- Spring Boot
- Spring Data JPA
- MySQL workbench
- Maven
- Javadoc

## 📝 Ejemplo de Uso

## 🤝 Contribución

1. Haz un Fork del proyecto
2. Crea una rama para tu feature (`git checkout -b feature/AmazingFeature`)
3. Commit tus cambios (`git commit -m 'Add some AmazingFeature'`)
4. Push a la rama (`git push origin feature/AmazingFeature`)
5. Abre un Pull Request

## ✨ Autor

* **Marcos Sandín Fernandez** - *Trabajo Inicial* - [Gammatech](https://github.com/gammatech)