# Juego de Gato (Tic-Tac-Toe) en Java

Un simple juego de Gato (Tic-Tac-Toe) implementado en Java que se ejecuta en la consola de comandos.

## Descripción

Este proyecto es una implementación del clásico juego de Gato (también conocido como Tres en Raya o Tic-Tac-Toe) para ser jugado en la consola de comandos. El juego permite a un jugador humano competir contra un BOT básica pero estratégica.

## Características

- Tablero de juego 3x3 representado en consola
- Interfaz de texto simple e intuitiva
- BOT con varios niveles de estrategia:
  - Busca movimientos ganadores
  - Bloquea al jugador cuando está a punto de ganar
  - Prefiere posiciones estratégicas (centro, esquinas)
- Sistema de validación de entrada del usuario
- Indicador de turno actual
- Detección de ganador y empates
- Opción para jugar múltiples partidas

## Requisitos

- Java JDK 8 o superior
- Terminal o consola de comandos

## Cómo compilar y ejecutar

1. Compila el archivo Java:
```bash
javac JuegoGato.java
```

2. Ejecuta el programa:
```bash
java JuegoGato
```

## Cómo jugar

1. El tablero se muestra con coordenadas numeradas del 0 al 2 para filas y columnas.
2. Cuando sea tu turno, ingresa tus movimientos en formato "fila,columna".
   - Por ejemplo: `1,2` colocará tu marca ('X') en la fila 1, columna 2.
3. La computadora ('O') tomará su turno automáticamente.
4. El juego termina cuando alguien forma una línea de tres símbolos iguales o cuando se llena el tablero.
5. Al finalizar, puedes elegir si jugar otra partida.


## Estructura del código

- `JuegoGato.java`: Contiene toda la lógica del juego, incluyendo:
  - Clase `EstadoJuego`: Mantiene el estado actual del tablero
  - Métodos para mostrar el tablero y validar entradas
  - Algoritmo de IA para la computadora
  - Sistema de detección de ganador

## Contribuciones

Las contribuciones son bienvenidas. Algunas posibles mejoras:
- Añadir diferentes niveles de dificultad
- Implementar un modo multijugador
- Añadir estadísticas de juego
- Crear una interfaz gráfica
