# EquilibriumIndex
La aplicación implementa todas las características:

•	Aplicación basada en Spring Boot
•	Tener un endpoint de servicio REST que pasándole dicho arraya, devuelva en JSON la respuesta: Fecha de solicitud, array solicitado e índices encontrado 
•	Configurar Hibernate + Spring Data JPA 
•	Persistir los resultados de cada consulta en una base de datos en memoria: H2
o	Se guardaría fecha de solicitud, array e índice encontrado
•	Endpoint REST para obtener todas las consultas de índice de array
•	Añadir Tests unitarios
•	Tests de integración

Rutas API REST:

http://localhost:8080/api/get/all
http://localhost:8080/api/get/equilibriumindex
http://localhost:8080/api/equilibriumindex/find/{param}

http://localhost:8080/h2-console
url=jdbc:h2:mem:testdb
driverClassName=org.h2.Driver
username=sa
password=password

Ejemplo:
http://localhost:8080/json/equilibrium/-7,1,5,2,-4,3,0:0,-4,7,-4,-2,6,-3,0

Rutas API Pruebas rendimiento e integración:

http://localhost:8080/json/equilibrium/{param}
http://localhost:8080/json/debug/loadtesting/{arraySize}
http://localhost:8080/json/debug/loadtestmaxarrays/{arraysNumber} 
http://localhost:8080/json/debug/loadtestingOnebyOne/{arraysNumber}
