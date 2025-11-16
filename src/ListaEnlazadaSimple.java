public class ListaEnlazadaSimple {

    private Medicamento primero;

    public ListaEnlazadaSimple() {
        primero = null;
    }

    public Medicamento getPrimero() {
        return primero;
    }

    public void setPrimero(Medicamento nuevoPrimero) {
        primero = nuevoPrimero;
    }

    public void insertarInicio(Medicamento m) {
        m.setSiguiente(primero);
        primero = m;
    }

    public void insertarFinal(Medicamento m) {
        if (primero == null) {
            primero = m;
            return;
        }
        Medicamento temp = primero;
        while (temp.getSiguiente() != null) {
            temp = temp.getSiguiente();
        }
        temp.setSiguiente(m);
    }

    public Medicamento buscarMedicamento(String nombre) {
        Medicamento actual = primero;
        while (actual != null && !actual.getNombre().equalsIgnoreCase(nombre)) {
            actual = actual.getSiguiente();
        }
        if (actual != null) {
            System.out.println("El medicamento fue encontrado.");
        } else {
            System.out.println("El medicamento no fue encontrado.");

        }
        return actual;
    }


    public void mostrarLista() {
        if (primero == null) {
            System.out.println("Lista vacía.");
            return;
        }
        Medicamento actual = primero;
        while (actual != null) {
            System.out.println(actual); // usa el toString() de Medicamento, avance 2: se cambió para que funcione con las modificaciones de nodo!
            actual = actual.getSiguiente();
        }
    }


    public Medicamento eliminarMedicamento(String nombre) {
        if (primero == null) {
            System.out.println("Lista vacía.");
            return null;
        }
        Medicamento actual = primero;
        Medicamento anterior = null;

        while (actual != null && !actual.getNombre().equalsIgnoreCase(nombre)) {
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
            return actual;
        } else {
            System.out.println("El medicamento no fue encontrado.");
            return null;
        }
    }

    //Este es un metodo que podemos usar para el carrito
    public double calcularCostoTotal() {
        double total = 0;
        Medicamento actual = primero;
        while (actual != null) {
            total += actual.getPrecio() * actual.getCantidad(); //cambio a actual por las modificaciones de antes
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

        Medicamento actual = primero;
        double totalAcumulado = 0;

        System.out.println("\n=======REPORTE DE COSTOS=======");
        while (actual != null) {
            double subtotal = actual.getPrecio() * actual.getCantidad();
            totalAcumulado += subtotal;

            System.out.println("Medicamento: " + actual.getNombre());
            System.out.println("Categoría: " + actual.getCategoria());
            System.out.println("Cantidad: " + actual.getCantidad());
            System.out.println("Precio unitario: ₡" + actual.getPrecio());
            System.out.println("Subtotal: ₡" + subtotal);
            System.out.println("----------------------------------");

            actual = actual.getSiguiente();
        }

        System.out.println("________TOTAL________ ₡" + totalAcumulado);
        System.out.println("===================================");
    }
}
