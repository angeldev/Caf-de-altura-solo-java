# ☕ Evaluación del proyecto de Marcos

## 🧱 1. Estructura del proyecto y arquitectura por capas
- ✅ Separación clara en capas (Controller, Service, Repository, Entity)
- ✅ Lógica de negocio correctamente ubicada en la capa de servicio
- ✅ No se mezcla acceso a datos ni lógica de presentación
- Comentario: Excelente separación por paquetes. Las responsabilidades 
están bien distribuidas y respetan la arquitectura en capas. Como punto 
extra para hilar más fino, métodos de validación como el que tienes en 
el OrderService de validateOrder, la lógica puedes incorporarla dentro 
de la Clase Order y que ella misma se valide. Por ejemplo, en el constructor 
puedes validar atributo a atributo antes de contruir el objeto.

## 🧩 2. Spring Core – Inyección de dependencias
- ✅ Se evita el uso de `new` para crear dependencias
- ✅ Uso de inyección de dependencias (por constructor o con `@Autowired`)
- ✅ Uso adecuado de `@Component`, `@Service`, `@Repository`
- Comentario: Buen uso de anotaciones estereotípicas y constructor injection.

## 🗃️ 3. Persistencia con JPA
- ✅ Entidades bien definidas y anotadas (`@Entity`, `@Id`, `@Column`)
- ✅ Relaciones modeladas correctamente (`@OneToMany`, `@ManyToOne`, etc.)
- ✅ Consultas por nombre de método (`findByTipo`, etc.)
- ✅ Separación lógica entre repositorio y servicio
- Comentario: Las entidades están completas y con relaciones bien modeladas 
(por ejemplo, Order y OrderItem). Muy correcto uso de Spring Data JPA.

## 🛢️ 4. Base de datos
- ✅ Configuración correcta en application.properties
- ✅ Conexión establecida con MySQL y persistencia de datos funcional mediante JPA/Hibernate
- Comentario: La configuración en application.properties está bien definida 
y se puede establecer conexión con la base de datos MySQL. Las entidades 
se persisten correctamente mediante JPA.    

## 🌐 5. Spring Web / REST
- 🟧 Endpoints REST bien definidos y nombrados
- ✅ Uso correcto de @GetMapping, @PostMapping, etc.
- ✅ Uso adecuado de @PathVariable, @RequestBody, @RequestParam
- Comentario: Los endpoints están bien diseñados y hacen uso correcto de 
 las anotaciones de Spring Web. Sin embargo, la convención REST recomienda 
 usar nombres en plural para los recursos (por ejemplo, /customers en lugar de /customer),
 lo cual no se ha seguido en este proyecto. También se puede mejorar incorporando 
 validación de entradas y manejo más robusto de errores HTTP.

## 🔐 6. Spring Security
- [ ] Autenticación implementada (por ejemplo, básica o JWT)
- [ ] Rutas protegidas según roles o permisos
- [ ] Configuración clara (`SecurityFilterChain`, filtros, etc.)
- Comentario:

## 🧪 7. Testing
- [ ] Uso de JUnit y Spring Boot Test
- [ ] Pruebas de servicios, repositorios o controladores
- [ ] Casos de éxito y error cubiertos
- Comentario:

## 🧼 8. Buenas prácticas y limpieza de código
- ✅ Nombres claros y expresivos
- ✅ Código sin duplicación ni clases innecesarias
- 🟧 Validaciones, manejo de errores, uso correcto de Optional
- Comentario: Buen estilo en general. No se detecta duplicación innecesaria
y los nombres son claros. Sin embargo, el manejo de errores puede mejorar. 
Por ejemplo, los mensajes que devuelve la API son poco informativos, 
como "Café no encontrado", sin detalles adicionales sobre qué dato falló 
o cómo resolverlo. Además, aunque se ha implementado una gestión centralizada 
de excepciones, se hace uso únicamente de RuntimeException. Sería más claro y 
profesional definir excepciones específicas como OrderNotFoundException, 
:qCoffeeNotFoundException, etc., lo que facilita la mantenibilidad y el entendimiento del código.


🎁 9. Extras (no obligatorios, pero suman)
- ❌ Uso de DTOs
- ❌ Swagger / documentación de la API
- ❌ Buen uso de Git (commits claros, ramas, etc.)
- ✅ Inclusión de un README.md claro con instrucciones de ejecución
- Comentario: El proyecto no utiliza DTOs (Data Transfer Objects), que son 
objetos intermedios entre la capa de presentación (controladores) y el modelo 
de dominio. Su uso ayuda a desacoplar la representación interna de los datos 
de cómo se exponen a través de la API, lo que permite mayor flexibilidad, seguridad 
y control del contenido que se devuelve.
Tampoco se ha incluido Swagger u otra forma de documentación automática 
de la API, lo cual sería muy útil para entender y probar los endpoints disponibles.
En cuanto a Git, aunque el repositorio está inicializado, no hay histórico de commits. 
Es recomendable realizar commits frecuentes, con mensajes claros que describan 
los cambios realizados, lo que permite tener trazabilidad del desarrollo y facilita el trabajo colaborativo.

---

## 📊 Nota orientativa
- Comentario general: Has hecho un muy buen trabajo estructurando el 
proyecto siguiendo la arquitectura en capas. La lógica está bien organizada, 
el uso de JPA es correcto y los endpoints funcionan de forma clara y coherente. 
Aun así, hay varios aspectos que podrías mejorar para dar un salto hacia un nivel 
más profesional.
  Por ejemplo, sería recomendable seguir las convenciones REST utilizando 
nombres en plural para los recursos. También podrías trabajar en el manejo 
de errores, ofreciendo mensajes más informativos y definiendo excepciones 
específicas en lugar de usar siempre RuntimeException. Incorporar DTOs te 
permitiría desacoplar mejor la lógica interna del modelo respecto a la capa web, 
y te daría más control sobre lo que se expone en la API.
  Además, faltan tests automatizados, que son fundamentales para garantizar el 
correcto funcionamiento del sistema a medida que crece. Y en cuanto al uso de Git, 
sería ideal mantener un historial de commits claro y ordenado, registrando los 
cambios de forma progresiva.
  En resumen, se nota que dominas la base técnica y que tienes potencial para mejorar 
aún más con buenas prácticas que marcan la diferencia en proyectos reales.
