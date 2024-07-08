package automoveis;
public class Moto {

    public void ligar() {
        conferirCombustivel();
        System.out.println("Ligando a moto...");
        System.out.println("Moto ligada!");
    }

    private void conferirCombustivel() {
        System.out.println("Conferindo o combustível...");
    }
}