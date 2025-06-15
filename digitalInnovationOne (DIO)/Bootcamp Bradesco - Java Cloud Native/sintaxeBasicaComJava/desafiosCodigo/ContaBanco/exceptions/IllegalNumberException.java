package sintaxeBasicaComJava.desafiosCodigo.ContaBanco.exceptions;
public class IllegalNumberException extends Exception {
    public IllegalNumberException() {
        super("Erro! O numero da conta deve ser um valor positivo com 4 dígitos");
    }
}