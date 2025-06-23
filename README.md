
# PROYECTO: Ciudad de los Niños – Sistema de Donaciones

## Tecnologías
- Java 17 
- PostgreSQL 14
- Maven
- JDBC
- JavaFX


## Pasos para configurar la base de datos

1. Crear la base de datos, ejecutamos en psql (o utilizando el cliente pgAdmin):
```psql
psql -U postgres -h localhost -d ciudaddelosninos -f sql_script/01-esquema.sql
```
2. Crear las tablas, relaciones y triggers, ejecutamos el script 01-esquema.sql con:
```psql
psql -U postgres -h localhost -d ciudaddelosninos -f sql_script/02-basededatos.sql
```
3. Insertar datos de prueba, ejecutamos el script 02-datos.sql:
```psql
psql -U postgres -h localhost -d ciudaddelosninos -f sql_script/03-datos.sql
```
## Compilación del proyecto

1. Compilar el código Java:

En Windows:
```powershell
$files = Get-ChildItem -Recurse -Filter *.java -Path src | ForEach-Object { $_.FullName }
javac -cp "src\main\java\postgresql-42.7.5.jar" -d out $files
```
En Linux:
```bash
javac -cp src/main/java/postgresql-42.7.5.jar -d out $(find src -type f -name "*.java")
```

## Ejecución del programa

En Windows:
```powershell
java -cp "out;src\main\java\postgresql-42.7.5.jar" CiudadDeLosNinosApp	
```
En Linux:
```bash
java -cp out:src/main/java/postgresql-42.7.5.jar CiudadDeLosNinosApp
```
