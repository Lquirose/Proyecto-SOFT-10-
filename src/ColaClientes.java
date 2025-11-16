import java.util.LinkedList;

public class ColaClientes {

    //Cola Prioridades
    // 1 = Cliente Básico
    // 2 = Cliente Afiliado
    // 3 = Cliente Premium

   private final LinkedList<Cliente> colaPrioridadCliente;

   public ColaClientes(){
       colaPrioridadCliente = new LinkedList<>();
   }

   //Insertar al cliente en la cola
    public void enqueue(Cliente nuevo){
        if(colaPrioridadCliente.isEmpty()) {
            colaPrioridadCliente.add(nuevo);
            return;
        }

        //Lógica para que la prioridad se mantenga
        // revisando el índice del cliente en la lista en caso de que se repita el número de prioridad
        int i = 0;
        while( i < colaPrioridadCliente.size() && colaPrioridadCliente.get(i).getPrioridad() >= nuevo.getPrioridad()){
            i++;
        }


        colaPrioridadCliente.add(nuevo); //inserta el número en la posición correcta si entra de último
        //en las colas de prioridad normales "PriorityQueue<Cliente> prioridad = new PriorityQueue<>();" se ordena como en los
        //montículos pero en este caso necesitamos que se tome en cuenta el número del índice de ese cliente en la cola.
    }

    public Cliente dequeue(){
       if(colaPrioridadCliente.isEmpty()) {
           System.out.println("La cola de clientes esta vacía");
           return null;
       }
       return colaPrioridadCliente.removeFirst(); //Saca el primer cliente en la cola
    }

    //siguiente en la cola (fila)
    public  Cliente peek(){
        if(colaPrioridadCliente.isEmpty()) {
            return null;
        }
        return colaPrioridadCliente.getFirst();
    }

    //Verificar que NO esté vacía
    public boolean colaVacia(){
        return colaPrioridadCliente.isEmpty();
    }

    //Imprimir
    public void ImprimirColaPriordadClientes() {
        if (colaPrioridadCliente.isEmpty()) {
            System.out.println("La cola de clientes está vacía.");
            return;
        }

        System.out.println("\n-- COLA DE CLIENTES --");
        for (Cliente c : colaPrioridadCliente) {
            System.out.println("Nombre: " + c.getNombre() +
                    "\nPrioridad: " + c.getPrioridad());
        }
    }
}
