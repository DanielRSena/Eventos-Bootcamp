import objs.listas.compras.CarrinhoDeCompras;

public class ListasCompras {
    public static void main(String[] args) {
        // Carrinho de compras
        CarrinhoDeCompras carrinho = new CarrinhoDeCompras();

        carrinho.adicionarItem("Gabinete RGB GamerVal", 159.00, 2);
        carrinho.adicionarItem("Mouse Gamer RGB", 52.90, 1);
        carrinho.adicionarItem("Caneca com asas Aesthetic", 69.90, 1);
        carrinho.adicionarItem("Moletom GamerVal BXD", 258.99, 1);
        carrinho.adicionarItem("Moletom GamerVal BXD", 9.99, 4);

        carrinho.mostrarItens();
        System.out.println("Total: " + carrinho.calcularTotal() + "\n");
        carrinho.removerItem("Moletom GamerVal BXD");

        carrinho.mostrarItens();
        System.out.println("Total: " + carrinho.calcularTotal() + "\n");

        carrinho.emitirNotaFiscal();
    }
}