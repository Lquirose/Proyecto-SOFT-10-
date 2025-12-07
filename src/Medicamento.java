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

    private final ArrayList<String> listaImagenes;

    // Para lista enlazada (si lo usas)
    private Medicamento siguiente;

    public Medicamento(String nombre, String categoria,
                       LocalDate fechaVencimiento, int cantidad, double precio,
                       String instrucciones, String efectosSecundarios) {

        this.nombre = nombre;
        this.categoria = categoria;
        this.fechaVencimiento = fechaVencimiento;
        this.cantidad = cantidad;
        this.precio = precio;
        this.instrucciones = instrucciones;
        this.efectosSecundarios = efectosSecundarios;
        this.listaImagenes = new ArrayList<>();
    }

    // Constructor usado para carrito
    public Medicamento(String nombre, double precio,
                       int cantidadComprada, String categoria) {

        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidadComprada;
        this.categoria = categoria;

        this.fechaVencimiento = null;
        this.instrucciones = "";
        this.efectosSecundarios = "";

        this.listaImagenes = new ArrayList<>();
        this.siguiente = null;
    }

    // Getters y Setters
    public Medicamento getSiguiente() { return siguiente; }
    public void setSiguiente(Medicamento siguiente) { this.siguiente = siguiente; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public LocalDate getFechaVencimiento() { return fechaVencimiento; }
    public void setFechaVencimiento(LocalDate fechaVencimiento) { this.fechaVencimiento = fechaVencimiento; }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    public String getInstrucciones() { return instrucciones; }
    public void setInstrucciones(String instrucciones) { this.instrucciones = instrucciones; }

    public String getEfectosSecundarios() { return efectosSecundarios; }
    public void setEfectosSecundarios(String efectosSecundarios) { this.efectosSecundarios = efectosSecundarios; }

    public ArrayList<String> getListaImagenes() { return listaImagenes; }

    public void agregarImagen(String rutaImagen) { listaImagenes.add(rutaImagen); }
    public void eliminarImagen(String rutaImagen) { listaImagenes.remove(rutaImagen); }

    @Override
    public String toString() {
        return "Nombre: " + nombre +
                "\nPrecio: " + precio +
                "\nCategoría: " + categoria +
                "\nCantidad: " + cantidad +
                "\nFecha de Vencimiento: " + fechaVencimiento +
                "\nInstrucciones: " + instrucciones +
                "\nEfectos Secundarios: " + efectosSecundarios +
                "\nImágenes: " + listaImagenes;
    }
}
