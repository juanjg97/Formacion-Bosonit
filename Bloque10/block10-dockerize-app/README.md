### Para crear el .jar
- 1.- En IntelliJ ir al extremo superior derecho,desplegar la ventana de maven
- 2.- Ir al módulo block10-dockerize-app, en la pestaña Lifecycle y luego dar doble click en install

### Para ejecutar el programa
- 1.- Situarse en la carpeta src del proyecto y ejecutar con la terminal docker compose up
- 2.- Ir a docker desktop para ver los contenedores y dar click en la url de PGADMIN
- 3.- El usuario es juan@mail.com y la contraseña es admin
- 4.- Crear un servidor 
  - En la pestaña general poner "Servidor1", por ejemplo
  - En la pestaña conexión:
    - Host name / address: postgres_container
    - Port: 5432
    - Maintance database: postgres
    - Username: juan
    - Password: root
    - Finalmente dar click en save.
    - 
Ahora se puede ir a la BDD postgres y ahí aparecera la tabla persona
después se pueden realizar pruebas en POSTMAN usando el archivo json que está en
la carpeta del proyecto


