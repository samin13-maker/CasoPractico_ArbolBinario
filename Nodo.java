public class Nodo {     
    private Persona dato;     
    private Nodo izq;     
    private Nodo der;     

    public Nodo(Persona p) {        
        this.dato = p;        
        this.izq = null;        
        this.der = null;    
    }     

    public Persona getDato() {
        return dato;
    }

    public void setDato(Persona dato) {
        this.dato = dato;
    }

    public Nodo getIzq() {
        return izq;
    }

    public void setIzq(Nodo izq) {
        this.izq = izq;
    }

    public Nodo getDer() {
        return der;
    }

    public void setDer(Nodo der) {
        this.der = der;
    }
}
