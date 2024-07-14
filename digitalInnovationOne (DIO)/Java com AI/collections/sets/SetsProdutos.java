import objs.produtos.*;

public class SetsProdutos {
    public static void main(String[] args) {
        
        CadastroProdutos cadastroProdutos = new CadastroProdutos();
        cadastroProdutos.adicionar(1, "Caneta", 1.99, 20);
        cadastroProdutos.adicionar(2, "Lapis", 0.99, 20);
        cadastroProdutos.adicionar(3, "Borracha", 3.99, 20);
        cadastroProdutos.adicionar(3, "Caderno", 22.99, 20);

        for (Produto prod : cadastroProdutos.exibirProdutosPorPreco())
            System.out.println(prod.toString());

        System.out.println("\n");

        for (Produto prod : cadastroProdutos.exibirProdutosPorNome())
            System.out.println(prod.toString());

        System.out.println("\n");

        cadastroProdutos.exibirProdutos();
    }
}