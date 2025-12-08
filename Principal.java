import java.util.Scanner;

public class Principal {
    private static ArbolBinario arbol = new ArbolBinario();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=== Concurso de Programación - Gestión de Participantes (Consola) ===");
        boolean salir = false;

        while (!salir) {
            mostrarMenu();
            int opcion = leerInt("Selecciona una opción: ");

            switch (opcion) {
                case 1:
                    agregarParticipante();
                    break;
                case 2:
                    arbol.inordenMostrarMatriculaCategoria();
                    break;
                case 3:
                    mostrarEstadisticas();
                    break;
                case 4:
                    System.out.println("Saliendo... ¡Hasta luego!");
                    salir = true;
                    break;
                default:
                    System.out.println("Opción inválida. Intenta de nuevo.");
            }
            System.out.println();
        }

        sc.close();
    }

    private static void mostrarMenu() {
        System.out.println("-------------------------------------------------");
        System.out.println("1. Agregar participante");
        System.out.println("2. Visualizar matrícula y categoría de todos los participantes (Inorden)");
        System.out.println("3. Visualizar estadísticas (promedio edad y cantidad por categoría)");
        System.out.println("4. Salir");
        System.out.println("-------------------------------------------------");
    }

    private static void agregarParticipante() {
        System.out.println("== Agregar participante ==");

        int matricula = leerInt("Matrícula (entero): ");
        // validar matrícula positiva
        if (matricula <= 0) {
            System.out.println("Matrícula inválida. Debe ser un número entero positivo.");
            return;
        }

        String categoria = leerCategoria();
        if (categoria == null) {
            System.out.println("Categoría inválida. Operación cancelada.");
            return;
        }

        int edad = leerInt("Edad (años): ");
        if (edad <= 0) {
            System.out.println("Edad inválida. Debe ser un número entero positivo.");
            return;
        }

        Persona p = new Persona(matricula, categoria, edad);
        boolean ok = arbol.insertar(p);
        if (ok) {
            System.out.println("Participante agregado correctamente.");
        } else {
            System.out.println("Error: la matrícula ya existe. No se agregó el participante.");
        }
    }

    private static String leerCategoria() {
        System.out.println("Categorías disponibles:");
        System.out.println("1. Principiante");
        System.out.println("2. Intermedio");
        System.out.println("3. Avanzado");
        int op = leerInt("Selecciona categoría (1-3): ");
        switch (op) {
            case 1:
                return "Principiante";
            case 2:
                return "Intermedio";
            case 3:
                return "Avanzado";
            default:
                return null;
        }
    }

    private static void mostrarEstadisticas() {
        System.out.println("== Estadísticas ==");
        ArbolBinario.Estadisticas est = arbol.obtenerEstadisticas();
        System.out.println(est.toString());
    }

    private static int leerInt(String mensaje) {
        int valor = -1;
        while (true) {
            System.out.print(mensaje);
            String linea = sc.nextLine().trim();
            if (linea.isEmpty()) {
                System.out.println("Entrada vacía. Intenta de nuevo.");
                continue;
            }
            try {
                valor = Integer.parseInt(linea);
                return valor;
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Ingresa un número entero.");
            }
        }
    }
}
