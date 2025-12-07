import java.util.LinkedList;

public class ColaClientes {

    private final LinkedList<Cliente> colaPrioridadCliente;
    private Grafo grafo;  //Grafo para registrar ubicaciones

    public ColaClientes(Grafo grafo){
        this.colaPrioridadCliente = new LinkedList<>();
        this.grafo = grafo;
    }

    // Insertar cliente en la cola y agregar su ubicación al grafo
    public void enqueue(Cliente nuevo){

        //Si la dirección del cliente no existe como vértice, agregarla automáticamente
        if(!grafo.existeVertice(nuevo.getDireccion())){
            grafo.agregarVertice(nuevo.getDireccion());
        }

        // Inserción por prioridad (tu código original)
        if(colaPrioridadCliente.isEmpty()) {
            colaPrioridadCliente.add(nuevo);
            return;
        }

        int i = 0;
        while( i < colaPrioridadCliente.size() &&
                colaPrioridadCliente.get(i).getPrioridad() >= nuevo.getPrioridad())
        {
            i++;
        }

        colaPrioridadCliente.add(i, nuevo);
    }

    public Cliente dequeue(){
        if(colaPrioridadCliente.isEmpty()) {
            System.out.println("La cola de clientes esta vacía");
            return null;
        }
        return colaPrioridadCliente.removeFirst();
    }

    public Cliente peek(){
        if(colaPrioridadCliente.isEmpty()) {
            return null;
        }
        return colaPrioridadCliente.getFirst();
    }

    public boolean colaVacia(){
        return colaPrioridadCliente.isEmpty();
    }

    public Cliente buscarPorID(String id) {
        for (Cliente c : colaPrioridadCliente) {
            if (c.getIdCliente().equals(id)) {
                return c;
            }
        }
        return null;
    }

    public boolean eliminar(String id) {
        for (int i = 0; i < colaPrioridadCliente.size(); i++) {
            if (colaPrioridadCliente.get(i).getIdCliente().equals(id)) {
                colaPrioridadCliente.remove(i);
                return true;
            }
        }
        return false;
    }
 main
    public void mostrarCola() {
        if (colaPrioridadCliente.isEmpty()) {
            System.out.println("La cola de clientes está vacía.");
            return;
        }

        System.out.println("\n-- COLA DE CLIENTES --");
        for (Cliente c : colaPrioridadCliente) {
            System.out.println("Nombre: " + c.getNombre() +
                    "\nPrioridad: " + c.getPrioridad() +
                    "\nUbicación: " + c.getDireccion());
        }
    }
}
