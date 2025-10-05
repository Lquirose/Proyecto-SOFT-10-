import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    private static final Scanner sc = new Scanner(System.in);
    private static final ListaEnlazadaSimple listaMedicamentos = new ListaEnlazadaSimple();

    public static void main(String[] args) {
        int opcion;
        do {
            mostrarMenu();
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    insertarMedicamento(true); // Inserta al inicio
                    break;
                case 2:
                    insertarMedicamento(false); //Inserta al final
                    break;
                case 3:
                    editarMedicamento();
                    break;
                case 4:
                    eliminarMedicamento();
                    break;
                case 5:
                    mostrarMedicamentos();
                    break;
                case 6:
                    mostrarReporteCostos();
                    break;
                case 7:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (opcion != 7);

        sc.close();
    }

    // --- MÉTODOS DEL MENÚ ---

    private static void mostrarMenu() {
        System.out.println("\n===== MENÚ FARMACIA =====");
        System.out.println("1. Insertar medicamento al inicio");
        System.out.println("2. Insertar medicamento al final");
        System.out.println("3. Editar medicamento");
        System.out.println("4. Eliminar medicamento");
        System.out.println("5. Mostrar todos los medicamentos");
        System.out.println("6. Mostrar reporte de costos");
        System.out.println("7. Salir");
        System.out.print("Seleccione una opción: ");
    }

    private static void insertarMedicamento(boolean alInicio) {
        System.out.print("Código del producto: ");
        int codigoProducto = sc.nextInt();
        sc.nextLine();

        System.out.print("Nombre: ");
        String nombre = sc.nextLine();

        System.out.print("Categoría: ");
        String categoria = sc.nextLine();

        System.out.print("Cantidad: ");
        int cantidad = sc.nextInt();
        sc.nextLine();

        System.out.print("Precio: ");
        double precio = sc.nextDouble();
        sc.nextLine();

        // Validación de fecha
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

        System.out.println("Instrucciones: ");
        String instrucciones = sc.nextLine();

        System.out.println("Efectos Secundarios: ");
        String efectosSecundarios = sc.nextLine();

        Medicamento m = new Medicamento(

                nombre, categoria,
                fecha, cantidad, precio,
                codigoProducto, instrucciones, efectosSecundarios
        );

        System.out.print("¿Desea agregar imágenes a este medicamento? (s/n): ");
        String resp = sc.nextLine();
        if (resp.equalsIgnoreCase("s")) {
            gestionarImagenes(m);
        }

        if (alInicio) {
            listaMedicamentos.insertarInicio(m);
        } else {
            listaMedicamentos.insertarFinal(m);
        }
        System.out.println("Medicamento agregado.");
    }

    private static void editarMedicamento() {
        System.out.print("Ingrese el nombre del medicamento a editar: ");
        String nombreEditar = sc.nextLine();
        Medicamento medEditar = listaMedicamentos.buscarMedicamento(nombreEditar);

        if (medEditar != null) {
            System.out.println("--- Editando Medicamento ---");

            System.out.print("Nuevo nombre (" + medEditar.getNombre() + "): ");
            String nuevoNombre = sc.nextLine();
            if (!nuevoNombre.isEmpty()) medEditar.setNombre(nuevoNombre);

            System.out.print("Nueva categoría (" + medEditar.getCategoria() + "): ");
            String nuevaCategoria = sc.nextLine();
            if (!nuevaCategoria.isEmpty()) medEditar.setCategoria(nuevaCategoria);

            System.out.print("Nueva fecha de vencimiento (yyyy-MM-dd) (" + medEditar.getFechaVencimiento() + "): ");
            String fechaStr = sc.nextLine();
            if (!fechaStr.isEmpty()) {
                try {
                    LocalDate nuevaFecha = LocalDate.parse(fechaStr);
                    medEditar.setFechaVencimiento(nuevaFecha);
                } catch (Exception e) {
                    System.out.println("Fecha inválida, no se modificó.");
                }
            }

            System.out.print("Nueva cantidad (" + medEditar.getCantidad() + "): ");
            String cantStr = sc.nextLine();
            if (!cantStr.isEmpty()) {
                try {
                    medEditar.setCantidad(Integer.parseInt(cantStr));
                } catch (NumberFormatException e) {
                    System.out.println("Cantidad inválida, no se modificó.");
                }
            }

            System.out.print("Nuevo precio (" + medEditar.getPrecio() + "): ");
            String precioStr = sc.nextLine();
            if (!precioStr.isEmpty()) {
                try {
                    medEditar.setPrecio(Double.parseDouble(precioStr));
                } catch (NumberFormatException e) {
                    System.out.println("Precio inválido, no se modificó.");
                }
            }

            System.out.print("Nuevas instrucciones (" + medEditar.getInstrucciones() + "): ");
            String instr = sc.nextLine();
            if (!instr.isEmpty()) medEditar.setInstrucciones(instr);

            System.out.print("Nuevos efectos secundarios (" + medEditar.getEfectosSecundarios() + "): ");
            String efectos = sc.nextLine();
            if (!efectos.isEmpty()) medEditar.setEfectosSecundarios(efectos);

            System.out.print("¿Desea gestionar imágenes para este medicamento? (s/n): ");
            String resp = sc.nextLine();
            if (resp.equalsIgnoreCase("s")) {
                gestionarImagenes(medEditar);
            }


            System.out.println("Medicamento actualizado correctamente.");
        } else {
            System.out.println("El medicamento no fue encontrado.");
        }
    }


    private static void eliminarMedicamento() {
        System.out.print("Ingrese el nombre del medicamento a eliminar: ");
        String nombreEliminar = sc.nextLine();
        listaMedicamentos.eliminarMedicamento(nombreEliminar);
    }

    private static void mostrarMedicamentos() {
        listaMedicamentos.mostrarLista();
    }

    private static void mostrarReporteCostos() {
        double total = listaMedicamentos.calcularCostoTotal();
        System.out.println("Costo total del inventario: " + total);
    }


    private static void gestionarImagenes(Medicamento med) {
        int opcion;
        do {
            System.out.println("\n--- Gestión de imágenes para: " + med.getNombre() + " ---");
            System.out.println("1. Agregar imagen");
            System.out.println("2. Eliminar imagen");
            System.out.println("3. Mostrar imágenes");
            System.out.println("0. Volver");
            System.out.print("Seleccione una opción: ");
            opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {
                case 1 -> {
                    System.out.print("Ingrese la ruta o URL de la imagen: ");
                    String ruta = sc.nextLine();
                    med.agregarImagen(ruta);
                    System.out.println("Imagen agregada.");
                }
                case 2 -> {
                    med.mostrarImagenes();
                    System.out.print("Ingrese la ruta de la imagen a eliminar: ");
                    String rutaEliminar = sc.nextLine();
                    med.eliminarImagen(rutaEliminar);
                    System.out.println("Imagen eliminada.");
                }
                case 3 -> med.mostrarImagenes();
                case 0 -> System.out.println("Volviendo...");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }

}
