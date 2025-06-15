package sintaxeBasicaComJava.desafiosCodigo.explorandoSintaxeJava;

import java.util.Scanner;

public class ClassificacoesClientesSaldo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String resultado = "";
        double saldo = scanner.nextDouble();
    
        if(saldo < 0) resultado= "Negativado";
        else resultado = (saldo <= 500)? "Baixo" : "Confortavel";

        System.out.println(resultado);

        scanner.close();
    }
}