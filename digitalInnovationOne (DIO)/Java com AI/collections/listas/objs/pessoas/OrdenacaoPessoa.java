package objs.pessoas;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class OrdenacaoPessoa {

    private List<Pessoa> pessoas;

    public OrdenacaoPessoa() {
        this.pessoas = new ArrayList<>();
    }

    public void adicionarPessoa(String nome, int idade, double altura) {
        this.pessoas.add(new Pessoa(nome, idade, altura));
    }

    public List<Pessoa> getPessoas() {
        return pessoas;
    }

    public List<Pessoa> ordenarPorIdade() {
        List<Pessoa> pessoasPorIdade = new ArrayList<>(this.pessoas);
        Collections.sort(pessoasPorIdade);
        return pessoasPorIdade;
        //ele já sabe que será ordenado por idade por que lá na classe 'Pessoa' tem a funcionalidade de comparar por idade (procure por 'compareTo').
    }

    public List<Pessoa> ordenarPorAltura() {
        List<Pessoa> pessoasPorAltura = new ArrayList<>(this.pessoas);
        Collections.sort(pessoasPorAltura, new ComparatorAltura());
        return pessoasPorAltura;
    }
}