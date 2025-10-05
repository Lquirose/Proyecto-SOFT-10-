public class Nodo {
    private Medicamento dato;
    private Nodo siguiente;

    public Nodo(Medicamento dato) {
        this.dato = dato;
        this.siguiente = null;
    }


    public Medicamento getDato() {
        return dato;
    }

    public void setDato(Medicamento dato) {
        this.dato = dato;
    }

    public Nodo getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }
}
