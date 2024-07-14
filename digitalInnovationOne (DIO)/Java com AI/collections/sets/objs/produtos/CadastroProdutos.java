package objs.produtos;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class CadastroProdutos {
    private Set<Produto> produtos;

    public CadastroProdutos() {
        this.produtos = new HashSet<>();
    }

    public void adicionar(long codigo, String nome, double preco, int quantidade) {
        this.produtos.add(new Produto(codigo, nome, preco, quantidade));
    }

    public void exibirProdutos() {
        for (Produto p : this.produtos) System.out.println(p.toString());
    }

    public Set<Produto> exibirProdutosPorNome() {
        //como o HashSet nao deixar ordenado, temos que usar TreeSet
        Set<Produto> produtosPorNome = new TreeSet<>(produtos);

        //Na classe 'Produto', tem a funcionalidade de comparar por nome, então podemos só retornar o TreeSet
        return produtosPorNome;
    }

    public Set<Produto> exibirProdutosPorPreco() {
        Set<Produto> produtosPorPreco = new TreeSet<>(new ComparatorPorPreco());

        produtosPorPreco.addAll(produtos);
        return produtosPorPreco;
    }
}