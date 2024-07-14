import objs.contatos.*;

public class SetsContatos {
    public static void main(String[] args) {
        AgendaContatos agenda = new AgendaContatos();

        agenda.adicionarContato("Yuri Alpha", 1);
        agenda.adicionarContato("Yuri Alpha", 12);
        agenda.adicionarContato("Lupusregina Beta", 121);
        agenda.adicionarContato("Narberal Gamma", 123);

        System.out.println("\nContatos: ");
        agenda.exibirContatos();

        System.out.println("\nBuscando por nome: ");
        for (Contato cont : agenda.procurarPorNome("Yuri")) {
            System.out.println(cont.toString());
        }

        agenda.alterarContato("Lupusregina Beta", 12);
        
        System.out.println("\nContatos: ");
        agenda.exibirContatos();
    }
}