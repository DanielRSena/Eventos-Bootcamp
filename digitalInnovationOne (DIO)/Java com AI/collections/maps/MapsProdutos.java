import objs.estoqueProdutos.EstoqueProdutos;

public class MapsProdutos {
    public static void main(String[] args) {

        EstoqueProdutos cadastroProdutos = new EstoqueProdutos();

        cadastroProdutos.getEstoqueProdutosMap();

        System.out.println();

        cadastroProdutos.adicionarProduto(1, "Caneta", 1.99, 20);
        cadastroProdutos.adicionarProduto(2, "Lapis", 0.99, 20);
        cadastroProdutos.adicionarProduto(3, "Borracha", 3.99, 20);

        cadastroProdutos.getEstoqueProdutosMap(); //observe que em list e set, usa-se [], e maps {}, como se fosse um dicionário

        System.out.println();

        System.out.println("Total do estoque: " + cadastroProdutos.calcularTotal());

        System.out.println();

        System.out.println("Produto mais caro: " + cadastroProdutos.getProdutoMaisCaro());

        System.out.println();
    }
}