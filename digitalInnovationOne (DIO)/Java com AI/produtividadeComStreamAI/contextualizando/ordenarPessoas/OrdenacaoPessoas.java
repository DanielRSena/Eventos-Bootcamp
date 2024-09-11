package produtividadeComStreamAI.contextualizando.ordenarPessoas;

import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;


public class OrdenacaoPessoas {
    
    private List<Pessoa> pessoas;

    public OrdenacaoPessoas() {
        this.pessoas = new ArrayList<>();
    }

    public void adicionarPessoa(String nome, int idade, double altura) {
        this.pessoas.add(new Pessoa(nome, idade, altura));
    }

    public List<Pessoa> getPessoas() {
        return pessoas;
    }

    public List<Pessoa> ordenarPorIdade() {
        if(pessoas != null) {
            List<Pessoa> pessoasPorIdade = new ArrayList<>(pessoas);
            pessoasPorIdade.sort((p1, p2) -> Integer.compare(p1.getIdade(), p2.getIdade()));
            return pessoasPorIdade;
        }
        else throw new RuntimeException("Lista de pessoas vazia");
    }


    /*
    Reference

    - Método desde o Java 8 que permite fazer a referência a um outro método ou construtor de uma classe de forma funcional, e assim indicar que ele deve ser utilizado num ponto específico de código, o que deixa tudo mais simples e legível.

    - Para usar, você informa uma classe ou referencia seguida de dois pontos :: e o nome do método (sem os parênteses).
    */

    public List<Pessoa> ordenarPorAltura() {
        if(pessoas == null) throw new RuntimeException("Lista de pessoas vazia");
        else {
            List<Pessoa> pessoasPorAltura = new ArrayList<>(pessoas);
            pessoasPorAltura.sort(Comparator.comparingDouble(Pessoa::getAltura));
            return pessoasPorAltura;
        }
    }
}

//https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/Comparator.html