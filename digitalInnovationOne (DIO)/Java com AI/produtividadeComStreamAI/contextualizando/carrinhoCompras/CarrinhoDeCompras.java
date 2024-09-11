package produtividadeComStreamAI.contextualizando.carrinhoCompras;

import java.util.ArrayList;
import java.util.List;

public class CarrinhoDeCompras {
    private List<Item> itens;

    public CarrinhoDeCompras() {
        this.itens = new ArrayList<>();
    }

    public double calcularTotal() {
        if(!itens.isEmpty()) {
            double total = 0;
            for (Item item : itens) {
                total += item.getPreco() * item.getQuantidade();
            }
            return total;
        }
        else throw new RuntimeException("Carrinho vazio");
    }
    //essa é uma forma imperativa, preocupamos em ter que se preocupar com tudo o que acontece, bem como a ordem de execução.

    public double calcularTotal2() {
        if(!itens.isEmpty()) {
            return itens.stream().mapToDouble(item -> item.getPreco() * item.getQuantidade()).sum();
        }
        else throw new RuntimeException("Carrinho vazio");
    }
    // nesse caso, o stream serve para pegar cada item e fazer a multiplicação e no final somar tudo. Essa é a forma declarativa, o paradigma de programação funcional/declarativa.

    // essa -> é a expressão lambda, que é uma função anonima, que pode ser escrita de forma mais simples e mais curta. Ela significa algo como "para cada item do itens, faca isso".
}