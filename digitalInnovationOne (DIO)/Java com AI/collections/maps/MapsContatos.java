import objs.AgendaContatos;

public class MapsContatos {
    public static void main(String[] args) {

        AgendaContatos agenda = new AgendaContatos();

        System.out.println("Contatos: ");
        agenda.exibirContatos();

        agenda.adicionarContato("Yuri Alpha", 1);
        agenda.adicionarContato("Yuri Alpha", 12); //sobrescreve o 1
        agenda.adicionarContato("Lupusregina Beta", 121);
        agenda.adicionarContato("Narberal Gamma", 123);

        System.out.println("\nContatos: ");
        agenda.exibirContatos();
    }
}