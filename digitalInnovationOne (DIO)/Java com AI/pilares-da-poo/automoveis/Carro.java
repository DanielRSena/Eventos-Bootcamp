package automoveis;

public class Carro extends Veiculo {
    public void ligar() {
        conferirCombustivel();
        System.out.println("Ligando o carro...");
        System.out.println("Carro ligado!");
    }

    private void conferirCombustivel() {
        System.out.println("Conferindo o combustível...");
    }
}
