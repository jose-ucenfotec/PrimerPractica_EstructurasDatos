public class ListaEnlazada{
    private NodoLista primero;

    //Constructor
    public ListaEnlazada(){
        primero = null;
    }

    //Getters
    public NodoLista getPrimero(){
        return primero;
    }

    //Setters
    public void setPrimero(NodoLista nuevoPrimero){
        primero = nuevoPrimero;
    }

    //Inserta un ticket resuelto al final de la lista
    public void insertarNodoFinal(Ticket ticket){
        NodoLista nodoInsertar = new NodoLista(ticket);
        if(primero == null){
            setPrimero(nodoInsertar);
            return;
        }
        NodoLista temp = primero;
        while(temp.getSiguiente() != null){
            temp = temp.getSiguiente();
        }
        temp.setSiguiente(nodoInsertar);
    }

    //Busca un ticket resuelto por su ID
    public NodoLista buscar(int id){
        if(id <= 0 || id > Ticket.getCantidad()){
            System.out.println("No existe ningun ticket con el ID " + id);
            return null;
        }
        NodoLista temp = primero;
        while(temp != null && temp.getTicket().getId() != id){
            temp = temp.getSiguiente();
        }
        if(temp == null){
            System.out.println("El ticket con ID " + id + " esta pendiente");
        }else{
            System.out.println("Ticket resuelto encontrado");
        }
        return temp;
    }

    //Muestra todos los tickets resueltos
    public void mostrarLista(){
        if(primero == null){
            System.out.println("No hay tickets resueltos");
            return;
        }
        NodoLista temp = primero;
        int contador = 1;
        while(temp != null){
            System.out.println("Ticket " + contador + ": " + temp.getTicket());
            temp = temp.getSiguiente();
            contador++;
        }
    }

    //toString
    public String toString(){
        return "Primer ticket: " + primero;
    }
}
