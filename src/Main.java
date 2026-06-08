import java.io.IOException;

public class Main{
    public static void main(String[] args) throws IOException{
        ColaPrioridad colaTickets = new ColaPrioridad();
        ListaEnlazada listaTickets = new ListaEnlazada();
        Menu menu = new Menu(colaTickets, listaTickets);
        menu.menuPrincipal();
    }
}