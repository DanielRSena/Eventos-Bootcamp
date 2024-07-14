import objs.convidados.ConjuntoConvidados;

public class SetsConvidados {
    public static void main(String[] args) {
        
        ConjuntoConvidados conjuntoConvidados = new ConjuntoConvidados();

        conjuntoConvidados.adicionarConvidado("Yuri Alpha", 1);
        conjuntoConvidados.adicionarConvidado("Lupusregina Beta", 2);
        conjuntoConvidados.adicionarConvidado("Narberal Gamma", 2);

        conjuntoConvidados.mostrarConvidados(); //perceba que ele printa de forma aleatória
        System.out.println("Total de convidados: " + conjuntoConvidados.contarConvidados());
        System.out.println("\n");

        conjuntoConvidados.removerConvidado(2);
        conjuntoConvidados.mostrarConvidados();
        System.out.println("Total de convidados: " + conjuntoConvidados.contarConvidados());
        System.out.println("\n");

    }
}