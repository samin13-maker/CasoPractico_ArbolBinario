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
        if (p.getMatricula() == actual.getDato().getMatricula()) {
            return false; 
        } 
        else if (p.getMatricula() < actual.getDato().getMatricula()) {
            if (actual.getIzq() == null) {
                actual.setIzq(new Nodo(p));
                return true;
            } else {
                return insertarRec(actual.getIzq(), p);
            }
        } 
        else { 
            if (actual.getDer() == null) {
                actual.setDer(new Nodo(p));
                return true;
            } else {
                return insertarRec(actual.getDer(), p);
            }
        }
    }

    public void inordenMostrarMatriculaCategoria() {
        if (raiz == null) {
            System.out.println("El árbol está vacío.");
        } else {
            System.out.println("\nParticipantes (Inorden por matrícula):");
            inordenRec(raiz);
        }
    }

    private void inordenRec(Nodo nodo) {
        if (nodo != null) {
            inordenRec(nodo.getIzq());
            System.out.println("Matrícula: " + nodo.getDato().getMatricula() + 
                               " | Categoría: " + nodo.getDato().getCategoria());
            inordenRec(nodo.getDer());
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
        recorrerYContar(nodo.getIzq(), est);

        est.totalParticipantes++;
        est.sumaEdades += nodo.getDato().getEdad();

        String cat = nodo.getDato().getCategoria().toLowerCase();
        switch(cat) {
            case "principiante": est.countPrincipiante++; break;
            case "intermedio":   est.countIntermedio++;   break;
            case "avanzado":     est.countAvanzado++;     break;
            default: est.countOtros++; break;
        }

        recorrerYContar(nodo.getDer(), est);
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
            }
        }

        @Override
        public String toString() {
            return "\nTotal participantes: " + totalParticipantes +
                   "\nPromedio de edad: " + String.format("%.2f", promedioEdad) +
                   "\nPrincipiantes: " + countPrincipiante +
                   "\nIntermedios: " + countIntermedio +
                   "\nAvanzados: " + countAvanzado +
                   (countOtros > 0 ? "\nOtros: " + countOtros : "");
        }
    }
}
