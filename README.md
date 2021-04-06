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