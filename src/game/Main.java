import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;

/**
 * La clase {@code Main} implementa un juego de Blackjack jugable desde la consola.
 * <p>
 * Todo el flujo del juego, la creación del mazo, las reglas de puntuación y 
 * la inteligencia básica del crupier están contenidos de manera secuencial 
 * dentro del método principal para cumplir con el requerimiento de diseño de un solo método.
 * </p>
 *
 * @author Tu Nombre / Code
 * @version 1.1 (Compatible con Java 8+)
 */
public class Main {

    /**
     * Punto de entrada principal de la aplicación.
     * Ejecuta el bucle principal del juego de Blackjack, gestionando la entrada
     * del usuario y evaluando las condiciones de victoria o derrota.
     *
     * @param args Argumentos de la línea de comandos (no se utilizan en esta aplicación).
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        /*
         * Función Lambda para calcular el puntaje de una mano.
         * Toma una lista de strings (las cartas) y devuelve el valor entero total.
         * Maneja la lógica especial del As (vale 11, pero se reduce a 1 si el puntaje supera 21).
         */
        Function<List<String>, Integer> calcularPuntaje = (mano) -> {
            int total = 0;
            int ases = 0;
            
            for (String carta : mano) {
                // Se extrae el valor de la carta ignorando el último carácter (el palo)
                String valor = carta.substring(0, carta.length() - 1);
                
                switch (valor) {
                    case "J":
                    case "Q":
                    case "K":
                        total += 10;
                        break;
                    case "A":
                        total += 11;
                        ases++;
                        break;
                    default:
                        total += Integer.parseInt(valor);
                        break;
                }
            }
            
            // Reducción del valor del As de 11 a 1 si el jugador se pasa de 21
            while (total > 21 && ases > 0) {
                total -= 10;
                ases--;
            }
            return total;
        };

        System.out.println("=========================================");
        System.out.println("♠♥♣♦ BIENVENIDO AL BLACKJACK EN JAVA ♦♣♥♠");
        System.out.println("=========================================");

        boolean jugarDeNuevo = true;

        while (jugarDeNuevo) {
            // 1. CONSTRUCCIÓN Y MEZCLA DEL MAZO
            String[] palos = {"♥", "♦", "♣", "♠"};
            String[] valores = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
            List<String> mazo = new ArrayList<>();
            
            for (String palo : palos) {
                for (String valor : valores) {
                    mazo.add(valor + palo);
                }
            }
            Collections.shuffle(mazo); // Barajamos el mazo de forma aleatoria

            // 2. INICIALIZACIÓN DE MANOS
            List<String> manoJugador = new ArrayList<>();
            List<String> manoCrupier = new ArrayList<>();

            // Se reparten las 2 primeras cartas alternando entre jugador y crupier
            manoJugador.add(mazo.remove(mazo.size() - 1));
            manoCrupier.add(mazo.remove(mazo.size() - 1));
            manoJugador.add(mazo.remove(mazo.size() - 1));
            manoCrupier.add(mazo.remove(mazo.size() - 1));

            boolean turnoJugador = true;

            // 3. CICLO DE TURNO DEL JUGADOR
            while (turnoJugador) {
                int puntajeJugador = calcularPuntaje.apply(manoJugador);
                
                System.out.println("\n--- TU TURNO ---");
                System.out.println("Tu mano: " + manoJugador + " (Puntaje: " + puntajeJugador + ")");
                System.out.println("Mano del Crupier: [" + manoCrupier.get(0) + ", [Oculta]]");

                // Verificación de derrota automática ("Busto")
                if (puntajeJugador > 21) {
                    System.out.println("¡Busto! Te has pasado de 21. Pierdes esta ronda.");
                    turnoJugador = false;
                    break; 
                }

                System.out.print("¿Deseas (P)edir carta o (M)antenerte?: ");
                String accion = scanner.nextLine().trim().toUpperCase();

                if (accion.equals("P")) {
                    manoJugador.add(mazo.remove(mazo.size() - 1));
                } else if (accion.equals("M")) {
                    turnoJugador = false; // El jugador decide plantarse
                } else {
                    System.out.println("Entrada no reconocida. Ingresa 'P' o 'M'.");
                }
            }

            int puntajeFinalJugador = calcularPuntaje.apply(manoJugador);

            // 4. TURNO DEL CRUPIER (Solo se ejecuta si el jugador no hizo "Busto")
            if (puntajeFinalJugador <= 21) {
                System.out.println("\n--- TURNO DEL CRUPIER ---");
                int puntajeCrupier = calcularPuntaje.apply(manoCrupier);
                System.out.println("Mano inicial del Crupier: " + manoCrupier + " (Puntaje: " + puntajeCrupier + ")");

                // Regla del casino: El crupier está obligado a pedir carta si tiene menos de 17
                while (puntajeCrupier < 17) {
                    System.out.println("El Crupier pide una carta...");
                    manoCrupier.add(mazo.remove(mazo.size() - 1));
                    puntajeCrupier = calcularPuntaje.apply(manoCrupier);
                    System.out.println("Mano del Crupier: " + manoCrupier + " (Puntaje: " + puntajeCrupier + ")");
                }

                // 5. EVALUACIÓN DE RESULTADOS
                System.out.println("\n================ RESULTADO ================");
                if (puntajeCrupier > 21) {
                    System.out.println("¡El Crupier se pasó de 21! ¡TÚ GANAS!");
                } else if (puntajeFinalJugador > puntajeCrupier) {
                    System.out.println("¡TÚ GANAS! Tienes un puntaje mayor al del Crupier.");
                } else if (puntajeCrupier > puntajeFinalJugador) {
                    System.out.println("EL CRUPIER GANA. Mejor suerte la próxima vez.");
                } else {
                    System.out.println("¡ES UN EMPATE!");
                }
                System.out.println("===========================================");
            }

            // 6. GESTIÓN DE NUEVA PARTIDA
            System.out.print("\n¿Quieres jugar otra partida? (S/N): ");
            String respuesta = scanner.nextLine().trim().toUpperCase();
            if (!respuesta.equals("S")) {
                jugarDeNuevo = false;
                System.out.println("¡Gracias por jugar! Cerrando mesa...");
            }
        }
        
        scanner.close(); // Se libera el recurso del escáner
    }
}