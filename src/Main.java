import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ListaEnlazadaSimple listaMedicamentos = new ListaEnlazadaSimple();

        int opcion;
        do {
            System.out.println("\n===== MENÚ FARMACIA =====");
            System.out.println("1. Insertar medicamento al inicio");
            System.out.println("2. Insertar medicamento al final");
            System.out.println("3. Editar medicamento");
            System.out.println("4. Eliminar medicamento");
            System.out.println("5. Mostrar todos los medicamentos");
            System.out.println("6. Mostrar reporte de costos");
            System.out.println("7. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                case 2:

                    //Acá pongo el del código me parece que hace más sentido que se solicite de primero
                    System.out.print("Código del producto: ");
                    int codigoProducto = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine();

                    System.out.print("Categoría: ");
                    String categoria = sc.nextLine();

                    System.out.print("Descripción: ");
                    String descripcion = sc.nextLine();

                    System.out.print("Cantidad: ");
                    int cantidad = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Precio: ");
                    double precio = sc.nextDouble();
                    sc.nextLine();

                    //Le voy a hacer una validacion Melima cualquier cosita para que quede como cuando el profe hace las de la clase
                    LocalDate fecha = null;
                    boolean fechaValida = false;
                    while (!fechaValida) {
                        System.out.print("Fecha de vencimiento (AAAA-MM-DD): ");
                        String fechaStr = sc.nextLine();
                        try {
                            fecha = LocalDate.parse(fechaStr);
                            fechaValida = true;
                        } catch (Exception e) {
                            System.out.println("Formato inválido. Use (AAAA-MM-DD).");
                        }
                    }

                    //Campos para las insrucciones del medicamento y los efectos

                    System.out.println("Instrucciones: ");
                    String instrucciones = sc.nextLine();

                    System.out.println("Efectos Secundarios: ");
                    String efectosSecundario = sc.nextLine();


                    Medicamento m = new Medicamento(nombre, categoria, fecha, cantidad, precio,
                            codigoProducto,instrucciones, efectosSecundario);

                    if (opcion == 1) {
                        listaMedicamentos.insertarInicio(m);
                    } else {
                        listaMedicamentos.insertarFinal(m);
                    }
                    System.out.println("Medicamento agregado.");
                    break;

                case 3:
                    // tengo que arreglar bien este metodo
                    System.out.print("Ingrese el nombre del medicamento a editar: ");
                    String nombreEditar = sc.nextLine();
                    Medicamento medEditar = listaMedicamentos.buscarMedicamento(nombreEditar);

                    if (medEditar != null) {
                        System.out.println("---Editando Medicamento---");

                        System.out.print("Nuevo nombre (" + medEditar.getNombre() + "): ");
                        String nuevoNombre = sc.nextLine();
                        if (!nuevoNombre.isEmpty()) medEditar.setNombre(nuevoNombre);

                        System.out.print("Nueva cantidad (" + medEditar.getCantidad() + "): ");
                        String cantStr = sc.nextLine();
                        if (!cantStr.isEmpty()) medEditar.setCantidad(Integer.parseInt(cantStr));

                        System.out.print("Nuevo precio (" + medEditar.getPrecio() + "): ");
                        String precioStr = sc.nextLine();
                        if (!precioStr.isEmpty()) medEditar.setPrecio(Double.parseDouble(precioStr));

                        System.out.println("Medicamento actualizado.");
                    } else {
                        System.out.println("El medicamento no fue encontrado.");
                    }
                    break;

                case 4:

                    System.out.print("Ingrese el nombre del medicamento a eliminar: ");
                    String nombreEliminar = sc.nextLine();
                    listaMedicamentos.eliminarMedicamento(nombreEliminar);
                    break;

                case 5:
                    // Mostrar lista
                    listaMedicamentos.mostrarLista();
                    break;

                case 6:
                    //Reporte de costos:
                    listaMedicamentos.mostrarReporteCostos();
                    break;

                case 7:
                    System.out.println("Saliedo...");
                    break;

                default:
                    System.out.println("Opción inválida.");
            }

        } while (opcion != 7);

        sc.close();
    }
}
