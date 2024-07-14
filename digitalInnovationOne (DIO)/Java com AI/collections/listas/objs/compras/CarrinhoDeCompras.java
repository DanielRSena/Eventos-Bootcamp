package objs.compras;

import java.util.List;
import java.util.ArrayList;

public class CarrinhoDeCompras {
    private List <Item> itens;

    public void adicionarItem(String nome, double preco, int quantidade) {
        Item item = new Item(nome, preco, quantidade);
        if(itens == null) itens = new ArrayList<>();

        if(item.getQuantidade() == 0) {
            System.out.println("Quantidade inválida!");
            return;
        }

        if(item.getPreco() < 0) {
            System.out.println("Preço inválido!");
            return;
        }

        for(Item i : itens) {
            if(i.getNome().equalsIgnoreCase(item.getNome())) {
                i.setQuantidade(i.getQuantidade() + item.getQuantidade());
                return; //aqui, sempre que o objeto tiver o mesmo nome, o valor dele passa a ser o do produto antigo.
            }
        }
        itens.add(item);                
    }

    public void removerItem(String nome) {
        for(Item i : itens) {
            if(i.getNome().equalsIgnoreCase(nome)) {
                itens.remove(i);
                return;
            }
        }
    }

    public double calcularTotal() {
        double total = 0;
        for(Item i : itens) total += i.getPreco() * i.getQuantidade();

        if(total == 0) System.out.println("Carrinho vazio!");
        else total = Math.round(total * 100.0) / 100.0;

        return total;
    }

    public void emitirNotaFiscal() {
        if (itens == null) {
            System.out.println("\nCarrinho vazio!\n");
            return;
        }

        System.out.println("\n\t-- NFE - Loja do GamerVal --\n");
        for(Item i : itens) System.out.println(i.getNome() + " | " + i.getPreco() + " | " + i.getQuantidade());
        System.out.println("\n\tTotal: " + calcularTotal());
    }

    public void mostrarItens() {
        if (itens == null) {
            System.out.println("\nCarrinho vazio!\n");
            return;
        }

        for(Item i : itens) 
            System.out.println(i.getNome() + " | " + i.getPreco() + " | " + i.getQuantidade());
    }
}