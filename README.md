
## Integrantes

Juan Sebastián Díaz Rodríguez<br>
Fabián René García Perdomo<br>
Stiven Gonzalez Olaya<br>
Andrés Felipe Garzón Barreto<br>
Andrés Felipe Ariza Londoño<br>

---

# Blackjack en Java 

## Descripción del proyecto

Este proyecto corresponde a una implementación del clásico juego de cartas **Blackjack** (también conocido como **21**) desarrollada en **Java** para ejecutarse por consola.

El sistema permite que un jugador humano se enfrente al "Crupier" (controlado por el sistema), respetando las reglas clásicas del casino. Además, incluye la mezcla aleatoria de un mazo de 52 cartas, cálculo dinámico del valor del As (1 u 11), detección automática de victoria, derrota por "busto" (pasarse de 21), empate y la opción de jugar múltiples rondas continuas.

La solución fue construida en una única clase `Main`, manteniendo buenas prácticas de desarrollo de software como:

- separación de responsabilidades mediante bloques lógicos y funciones Lambda,
- nombres descriptivos,
- validación de entradas de usuario,
- documentación con **Javadoc** y comentarios internos,
- legibilidad y mantenibilidad del código.

---

## Objetivo del juego

El objetivo del juego es sumar un valor lo más cercano posible a **21** con las cartas de tu mano, sin pasarse de esa cifra, y obtener una puntuación mayor a la del Crupier.

---

## Reglas del juego

Las reglas del juego implementadas son las siguientes:

1. Se utiliza un mazo estándar de **52 cartas** (sin comodines).
2. Participan dos entidades: el **Jugador** y el **Crupier**.
3. Las cartas numéricas (2-10) valen su valor nominal.
4. Las figuras (J, Q, K) valen **10 puntos** cada una.
5. El As (A) vale **11 puntos**, pero si este valor hace que el puntaje total supere los 21, su valor se reduce automáticamente a **1 punto**.
6. Ambos reciben dos cartas al inicio; el jugador puede ver ambas cartas, pero una carta del Crupier permanece oculta.
7. En su turno, el jugador puede elegir **(P)edir** una nueva carta o **(M)antenerse** con su mano actual.
8. Si el jugador supera los 21 puntos, pierde automáticamente la ronda (Busto).
9. El Crupier está obligado por las reglas del casino a pedir cartas hasta que su puntaje sea de al menos **17**.
10. Gana quien tenga el puntaje más alto sin pasarse de 21. Si tienen el mismo puntaje, es un empate.

---

## Características implementadas

El programa incluye las siguientes funcionalidades:

- generación y barajado de un mazo completo de 52 cartas,
- reparto alterno de cartas iniciales,
- cálculo inteligente del puntaje de la mano (gestión del As),
- visualización dinámica de las manos en consola,
- ocultamiento de la segunda carta del Crupier durante el turno del jugador,
- ingreso de decisiones por parte del jugador (Pedir/Mantenerse),
- validación de entradas por teclado,
- turno automatizado del Crupier según reglas estrictas,
- detección de victoria, derrota y empate,
- reinicio de partidas para juego continuo.

---

## Requisitos técnicos

Para ejecutar este proyecto se requiere:

- **Java Development Kit (JDK) 8** o superior (código adaptado para máxima compatibilidad),
- consola o terminal del sistema operativo,
- compilador `javac`,
- intérprete `java`.

Puedes verificar la instalación de Java con los siguientes comandos:

```bash
javac -version
java -version
