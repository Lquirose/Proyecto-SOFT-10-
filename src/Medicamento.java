import java.time.LocalDate;
import java.util.ArrayList;

public class Medicamento {

    private String nombre;
    private double precio;
    private String categoria;
    private LocalDate fechaVencimiento;
    private int cantidad;
    private String descripcion;
    private String instrucciones;
    private String efectosSecundarios;
    private String restricciones;
    private int codigoProducto;

    private final ArrayList<String> listaImagenes;

    public Medicamento(String nombre, String categoria, String descripcion,
                       LocalDate fechaVencimiento, int cantidad, double precio,
                       int codigoProducto, String instrucciones,
                       String efectosSecundarios, String restricciones) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.descripcion = descripcion;
        this.fechaVencimiento = fechaVencimiento;
        this.cantidad = cantidad;
        this.precio = precio;
        this.codigoProducto = codigoProducto;
        this.instrucciones = instrucciones;
        this.efectosSecundarios = efectosSecundarios;
        this.restricciones = restricciones;
        this.listaImagenes = new ArrayList<>();
    }

    public Medicamento(String nombre, String categoria, String descripcion,
                       LocalDate fechaVencimiento, int cantidad, double precio,
                       String instrucciones, String efectosSecundarios, String restricciones) {
        this(nombre, categoria, descripcion, fechaVencimiento, cantidad, precio,
                0, instrucciones, efectosSecundarios, restricciones);
    }

    // ===== Métodos para imágenes =====
    public void agregarImagen(String rutaImagen) {
        listaImagenes.add(rutaImagen);
    }

    public void eliminarImagen(String rutaImagen) {
        listaImagenes.remove(rutaImagen);
    }

    public ArrayList<String> getListaImagenes() {
        return listaImagenes;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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

    public String getRestricciones() {
        return restricciones;
    }

    public void setRestricciones(String restricciones) {
        this.restricciones = restricciones;
    }

    public int getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(int codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    // ===== toString =====
    @Override
    public String toString() {
        return "Código Producto: " + codigoProducto +
                "\nNombre: " + nombre +
                "\nDescripción: " + descripcion +
                "\nPrecio: " + precio +
                "\nCategoría: " + categoria +
                "\nCantidad: " + cantidad +
                "\nFecha de Vencimiento: " + fechaVencimiento +
                "\nInstrucciones: " + instrucciones +
                "\nEfectos Secundarios: " + efectosSecundarios +
                "\nRestricciones: " + restricciones +
                "\nImágenes: " + listaImagenes;
    }
}
