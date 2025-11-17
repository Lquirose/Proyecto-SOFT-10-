import java.time.LocalDate;
import java.util.Scanner;

public class Main {

    private static final Scanner sc = new Scanner(System.in);

    // Árbol de medicamentos
    private static final arbolProductos inventario = new arbolProductos();

    // Cola de prioridad para clientes
    private static final ColaClientes colaClientes = new ColaClientes();

    public static void main(String[] args) {

        int opcion;
        do {
            mostrarMenuPrincipal();
            opcion = leerEntero();

            switch (opcion) {
                case 1 -> menuMedicamentos();
                case 2 -> menuClientes();
                case 3 -> atenderCola();
                case 4 -> System.out.println("Saliendo del sistema...");
                default -> System.out.println("Opción inválida.");
            }
        } while (opcion != 4);
    }

    // ================================
    //  MENÚ PRINCIPAL
    // ================================
    private static void mostrarMenuPrincipal() {
        System.out.println("\n===== MENÚ PRINCIPAL =====");
        System.out.println("1. Gestionar Medicamentos");
        System.out.println("2. Gestionar Clientes");
        System.out.println("3. Atender Cola");
        System.out.println("4. Salir");
        System.out.print("Seleccione una opción: ");
    }

    // ================================
    //  MENU MEDICAMENTOS
    // ================================
    private static void menuMedicamentos() {
        int opcion;
        do {
            System.out.println("\n===== MENÚ MEDICAMENTOS =====");
            System.out.println("1. Insertar medicamento");
            System.out.println("2. Editar medicamento");
            System.out.println("3. Eliminar medicamento");
            System.out.println("4. Mostrar medicamentos (In-Order)");
            System.out.println("5. Mostrar costo total del inventario");
            System.out.println("6. Volver");
            System.out.print("Seleccione una opción: ");

            opcion = leerEntero();

            switch (opcion) {
                case 1 -> insertarMedicamento();
                case 2 -> editarMedicamento();
                case 3 -> eliminarMedicamento();
                case 4 -> mostrarMedicamentos();
                case 5 -> mostrarReporteCostos();
                case 6 -> {}
                default -> System.out.println("Opción inválida.");
            }
        } while (opcion != 6);
    }

    // ================================
    // MÉTODOS DE MEDICAMENTOS
    // ================================
    private static void insertarMedicamento() {
        System.out.print("Código del producto: ");
        int codigoProducto = leerEntero();

        sc.nextLine();
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();

        System.out.print("Categoría: ");
        String categoria = sc.nextLine();

        System.out.print("Cantidad: ");
        int cantidad = leerEntero();

        System.out.print("Precio: ");
        double precio = leerDouble();

        sc.nextLine(); //Limpia el buffer para que no de el error en la fecha!

        LocalDate fecha = null;
        while (true) {
            System.out.print("Fecha de vencimiento (AAAA-MM-DD): ");
            try {
                fecha = LocalDate.parse(sc.nextLine());
                break;
            } catch (Exception e) {
                System.out.println("Formato inválido. Intente nuevamente.");
            }
        }

        System.out.print("Instrucciones: ");
        String instrucciones = sc.nextLine();

        System.out.print("Efectos secundarios: ");
        String efectos = sc.nextLine();

        Medicamento m = new Medicamento(nombre, categoria, fecha, cantidad, precio,
                codigoProducto, instrucciones, efectos);

        System.out.print("¿Desea agregar imágenes? (s/n): ");
        if (sc.nextLine().equalsIgnoreCase("s"))
            gestionarImagenes(m);

        inventario.insertar(m);
        System.out.println("Medicamento agregado correctamente.");
    }

    private static void editarMedicamento() {
        System.out.print("Nombre del medicamento a editar: ");
        String nombre = sc.nextLine();

        Medicamento med = inventario.buscar(nombre);

        if (med == null) {
            System.out.println("No existe un medicamento con este nombre.");
            return;
        }

        System.out.println("--- Editar medicamento ---");
        System.out.print("Nuevo nombre (" + med.getNombre() + "): ");
        String nuevo = sc.nextLine();
        if (!nuevo.isEmpty()) med.setNombre(nuevo);

        System.out.print("Nueva categoría (" + med.getCategoria() + "): ");
        String cat = sc.nextLine();
        if (!cat.isEmpty()) med.setCategoria(cat);

        System.out.print("Nueva cantidad (" + med.getCantidad() + "): ");
        String cant = sc.nextLine();
        if (!cant.isEmpty()) med.setCantidad(Integer.parseInt(cant));

        System.out.print("Nuevo precio (" + med.getPrecio() + "): ");
        String pre = sc.nextLine();
        if (!pre.isEmpty()) med.setPrecio(Double.parseDouble(pre));

        System.out.print("¿Editar imágenes? (s/n): ");
        if (sc.nextLine().equalsIgnoreCase("s"))
            gestionarImagenes(med);

        System.out.println("Medicamento actualizado.");
    }

    private static void eliminarMedicamento() {
        System.out.print("Nombre del medicamento a eliminar: ");
        inventario.eliminarPorNombre(sc.nextLine());
        System.out.println("Medicamento eliminado.");
    }

    private static void mostrarMedicamentos() {
        System.out.println("\n===== INVENTARIO (In-Order) =====");
        inventario.inOrder();
    }

    private static void mostrarReporteCostos() {
        System.out.println("Costo total: " + inventario.calcularCostoTotal());
    }

    private static void gestionarImagenes(Medicamento med) {
        int op;
        do {
            System.out.println("\n--- Imágenes de " + med.getNombre() + " ---");
            System.out.println("1. Agregar imagen");
            System.out.println("2. Eliminar imagen");
            System.out.println("3. Mostrar imágenes");
            System.out.println("0. Volver");
            op = leerEntero();
            sc.nextLine();

            switch (op) {
                case 1 -> {
                    System.out.print("Ruta: ");
                    med.agregarImagen(sc.nextLine());
                }
                case 2 -> {
                    med.mostrarImagenes();
                    System.out.print("Ruta a eliminar: ");
                    med.eliminarImagen(sc.nextLine());
                }
                case 3 -> med.mostrarImagenes();
                case 0 -> {}
                default -> System.out.println("Opción inválida.");
            }
        } while (op != 0);
    }



    // ================================
    // MENU CLIENTES
    // ================================
    private static void menuClientes() {
        int opcion;

        do {
            System.out.println("\n===== MENÚ CLIENTES =====");
            System.out.println("1. Ingresar cliente");
            System.out.println("2. Editar cliente");
            System.out.println("3. Eliminar cliente");
            System.out.println("4. Mostrar clientes en cola");
            System.out.println("5. Volver");
            System.out.print("Seleccione una opción: ");

            opcion = leerEntero();

            switch (opcion) {
                case 1 -> ingresarCliente();
                case 2 -> editarCliente();
                case 3 -> eliminarCliente();
                case 4 -> mostrarCola();
                case 5 -> {}
                default -> System.out.println("Opción inválida.");
            }
        } while (opcion != 5);
    }

    // ================================
    // MÉTODOS CLIENTES
    // ================================
    private static void ingresarCliente() {
        sc.nextLine();
        System.out.print("ID Cliente: ");
        String id = sc.nextLine();

        System.out.print("Nombre: ");
        String nombre = sc.nextLine();

        System.out.print("Correo: ");
        String correo = sc.nextLine();

        System.out.print("Teléfono: ");
        String tel = sc.nextLine();

        System.out.print("Dirección: ");
        String dir = sc.nextLine();

        Cliente c = new Cliente(id, nombre, correo, tel, dir);

        System.out.print("Prioridad (1–3): ");
        c.setPrioridad(leerEntero());
        sc.nextLine(); // limpiar buffer

<<<<<<< Updated upstream
<<<<<<< Updated upstream
<<<<<<< Updated upstream
=======
=======
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
        System.out.println("A continuación se mostrará la lista de medicamentos. Presione 's' para continuar:");
        String carritoLlenar = sc.nextLine();

        if (!carritoLlenar.equalsIgnoreCase("s")) {
            System.out.println("Operación cancelada. Debe presionar 's' para continuar.");
            return;
        }

        // Mostrar inventario
<<<<<<< Updated upstream
<<<<<<< Updated upstream
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
        System.out.println("\n=== LISTA DE MEDICAMENTOS ===");
        inventario.inOrder();

        System.out.println("\nIngrese productos al carrito (escriba 'fin' para terminar):");

        while (true) {
            System.out.print("Nombre del medicamento: ");
            String prod = sc.nextLine();
            if (prod.equalsIgnoreCase("fin")) break;

            Medicamento invMed = inventario.buscar(prod);

            if (invMed != null) {
                System.out.print("¿Cuántas unidades desea? ");
                int cantidad = leerEntero();
<<<<<<< Updated upstream
<<<<<<< Updated upstream
<<<<<<< Updated upstream

                if (cantidad > invMed.getCantidad()) {
                    System.out.println("❌ No hay suficientes existencias. Solo quedan: " + invMed.getCantidad());
=======
=======
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
                sc.nextLine(); // limpiar buffer

                if (cantidad > invMed.getCantidad()) {
                    System.out.println("No hay suficientes existencias. Solo quedan: " + invMed.getCantidad());
<<<<<<< Updated upstream
<<<<<<< Updated upstream
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
                    continue;
                }

                // Restar existencias reales
                invMed.setCantidad(invMed.getCantidad() - cantidad);

<<<<<<< Updated upstream
<<<<<<< Updated upstream
<<<<<<< Updated upstream
                // Crear un medicamento para el carrito
                Medicamento medCarrito = new Medicamento(
                        invMed.getNombre(),
                        invMed.getPrecio(),
                        cantidad,                 // cantidad comprada del usuario
                        invMed.getCategoria()
                );

                medCarrito.setSiguiente(null); // evitar romper la lista del árbol

                // Insertar en el carrito
                c.getCarrito().insertarFinal(medCarrito);

                System.out.println("✔ Producto agregado.");
=======
=======
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
                // Crear medicamento del carrito (copia ligera)
                Medicamento medCarrito = new Medicamento(
                        invMed.getNombre(),
                        invMed.getPrecio(),
                        cantidad,
                        invMed.getCategoria()
                );

                c.getCarrito().insertarFinal(medCarrito);
                System.out.println("✔ Producto agregado.");

<<<<<<< Updated upstream
<<<<<<< Updated upstream
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
            } else {
                System.out.println("❌ El producto no existe.");
            }
        }

        // Encolar cliente correctamente
        colaClientes.enqueue(c);
        System.out.println("Cliente agregado y encolado.");
    }


<<<<<<< Updated upstream
<<<<<<< Updated upstream
<<<<<<< Updated upstream

=======
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
    private static void editarCliente() {
        sc.nextLine();
        System.out.print("Ingrese ID del cliente para editar: ");
        String id = sc.nextLine();

        Cliente cliente = colaClientes.buscarPorID(id);

        if (cliente == null) {
            System.out.println("Cliente no encontrado.");
            return;
        }

        System.out.print("Nuevo nombre (" + cliente.getNombre() + "): ");
        String nuevo = sc.nextLine();
        if (!nuevo.isEmpty()) cliente.setNombre(nuevo);

        System.out.print("Nuevo correo (" + cliente.getCorreo() + "): ");
        String correo = sc.nextLine();
        if (!correo.isEmpty()) cliente.setCorreo(correo);

        System.out.print("Nuevo teléfono (" + cliente.getTelefono() + "): ");
        String tel = sc.nextLine();
        if (!tel.isEmpty()) cliente.setTelefono(tel);

        System.out.print("Nueva dirección (" + cliente.getDireccion() + "): ");
        String dir = sc.nextLine();
        if (!dir.isEmpty()) cliente.setDireccion(dir);

        System.out.print("Nueva prioridad (" + cliente.getPrioridad() + "): ");
        String prio = sc.nextLine();
        if (!prio.isEmpty()) cliente.setPrioridad(Integer.parseInt(prio));

        System.out.println("Cliente actualizado.");
    }

    private static void eliminarCliente() {
        sc.nextLine();
        System.out.print("ID Cliente a eliminar: ");
        String id = sc.nextLine();

        boolean eliminado = colaClientes.eliminar(id);

        if (eliminado) {
            System.out.println("Cliente eliminado.");
        }
        else {
            System.out.println("El cliente no existe.");
        }
    }

    private static void mostrarCola() {
        System.out.println("\n=== CLIENTES EN COLA ===");
        colaClientes.mostrarCola();
    }

    private static void atenderCola() {
        if (colaClientes.colaVacia()) {
            System.out.println("No hay clientes en espera.");
            return;
        }

        Cliente c = colaClientes.dequeue();
        System.out.println("\n=== ATENDIENDO CLIENTE ===");

        // Solo nombre e ID
        System.out.println("Cliente: " + c.getNombre());
        System.out.println("ID: " + c.getIdCliente());

        System.out.println("\nCarrito:");
        c.getCarrito().mostrarReporteCostos();
    }



    // ================================
    // MÉTODOS EXTRA
    // ================================
    private static int leerEntero() {
        while (!sc.hasNextInt()) {
            System.out.print("Ingrese un número válido: ");
            sc.next();
        }
        return sc.nextInt();
    }

    private static double leerDouble() {
        while (!sc.hasNextDouble()) {
            System.out.print("Ingrese un número válido: ");
            sc.next();
        }
        return sc.nextDouble();
    }
}
