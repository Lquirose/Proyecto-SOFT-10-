public class Cliente {
    private String idCliente;
    private String nombre;
    private String correo;
    private String telefono;
    private String direccion;
    private ListaEnlazadaSimple carrito;


    public Cliente(String idCliente, String nombre, String correo, String telefono, String direccion) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.correo = correo;
        this.telefono = telefono;
        this.direccion = direccion;
        this.carrito = new ListaEnlazadaSimple();
    }

    // Getters y setters
    public String getIdCliente() { return idCliente; }
    public void setIdCliente(String idCliente) { this.idCliente = idCliente; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public ListaEnlazadaSimple getCarrito() { return carrito; }
    public void setCarrito(ListaEnlazadaSimple carrito) { this.carrito = carrito; }

    @Override
    public String toString() {
        return "Nombre:" + nombre+
                "\nidCliente='" + idCliente +
                "\ncorreo='" + correo +
                "\ntelefono='" + telefono +
                "\ndireccion='" + direccion;
    }
}
