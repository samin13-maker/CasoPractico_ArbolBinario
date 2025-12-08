public class Nodo {
    Persona dato;
    Nodo izq;
    Nodo der;

    public Nodo(Persona p) {
        this.dato = p;
        this.izq = null;
        this.der = null;
    }
}
