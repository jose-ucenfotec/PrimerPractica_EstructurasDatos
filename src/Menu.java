import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Menu{
    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private ListaEnlazada listaTickets;
    private ColaPrioridad colaTickets;
    private DateTimeFormatter formatoFecha;

    //Constructor
    public Menu(ColaPrioridad colaTickets, ListaEnlazada listaTickets){
        this.colaTickets = colaTickets;
        this.listaTickets = listaTickets;
        this.formatoFecha = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    }

    //Menu principal
    public void menuPrincipal() throws IOException{
        int opcion = -1;
        while(opcion != 0){
            System.out.println("\n--SISTEMA DE TICKETS--");
            System.out.println("1. Menu de Usuario");
            System.out.println("2. Menu de Administrador");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opcion: ");
            opcion = Integer.parseInt(reader.readLine());

            if(opcion == 1){
                menuUsuario();
            }else if(opcion == 2){
                menuAdministrador();
            }else if(opcion == 0){
                System.out.println("Saliendo del programa");
            }else{
                System.out.println("Opcion invalida");
            }
        }
    }

    //Menu de usuario
    private void menuUsuario() throws IOException{
        int opcion = -1;
        while(opcion != 0){
            System.out.println("\n--MENU USUARIO--");
            System.out.println("1. Crear ticket");
            System.out.println("2. Buscar ticket");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opcion: ");
            opcion = Integer.parseInt(reader.readLine());

            if(opcion == 1){
                crearTicket();
            }else if(opcion == 2){
                buscarTicket();
            }else if(opcion == 0){
                System.out.println("Volviendo al menu general...\n");
            }else{
                System.out.println("Opcion invalida");
            }
        }
    }

    //Menu de administrador
    private void menuAdministrador() throws IOException{
        int opcion = -1;
        while(opcion != 0){
            System.out.println("\n--MENU ADMINISTRADOR--");
            System.out.println("1. Ver ticket al frente");
            System.out.println("2. Resolver ticket");
            System.out.println("3. Ver tickets resueltos");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opcion: ");
            opcion = Integer.parseInt(reader.readLine());

            if(opcion == 1){
                verFrente();
            }else if(opcion == 2){
                resolverTicket();
            }else if(opcion == 3){
                listaTickets.mostrarLista();
            }else if(opcion == 0){
                System.out.println("Volviendo al menu general...\n");
            }else{
                System.out.println("Opcion invalida");
            }
        }
    }

    //Imprime la informacion de un ticket
    private void imprimirTicket(Ticket ticket){
        System.out.println(ticket.toString());
    }

    //Crea un nuevo ticket y lo inserta en la cola de prioridad
    private void crearTicket() throws IOException{
        System.out.println("\n--CREAR TICKET--");
        System.out.print("Nombre completo: ");
        String nombre = reader.readLine();

        System.out.print("Descripcion del problema: ");
        String descripcion = reader.readLine();

        String prioridad = "";
        while(!prioridad.equals("alta") && !prioridad.equals("media") && !prioridad.equals("baja")){
            System.out.print("Prioridad (alta/media/baja): ");
            prioridad = reader.readLine().toLowerCase();
            if(!prioridad.equals("alta") && !prioridad.equals("media") && !prioridad.equals("baja")){
                System.out.println("Prioridad invalida, ingresela de nuevo");
            }
        }

        Ticket nuevoTicket = new Ticket(descripcion, nombre, prioridad);
        colaTickets.insertar(nuevoTicket);
        System.out.println("\nTicket creado exitosamente");
        imprimirTicket(nuevoTicket);
    }

    //Busca un ticket resuelto en la lista enlazada por su ID
    private void buscarTicket() throws IOException{
        System.out.println("\n--BUSCAR TICKET--");
        System.out.print("Ingrese el ID del ticket: ");
        int id = Integer.parseInt(reader.readLine());

        NodoLista resultado = listaTickets.buscar(id);
        if(resultado != null){
            imprimirTicket(resultado.getTicket());
        }
    }

    //Muestra el ticket al frente de la cola sin removerlo
    private void verFrente(){
        System.out.println("\n--Ticket con mayor prioridad--");
        Ticket frente = colaTickets.verFrente();
        if(frente != null){
            imprimirTicket(frente);
        }
    }

    //Resuelve el ticket al frente de la cola y lo pasa a la lista de resueltos
    private void resolverTicket() throws IOException{
        System.out.println("\n--RESOLVER TICKET--");
        Ticket frente = colaTickets.verFrente();
        if(frente == null){
            return;
        }
        System.out.println("Ticket a resolver: ");
        imprimirTicket(frente);

        LocalDate fechaResolucion = null;
        while(fechaResolucion == null){
            System.out.print("\nIngrese la fecha de resolucion (yyyy/MM/dd): ");
            String entrada = reader.readLine();
            try{
                fechaResolucion = LocalDate.parse(entrada, formatoFecha);
            }catch(DateTimeParseException e){
                System.out.println("Formato invalido. Ejemplo: 2026/06/25");
            }
        }

        Ticket ticketResuelto = colaTickets.remover();
        ticketResuelto.setFechaResolucion(fechaResolucion);
        listaTickets.insertarNodoFinal(ticketResuelto);
        System.out.println("\nTicket resuelto. Informacion actualizada:");
        imprimirTicket(ticketResuelto);
    }
}
