public class ArbolBinario {
    private Nodo raiz;

    public ArbolBinario() {
        raiz = null;
    }

    public boolean insertar(Persona p) {
        if (raiz == null) {
            raiz = new Nodo(p);
            return true;
        } else {
            return insertarRec(raiz, p);
        }
    }

    private boolean insertarRec(Nodo actual, Persona p) {
        if (p.getMatricula() == actual.dato.getMatricula()) {
            return false; 
        } else if (p.getMatricula() < actual.dato.getMatricula()) {
            if (actual.izq == null) {
                actual.izq = new Nodo(p);
                return true;
            } else {
                return insertarRec(actual.izq, p);
            }
        } else { 
            if (actual.der == null) {
                actual.der = new Nodo(p);
                return true;
            } else {
                return insertarRec(actual.der, p);
            }
        }
    }

    public void inordenMostrarMatriculaCategoria() {
        if (raiz == null) {
            System.out.println("El árbol está vacío.");
        } else {
            System.out.println("Participantes (por matrícula):");
            inordenRec(raiz);
        }
    }

    private void inordenRec(Nodo nodo) {
        if (nodo != null) {
            inordenRec(nodo.izq);
            System.out.println("Matricula: " + nodo.dato.getMatricula() + " | Categoria: " + nodo.dato.getCategoria());
            inordenRec(nodo.der);
        }
    }

    public Estadisticas obtenerEstadisticas() {
        Estadisticas est = new Estadisticas();
        if (raiz == null) return est;
        recorrerYContar(raiz, est);
        est.calcularPromedio();
        return est;
    }

    private void recorrerYContar(Nodo nodo, Estadisticas est) {
        if (nodo == null) return;
        recorrerYContar(nodo.izq, est);

        est.totalParticipantes++;
        est.sumaEdades += nodo.dato.getEdad();
        String cat = nodo.dato.getCategoria().toLowerCase();

        if (cat.equals("principiante")) {
            est.countPrincipiante++;
        } else if (cat.equals("intermedio")) {
            est.countIntermedio++;
        } else if (cat.equals("avanzado")) {
            est.countAvanzado++;
        } else {
            est.countOtros++;
        }

        recorrerYContar(nodo.der, est);
    }

    public static class Estadisticas {
        int totalParticipantes = 0;
        int sumaEdades = 0;
        double promedioEdad = 0.0;
        int countPrincipiante = 0;
        int countIntermedio = 0;
        int countAvanzado = 0;
        int countOtros = 0;

        void calcularPromedio() {
            if (totalParticipantes > 0) {
                promedioEdad = (double) sumaEdades / totalParticipantes;
            } else {
                promedioEdad = 0.0;
            }
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("Total participantes: ").append(totalParticipantes).append("\n");
            sb.append(String.format("Promedio de edad: %.2f\n", promedioEdad));
            sb.append("Principiante: ").append(countPrincipiante).append("\n");
            sb.append("Intermedio: ").append(countIntermedio).append("\n");
            sb.append("Avanzado: ").append(countAvanzado).append("\n");
            if (countOtros > 0) sb.append("Otros: ").append(countOtros).append("\n");
            return sb.toString();
        }
    }
}
