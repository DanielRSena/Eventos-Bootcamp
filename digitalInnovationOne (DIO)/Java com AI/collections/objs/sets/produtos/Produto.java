package objs.sets.produtos;

import java.util.Comparator;

public class Produto implements Comparable<Produto> {
    private long codigo;
    private String nome;
    private double preco;
    private int quantidade;

    public Produto(long codigo, String nome, double preco, int quantidade) {
        this.codigo = codigo;
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    public long getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    @Override
    public String toString() {
        return  getCodigo() + " | " + nome + " | " + preco + " | " + quantidade;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Produto produto)) return false;
        return getCodigo() == produto.getCodigo();
    }

    @Override  
    public int hashCode() {
        return Long.hashCode(codigo);
    }

    @Override
    public int compareTo(Produto outroProduto) {
        return nome.compareToIgnoreCase(outroProduto.getNome());
    }
}

class ComparatorPorPreco implements Comparator<Produto> {
    @Override
    public int compare(Produto p1, Produto p2) {
        return Double.compare(p1.getPreco(), p2.getPreco());
    }
}