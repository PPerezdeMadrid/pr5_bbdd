# Práctica 5 de la asignatura Base de Datos II
## Objetivos de la práctica
El objetivo de esta práctica es que los alumnos aprendan a optimizar consultas SQL lanzadas contra un 
servidor MySQL. En particular se tratará el proceso de inserción o carga masiva de datos. Dichos 
procesos de optimización deberán realizarse tanto desde la consola de MySQL como desde una 
aplicación Java desarrollada al efecto. Los alumnos tendrán que medir tiempos de ejecución antes y 
después de cada optimización, y justificar razonadamente los resultados obtenidos. Asimismo, en esta 
práctica, los alumnos tendrán que poner en práctica los conocimientos adquiridos sobre copias de 
seguridad (backups) y replicación de bases de datos aprendidos en temas anteriores.

### Enunciado de la práctica
Esta práctica se compone de tres apartados principales: la búsqueda de una base de datos con un 
volumen de datos grande, la optimización de la carga de datos en la base de datos desde la consola 
de MySQL, y la optimización de la carga desde una aplicación Java.
Los apartados son los siguientes:
#### 1. Instalación de una base de datos grande. Para ello, primero se debe buscar en Internet
una base de datos cuyo contenido de alguna tabla (con la que se trabajará en los siguientes 
apartados) sea suficientemente grande (más de 500.000 de registros). Dicha base de datos 
debe importarse en un servidor MySQL.
#### 2. Carga de datos desde MySQL. Una vez cargada la base de datos en el servidor, se deberán 
llevar a cabo las siguientes acciones:
a. Identificar y seleccionar la tabla de trabajo. Aunque la base de datos tenga muchas 
tablas, para esta práctica se deberá seleccionar una tabla cuyo volumen de datos sea 
grande.

b. Duplicar la tabla en una nueva base de datos. Se deberá crear una nueva base de 
datos de trabajo que contenga únicamente la estructura de la tabla seleccionada 
anteriormente.

c. Cargar los datos en la tabla creada. El objetivo en este apartado consiste en comparar 
objetivamente diferentes métodos de importación/carga de datos en una base de datos. Se 
deberán probar 3 métodos:
    i. Importar los datos desde un fichero tabulado, que contenga los datos de la tabla 
    original.
    ii. Importar los datos obtenidos con mysqldump al volcar los datos de la tabla original, 
    utilizando las opciones por defecto del comando. 
    iii. Importar los datos obtenidos con mysqldump mediante la opción --skip-extendedinsert, al volcar los datos de la tabla original.

Para cada uno de los métodos, se deberán medir los tiempos de carga de los datos y 
realizar un análisis comparativo de los mismos, justificando razonadamente los 
resultados obtenidos.
Finalmente, para el método más costoso en tiempo se pide realizar una optimización 
de la carga, proponiendo las medidas que se estimen oportunas para obtener un tiempo 
de carga inferior al original y comparable con los otros procesos de carga. Después de 
realizar la optimización, importar nuevamente el conjunto de datos optimizado, 
medir tiempos de carga y volver a realizar el análisis comparativo con los métodos 
anteriores, justificando razonadamente los resultados obtenidos.
Nota importante: los alumnos deberán asegurarse de utilizar el motor InnoDB para las tablas 
sobre las que realicen la importación de datos, tanto en el apartado 2 como en el apartado 3.
#### 3. Carga desde una aplicación Java. 
En este apartado se pide desarrollar una aplicación Java que se conecte a un servidor MySQL
para realizar la carga de datos en una tabla. Se deberá utilizar la misma tabla y datos utilizados 
en el apartado 2.
La aplicación deberá leer los datos de un fichero de texto. Este fichero contendrá los datos 
de manera tabulada. Se deberá utilizar el mismo fichero utilizado en el apartado 2.c.i. En un 
primer proceso de carga, se deberá hacer un INSERT por cada una de las líneas leídas del 
fichero. La aplicación deberá medir el tiempo empleado en completar la carga de datos.
A partir de los resultados obtenidos, se pide optimizar el proceso de carga aplicando todas 
las medidas de optimización que se consideren oportunas para intentar reducir el tiempo 
de carga lo más posible. Una vez implementadas las optimizaciones, volver a medir el tiempo 
de carga.
Finalmente, realizar un análisis comparativo de los tiempos de carga obtenidos antes y 
después de la optimización, justificando razonadamente los resultados obtenidos.
#### 4. Comparar los resultados obtenidos desde consola y desde la aplicación. Realizar un 
análisis crítico de los resultados obtenidos en cada caso y justificar razonadamente dichos 
resultados
