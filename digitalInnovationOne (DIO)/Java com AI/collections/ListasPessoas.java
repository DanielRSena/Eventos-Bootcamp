import objs.listas.pessoas.*;

public class ListasPessoas {
    
    public static void main(String[] args) {
        OrdenacaoPessoa op = new OrdenacaoPessoa();

        op.adicionarPessoa("João", 20, 1.80);
        op.adicionarPessoa("Maria", 22, 1.65);
        op.adicionarPessoa("Pedro", 18, 1.90);

        System.out.println("\n\nLista Pessoas (sem ordenar)");
        for (Pessoa pessoa : op.getPessoas())
            System.out.println(pessoa.toString());

        System.out.println("\n\nLista Pessoas (ordenada por idade)");
        for (Pessoa pessoa : op.ordenarPorIdade())
            System.out.println(pessoa.toString());

        System.out.println("\n\nLista Pessoas (ordenada por altura)");
        for (Pessoa pessoa : op.ordenarPorAltura())
            System.out.println(pessoa.toString());

        System.out.println("\n");
    }
}