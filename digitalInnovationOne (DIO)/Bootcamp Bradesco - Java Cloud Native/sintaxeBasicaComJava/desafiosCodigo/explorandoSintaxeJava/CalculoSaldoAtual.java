package sintaxeBasicaComJava.desafiosCodigo.explorandoSintaxeJava;

import java.util.Scanner;
public class CalculoSaldoAtual {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        double saldoInicial = scanner.nextDouble();
        double saldoFinal = saldoInicial;

        for(int i = 0; i <= 2; i++) {
            double valor = scanner.nextDouble();
            saldoFinal = saldoInicial + valor;
            saldoInicial = saldoFinal;
        }
        
        // Saldo final
        System.out.printf("%.2f\n", saldoFinal);

        scanner.close();
    }
}