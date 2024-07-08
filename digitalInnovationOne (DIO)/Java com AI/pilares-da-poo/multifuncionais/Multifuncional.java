package multifuncionais;

public class Multifuncional implements Impressora, Digitalizadora, Copiadora {
//Java não permite múltipla herança, então não posso incluir mais de um construtor na classe.

    public void imprimir() {
        System.out.println("Imprimindo na Multifuncional");
    }

    public void copiar() {
        System.out.println("Copiando na Multifuncional");
    }

    public void digitalizar() {
        System.out.println("Digitalizando na Multifuncional");
    }
}