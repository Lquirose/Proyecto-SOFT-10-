import java.util.ArrayList;
import java.util.List;

public class arbolProductos {

    private class Nodo {
        Medicamento medicamento;
        Nodo izquierdo;
        Nodo derecho;

        Nodo(Medicamento m) {
            this.medicamento = m;
        }
    }

    private Nodo raiz;

    // ================================
    // INSERTAR POR NOMBRE
    // ================================
    public void insertar(Medicamento m) {
        raiz = insertarRec(raiz, m);
    }

    private Nodo insertarRec(Nodo actual, Medicamento m) {
        if (actual == null) return new Nodo(m);

        int cmp = m.getNombre().compareToIgnoreCase(actual.medicamento.getNombre());

        if (cmp < 0) actual.izquierdo = insertarRec(actual.izquierdo, m);
        else if (cmp > 0) actual.derecho = insertarRec(actual.derecho, m);
        else System.out.println("Ya existe un medicamento con ese nombre.");

        return actual;
    }

    // ================================
    // BUSCAR POR NOMBRE
    // ================================
    public Medicamento buscar(String nombre) {
        return buscarNombreRec(raiz, nombre);
    }

    private Medicamento buscarNombreRec(Nodo actual, String nombre) {
        if (actual == null) return null;

        int cmp = nombre.compareToIgnoreCase(actual.medicamento.getNombre());

        if (cmp == 0) return actual.medicamento;
        if (cmp < 0) return buscarNombreRec(actual.izquierdo, nombre);
        return buscarNombreRec(actual.derecho, nombre);
    }

    // ================================
    // ELIMINAR POR NOMBRE
    // ================================
    public void eliminarPorNombre(String nombre) {
        raiz = eliminarPorNombreRec(raiz, nombre);
    }

    private Nodo eliminarPorNombreRec(Nodo actual, String nombre) {
        if (actual == null) return null;

        int cmp = nombre.compareToIgnoreCase(actual.medicamento.getNombre());

        if (cmp < 0) {
            actual.izquierdo = eliminarPorNombreRec(actual.izquierdo, nombre);
        }
        else if (cmp > 0) {
            actual.derecho = eliminarPorNombreRec(actual.derecho, nombre);
        }
        else {
            // Caso 1: sin hijos
            if (actual.izquierdo == null && actual.derecho == null)
                return null;

            // Caso 2: un hijo
            if (actual.izquierdo == null)
                return actual.derecho;

            if (actual.derecho == null)
                return actual.izquierdo;

            // Caso 3: dos hijos → sucesor
            Nodo sucesor = minimo(actual.derecho);
            actual.medicamento = sucesor.medicamento;
            actual.derecho = eliminarPorNombreRec(actual.derecho, sucesor.medicamento.getNombre());
        }

        return actual;
    }

    private Nodo minimo(Nodo nodo) {
        while (nodo.izquierdo != null) nodo = nodo.izquierdo;
        return nodo;
    }

    // ================================
    // LISTA ORDENADA
    // ================================
    public List<Medicamento> obtenerListaOrdenada() {
        List<Medicamento> lista = new ArrayList<>();
        inOrderRec(raiz, lista);
        return lista;
    }

    private void inOrderRec(Nodo actual, List<Medicamento> lista) {
        if (actual != null) {
            inOrderRec(actual.izquierdo, lista);
            lista.add(actual.medicamento);
            inOrderRec(actual.derecho, lista);
        }
    }

    // ================================
    // IMPRIMIR
    // ================================
    public void inOrder() {
        imprimirInOrder(raiz);
    }

    private void imprimirInOrder(Nodo actual) {
        if (actual != null) {
            imprimirInOrder(actual.izquierdo);
            System.out.println(actual.medicamento);
            imprimirInOrder(actual.derecho);
        }
    }

    // ================================
    // COSTO TOTAL
    // ================================
    public double calcularCostoTotal() {
        return calcularCostoRec(raiz);
    }

    private double calcularCostoRec(Nodo actual) {
        if (actual == null) return 0;

        double costoActual = actual.medicamento.getPrecio()
                * actual.medicamento.getCantidad();

        return costoActual
                + calcularCostoRec(actual.izquierdo)
                + calcularCostoRec(actual.derecho);
    }
}
