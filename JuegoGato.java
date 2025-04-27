import java.util.Scanner;
import java.util.Random;

public class JuegoGato {
    // Constantes del juego
    private static final int FILAS = 3;
    private static final int COLUMNAS = 3;
    private static final char JUGADOR = 'X';
    private static final char BOT = 'O';
    private static final char VACIO = ' ';

    // Clase para el estado del juego
    static class EstadoJuego {
        char[][] tablero;
        char ganador;
        boolean turnoJugador;
        boolean juegoTerminado;

        public EstadoJuego() {
            tablero = new char[FILAS][COLUMNAS];
            inicializar();
        }

        // Inicializa el tablero y variables del juego
        public void inicializar() {
            for (int i = 0; i < FILAS; i++) {
                for (int j = 0; j < COLUMNAS; j++) {
                    tablero[i][j] = VACIO;
                }
            }
            ganador = VACIO;
            turnoJugador = true;
            juegoTerminado = false;
        }
    }

    // Dibujar el tablero en la consola
    public static void dibujarTablero(EstadoJuego estado) {
        System.out.println("\n  0 1 2");
        for (int i = 0; i < FILAS; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < COLUMNAS; j++) {
                System.out.print(estado.tablero[i][j]);
                if (j < COLUMNAS - 1) {
                    System.out.print("|");
                }
            }
            System.out.println();
            if (i < FILAS - 1) {
                System.out.println("  -+-+-");
            }
        }
        System.out.println();
    }

    // Verifica si hay un ganador
    public static char verificarGanador(char[][] tablero) {
        // Verificar filas
        for (int i = 0; i < FILAS; i++) {
            if (tablero[i][0] != VACIO && 
                tablero[i][0] == tablero[i][1] && 
                tablero[i][1] == tablero[i][2]) {
                return tablero[i][0];
            }
        }
        
        // Verificar columnas
        for (int i = 0; i < COLUMNAS; i++) {
            if (tablero[0][i] != VACIO && 
                tablero[0][i] == tablero[1][i] && 
                tablero[1][i] == tablero[2][i]) {
                return tablero[0][i];
            }
        }
        
        // Verificar diagonales
        if (tablero[0][0] != VACIO && 
            tablero[0][0] == tablero[1][1] && 
            tablero[1][1] == tablero[2][2]) {
            return tablero[0][0];
        }
        
        if (tablero[0][2] != VACIO && 
            tablero[0][2] == tablero[1][1] && 
            tablero[1][1] == tablero[2][0]) {
            return tablero[0][2];
        }
        
        return VACIO;
    }

    // Verificar si hay empate
    public static boolean esEmpate(char[][] tablero) {
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                if (tablero[i][j] == VACIO) {
                    return false;
                }
            }
        }
        return true;
    }

    // BOT mejorado que intenta ganar o bloquear
    public static void movimientoBot(char[][] tablero) {
        // Primero intentamos ganar en un solo movimiento
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                if (tablero[i][j] == VACIO) {
                    // Probamos este movimiento
                    tablero[i][j] = BOT;
                    if (verificarGanador(tablero) == BOT) {
                        // Movimiento ganador
                        return;
                    }
                    // Deshacemos el movimiento
                    tablero[i][j] = VACIO;
                }
            }
        }
        
        // Si no podemos ganar, intentamos bloquear al jugador
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                if (tablero[i][j] == VACIO) {
                    // Probamos este movimiento para el jugador
                    tablero[i][j] = JUGADOR;
                    if (verificarGanador(tablero) == JUGADOR) {
                        // El jugador ganaria asi que  bloqueamos
                        tablero[i][j] = BOT;
                        return;
                    }
                    // Deshacemos el movimiento
                    tablero[i][j] = VACIO;
                }
            }
        }
        
        // Si no hay movimientos, tomamos el centro si esta libre
        if (tablero[1][1] == VACIO) {
            tablero[1][1] = BOT;
            return;
        }
        
        // Si no, tomamos una esquina si esta libre
        int[][] esquinas = {{0, 0}, {0, 2}, {2, 0}, {2, 2}};
        int esquinasLibres = 0;
        
        // Contamos las esquinas libres
        for (int i = 0; i < 4; i++) {
            int fila = esquinas[i][0];
            int col = esquinas[i][1];
            if (tablero[fila][col] == VACIO) {
                esquinasLibres++;
            }
        }
        
        if (esquinasLibres > 0) {
            // Elegimos una esquina aleatoria
            Random rand = new Random();
            int esquinaAleatoria = rand.nextInt(esquinasLibres);
            int contador = 0;
            
            for (int i = 0; i < 4; i++) {
                int fila = esquinas[i][0];
                int col = esquinas[i][1];
                if (tablero[fila][col] == VACIO) {
                    if (contador == esquinaAleatoria) {
                        tablero[fila][col] = BOT;
                        return;
                    }
                    contador++;
                }
            }
        }
        
        // Si todo lo anterior falla, hacemos un movimiento aleatorio
        Random rand = new Random();
        while (true) {
            int fila = rand.nextInt(FILAS);
            int columna = rand.nextInt(COLUMNAS);
            if (tablero[fila][columna] == VACIO) {
                tablero[fila][columna] = BOT;
                return;
            }
        }
    }

    // Mostrar mensaje al finalizar el juego
    public static void mostrarMensajeFinal(char ganador) {
        System.out.println("=== FIN DEL JUEGO ===");
        
        if (ganador == JUGADOR) {
            System.out.println("¡Felicidades! Has ganado.");
        } else if (ganador == BOT) {
            System.out.println("Has perdido. ¡Mejor suerte la proxima vez!");
        } else {
            System.out.println("Empate. ¡Buen juego!");
        }
    }

    // Obtener una entrada válida del jugador
    public static int[] obtenerMovimientoJugador(Scanner scanner, char[][] tablero) {
        int[] movimiento = new int[2];
        boolean entradaValida = false;
        
        while (!entradaValida) {
            System.out.print("Ingresa tu movimiento (fila,columna): ");
            String entrada = scanner.nextLine();
            
            try {
                String[] coordenadas = entrada.split(",");
                if (coordenadas.length != 2) {
                    System.out.println("Formato invalido. Usa: fila,columna (ejemplo: 0,1)");
                    continue;
                }
                
                movimiento[0] = Integer.parseInt(coordenadas[0].trim());
                movimiento[1] = Integer.parseInt(coordenadas[1].trim());
                
                // Verificar si está dentro del rango
                if (movimiento[0] < 0 || movimiento[0] >= FILAS || 
                    movimiento[1] < 0 || movimiento[1] >= COLUMNAS) {
                    System.out.println("Movimiento fuera del tablero. Intenta nuevamente.");
                    continue;
                }
                
                // Verificar si la celda está vacía
                if (tablero[movimiento[0]][movimiento[1]] != VACIO) {
                    System.out.println("Esa celda ya esta ocupada. Intenta nuevamente.");
                    continue;
                }
                
                entradaValida = true;
                
            } catch (NumberFormatException e) {
                System.out.println("Entrada invalida. Ingresa números para fila y columna.");
            }
        }
        
        return movimiento;
    }

    // Preguntar si quiere jugar de nuevo
    public static boolean preguntarJugarDeNuevo(Scanner scanner) {
        while (true) {
            System.out.print("¿Quieres jugar otra vez? (s/n): ");
            String respuesta = scanner.nextLine().trim().toLowerCase();
            
            if (respuesta.equals("s") || respuesta.equals("si")) {
                return true;
            } else if (respuesta.equals("n") || respuesta.equals("no")) {
                return false;
            } else {
                System.out.println("Respuesta no valida. Por favor, ingresa 's' o 'n'.");
            }
        }
    }

    // Método principal
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EstadoJuego estado = new EstadoJuego();
        boolean jugarDeNuevo = true;
        
        System.out.println("=== JUEGO DE GATO (TIC-TAC-TOE) ===");
        System.out.println("Tú eres 'X', el BOT es 'O'");
        System.out.println("Para jugar, ingresa las coordenadas de tu movimiento como 'fila,columna'");
        System.out.println("Las filas y columnas van de 0 a 2");
        
        while (jugarDeNuevo) {
            estado.inicializar();
            
            // Bucle principal del juego
            while (!estado.juegoTerminado) {
                dibujarTablero(estado);
                
                // Turno del jugador
                if (estado.turnoJugador) {
                    System.out.println("Tu turno");
                    int[] movimiento = obtenerMovimientoJugador(scanner, estado.tablero);
                    estado.tablero[movimiento[0]][movimiento[1]] = JUGADOR;
                    estado.turnoJugador = false;
                } 
                // Turno del bot
                else {
                    System.out.println("Turno del BOT...");
                    try {
                        // Simular tiempo de "pensamiento"
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    movimientoBot(estado.tablero);
                    estado.turnoJugador = true;
                }
                
                // Verificar si hay ganador
                estado.ganador = verificarGanador(estado.tablero);
                if (estado.ganador != VACIO || esEmpate(estado.tablero)) {
                    estado.juegoTerminado = true;
                }
            }
            
            // Mostrar el tablero final
            dibujarTablero(estado);
            
            // Mostrar mensaje final y preguntar si quiere jugar de nuevo
            mostrarMensajeFinal(estado.ganador);
            jugarDeNuevo = preguntarJugarDeNuevo(scanner);
        }
        
        System.out.println("¡Gracias por jugar!");
        scanner.close();
    }
}