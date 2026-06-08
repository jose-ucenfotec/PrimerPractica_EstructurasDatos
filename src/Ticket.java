import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Ticket{
    //Atributos
    private String descripcion;
    private String nombreCompleto;
    private String prioridad;
    private LocalDate fechaCreacion;
    private LocalDate fechaResolucion;
    private static int cantidad = 0;
    private int id;

    //Constructor
    public Ticket(String descripcion, String nombreCompleto, String prioridad){
        this.descripcion = descripcion;
        this.nombreCompleto = nombreCompleto;
        this.prioridad = prioridad;
        this.fechaCreacion = LocalDate.now();
        this.fechaResolucion = null;
        this.id = ++cantidad;
    }

    //Getters
    public String getDescripcion(){
        return descripcion;
    }
    public String getNombreCompleto(){
        return nombreCompleto;
    }
    public String getPrioridad(){
        return prioridad;
    }
    public LocalDate getFechaCreacion(){
        return fechaCreacion;
    }
    public LocalDate getFechaResolucion(){
        return fechaResolucion;
    }
    public int getId(){
        return id;
    }
    public static int getCantidad(){
        return cantidad;
    }

    //Setters
    public void setDescripcion(String descripcion){
        this.descripcion = descripcion;
    }
    public void setNombreCompleto(String nombreCompleto){
        this.nombreCompleto = nombreCompleto;
    }
    public void setPrioridad(String prioridad){
        this.prioridad = prioridad;
    }
    public void setFechaResolucion(LocalDate fechaResolucion){
        this.fechaResolucion = fechaResolucion;
    }

    //toString
    public String toString(){
        DateTimeFormatter formatear = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String fechaRes;
        if(fechaResolucion == null){
            fechaRes = "Pendiente";
        }else{
            fechaRes = fechaResolucion.format(formatear);
        }
        return "ID: " + id +
                "\nUsuario: " + nombreCompleto +
                "\nPrioridad: " + prioridad +
                "\nDescripcion: " + descripcion +
                "\nFecha creacion: " + fechaCreacion.format(formatear) +
                "\nFecha resolucion: " + fechaRes;
    }
}
