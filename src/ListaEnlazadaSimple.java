public class ListaEnlazadaSimple {

    private Nodo primero;

    public ListaEnlazadaSimple() {
        primero = null;
    }

    public Nodo getPrimero() {
        return primero;
    }

    public void setPrimero(Nodo nuevoPrimero) {
        primero = nuevoPrimero;
    }

    public void insertarInicio(Medicamento m) {
        Nodo nuevo = new Nodo(m);
        nuevo.setSiguiente(primero);
        primero = nuevo;
    }

    public void insertarFinal(Medicamento m) {
        Nodo nuevo = new Nodo(m);
        if (primero == null) {
            setPrimero(nuevo);
            return;
        }
        Nodo temp = primero;
        while (temp.getSiguiente() != null) {
            temp = temp.getSiguiente();
        }
        temp.setSiguiente(nuevo);
    }

    public Medicamento buscarMedicamento(String nombre) {
        Nodo actual = primero;
        while (actual != null && !actual.getDato().getNombre().equalsIgnoreCase(nombre)) {
            actual = actual.getSiguiente();
        }
        if (actual != null) {
            System.out.println("El medicamento fue encontrado.");
            return actual.getDato();
        } else {
            System.out.println("El medicamento no fue encontrado.");
            return null;
        }
    }


    public void mostrarLista() {
        if (primero == null) {
            System.out.println("Lista vacía.");
            return;
        }
        Nodo actual = primero;
        while (actual != null) {
            System.out.println(actual.getDato()); // usa el toString() de Medicamento
            actual = actual.getSiguiente();
        }
    }


    public Medicamento eliminarMedicamento(String nombre) {
        if (primero == null) {
            System.out.println("Lista vacía.");
            return null;
        }
        Nodo actual = primero;
        Nodo anterior = null;

        while (actual != null && !actual.getDato().getNombre().equalsIgnoreCase(nombre)) {
            anterior = actual;
            actual = actual.getSiguiente();
        }

        if (actual != null) {
            System.out.println("El medicamento a eliminar fue encontrado.");
            if (actual == primero) {
                primero = primero.getSiguiente();
            } else {
                anterior.setSiguiente(actual.getSiguiente());
            }
            return actual.getDato();
        } else {
            System.out.println("El medicamento no fue encontrado.");
            return null;
        }
    }

    //Este es un metodo que podemos usar para el carrito
    public double calcularCostoTotal() {
        double total = 0;
        Nodo actual = primero;
        while (actual != null) {
            Medicamento m = actual.getDato();
            total += m.getPrecio() * m.getCantidad();
            actual = actual.getSiguiente();
        }
        return total;
    }

    //Para que se vea como factura (temporal)
    public void mostrarReporteCostos() {
        if (primero == null) {
            System.out.println("La lista está vacía.");
            return;
        }

        Nodo actual = primero;
        double totalAcumulado = 0;

        System.out.println("\n=======REPORTE DE COSTOS=======");
        while (actual != null) {
            Medicamento m = actual.getDato();
            double subtotal = m.getPrecio() * m.getCantidad();
            totalAcumulado += subtotal;

            System.out.println("Medicamento: " + m.getNombre());
            System.out.println("Categoría: " + m.getCategoria());
            System.out.println("Cantidad: " + m.getCantidad());
            System.out.println("Precio unitario: ₡" + m.getPrecio());
            System.out.println("Subtotal: ₡" + subtotal);
            System.out.println("----------------------------------");

            actual = actual.getSiguiente();
        }

        System.out.println("________TOTAL________ ₡" + totalAcumulado);
        System.out.println("===================================");
    }
}
