import java.util.ArrayList;

public class ColaPrioridad{
    private ArrayList<Ticket> colaTickets;

    //Constructor
    public ColaPrioridad(){
        colaTickets = new ArrayList<>();
    }

    //Retorna un valor numerico segun la prioridad del ticket
    private int valorPrioridad(String prioridad){
        switch(prioridad.toLowerCase()){
            case "alta":
                return 3;
            case "media":
                return 2;
            case "baja":
                return 1;
            default:
                return 0;
        }
    }

    //Inserta el ticket en la posicion correcta segun su prioridad
    public void insertar(Ticket ticket){
        if(colaTickets.isEmpty()){
            colaTickets.add(ticket);
            return;
        }
        int prioridadNuevo = valorPrioridad(ticket.getPrioridad());
        int i = 0;
        while(i < colaTickets.size() && prioridadNuevo <= valorPrioridad(colaTickets.get(i).getPrioridad())){
            i++;
        }
        colaTickets.add(i, ticket);
    }

    //Remueve y retorna el ticket del frente (mayor prioridad)
    public Ticket remover(){
        if(colaTickets.isEmpty()){
            System.out.println("No existen tickets en cola");
            return null;
        }
        return colaTickets.removeFirst();
    }

    //Retorna el ticket del frente sin removerlo
    public Ticket verFrente(){
        if(colaTickets.isEmpty()){
            System.out.println("No existen tickets en cola");
            return null;
        }
        return colaTickets.getFirst();
    }

    public int getTamanio(){
        return colaTickets.size();
    }
}
