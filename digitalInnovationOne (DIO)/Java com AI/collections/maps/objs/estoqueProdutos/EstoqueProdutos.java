package objs.estoqueProdutos;

import java.util.HashMap;
import java.util.Map;

public class EstoqueProdutos {
    private Map<Long, Produto> estoqueProdutosMap;

    public EstoqueProdutos() {
        this.estoqueProdutosMap = new HashMap<>();
    }

    public void adicionarProduto(long cod, String nome, double preco, int quantidade) {
        this.estoqueProdutosMap.put(cod, new Produto(nome, preco, quantidade));
    }

    public void getEstoqueProdutosMap() {
        System.out.println(this.estoqueProdutosMap);
    }

    public double calcularTotal() {
        double total = 0;
        if(!this.estoqueProdutosMap.isEmpty()) 
            for (Produto p : estoqueProdutosMap.values())
                total += p.getPreco() * p.getQuantidade();
        else System.out.println("Estoque vazio");
        return total;
    }

    public Produto getProdutoMaisCaro() {
        Produto maisCaro = null;
        double maiorPreco = Double.MIN_VALUE;

        if(!this.estoqueProdutosMap.isEmpty())
            for (Produto p : estoqueProdutosMap.values())
                if(p.getPreco() > maiorPreco)
                    maisCaro = p;
        return maisCaro;
    }
}