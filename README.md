# springboot-xmen-mutant

## Descripción del problema
 
Magneto quiere reclutar la mayor cantidad de mutantes para poder luchar contra los X-Men.

Nos ha contratado para desarrollar un proyecto que detecte si un humano es mutante basándose en su secuencia de ADN.

Para eso ha pedido crear un programa con un método o función con la siguiente firma:

```
boolean isMutant(String[] dna); // Ejemplo Java
```

Reglas: 
1. Se recibirá como parámetro un array de Strings que representan cada fila de una tabla de (NxN) con la secuencia del ADN. 
2. Las letras de los Strings solo pueden ser: (A,T,C,G), las cuales representa cada base nitrogenada del ADN.

Sabrás si un humano es mutante, si encuentras más de una secuencia de cuatro letras iguales​, de forma oblicua, horizontal o vertical.

Ejemplo (Caso mutante):

String[] dna = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};
En este caso el llamado a la función isMutant(dna) devuelve "true". 

Desarrolla el algoritmo de la manera más eficiente posible.


## Prerequisites

* Java 11.
* MySQL
* git command line tool (https://help.github.com/articles/set-up-git)
* Maven

### Pasos:

1. Creación de la base de datos, tabla y Trigger en MySQL
```

create database xmen;

CREATE TABLE `tb_analisis` (
  `id_analisis` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `ds_dna_code` varchar(40) DEFAULT NULL,
  `ds_dna` mediumtext,
  `fl_mutant` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id_analisis`),
  UNIQUE KEY `unq_code` (`ds_dna_code`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;


DELIMITER $$
USE `xmen`$$
CREATE DEFINER = CURRENT_USER TRIGGER `xmen`.`tb_analisis_BEFORE_INSERT` BEFORE INSERT ON `tb_analisis` FOR EACH ROW
BEGIN
	SET NEW.ds_dna_code = SHA1(NEW.ds_dna);
END$$
DELIMITER ;

```

2. Descargar el proyecto Git con la  linea de comandos:
```
git clone https://github.com/davidmaurellor/springboot-xmen-mutant
```

3. Editar el archivo de propiedades: springboot-xmen-mutant/src/main/resources/application.properties y cambiar los parámetros: {HOST}, {USER} y {PASSWORD}
```
...
spring.datasource.url=jdbc:mysql://{HOST}/xmen?useSSL=false
spring.datasource.username={USER}
spring.datasource.password={PASSWORD}
...
```

4. Ejecutar el proyecto
```
cd springboot-xmen-mutant
mvn package
java -jar target/springboot-xmen-mutant-0.0.1-SNAPSHOT.jar
```

5. Acceder a la URL
```
METODO GET:
http://localhost:8091/stats
```

```
METODO POST con BODY:
http://localhost:8091/mutant/

{
"dna":["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"]
}
```

## Demo

Actualmente se encuentra disponible el proyecto para test así:

1. Consulta vía GET de Estadísticas de DNA analizados de humanos:
```
http://ec2-52-24-72-9.us-west-2.compute.amazonaws.com:8080/stats
```

2. Analisis de DNA, invocando mediante POST la siguiente URL y en BODY enviar el JSON siguiente:
* POST: 
```
http://ec2-52-24-72-9.us-west-2.compute.amazonaws.com:8080/mutant/
```

* BODY:
```
{
"dna":["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"]
}
```



