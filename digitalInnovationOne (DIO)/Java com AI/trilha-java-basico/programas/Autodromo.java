import classes.automoveis.Carro;

public class Autodromo {

    public static void main(String[] args) {
        System.out.println("Sistema de Autodromo!");

        Carro onix = new Carro();

        onix.ligar();

        onix.setChassi("1234");
        System.out.println(onix.getChassi());

    }
}