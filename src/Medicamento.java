import java.time.LocalDate;
import java.util.ArrayList;

public class Medicamento {

    private String nombre;
    private double precio;
    private String categoria;
    private LocalDate fechaVencimiento;
    private int cantidad;
    private String instrucciones;
    private String efectosSecundarios;
    private int codigoProducto;

    private final ArrayList<String> listaImagenes;

    //Modificacion tomando en cuenta la retroalimentación de primer avance
    // se elimina la clase nodo y se deja en medicamentos (producto)
    private Medicamento siguiente;

    public Medicamento(String nombre, String categoria,
                       LocalDate fechaVencimiento, int cantidad, double precio,
                       int codigoProducto, String instrucciones,
                       String efectosSecundarios) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.fechaVencimiento = fechaVencimiento;
        this.cantidad = cantidad;
        this.precio = precio;
        this.codigoProducto = codigoProducto;
        this.instrucciones = instrucciones;
        this.efectosSecundarios = efectosSecundarios;
        this.listaImagenes = new ArrayList<>();
    }

    public Medicamento(String nombre, String categoria,
                       LocalDate fechaVencimiento, int cantidad, double precio,
                       String instrucciones, String efectosSecundarios) {
        this(nombre, categoria, fechaVencimiento, cantidad, precio,
                0, instrucciones, efectosSecundarios);
    }


    public ArrayList<String> getListaImagenes() {
        return listaImagenes;
    }

    //Getter y Setter del Nodo


    public Medicamento getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Medicamento siguiente) {
        this.siguiente = siguiente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getInstrucciones() {
        return instrucciones;
    }

    public void setInstrucciones(String instrucciones) {
        this.instrucciones = instrucciones;
    }

    public String getEfectosSecundarios() {
        return efectosSecundarios;
    }

    public void setEfectosSecundarios(String efectosSecundarios) {
        this.efectosSecundarios = efectosSecundarios;
    }

    public int getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(int codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre +
                "\nCódigo Producto: " + codigoProducto +
                "\nPrecio: " + precio +
                "\nCategoría: " + categoria +
                "\nCantidad: " + cantidad +
                "\nFecha de Vencimiento: " + fechaVencimiento +
                "\nInstrucciones: " + instrucciones +
                "\nEfectos Secundarios: " + efectosSecundarios +
                "\nImágenes: " + listaImagenes;
    }

    public void agregarImagen(String rutaImagen) {
        listaImagenes.add(rutaImagen);
    }

    public void eliminarImagen(String rutaImagen) {
        listaImagenes.remove(rutaImagen);
    }

    public void mostrarImagenes() {
        if (listaImagenes.isEmpty()) {
            System.out.println("No hay imágenes registradas para este medicamento.");
        } else {
            System.out.println("Imágenes del medicamento:");
            for (int i = 0; i < listaImagenes.size(); i++) {
                System.out.println((i + 1) + ". " + listaImagenes.get(i));
            }
        }
    }

}
