package sintaxeBasicaComJava.desafiosCodigo.ContaBanco;

import java.security.InvalidKeyException;
import java.util.Scanner;

import sintaxeBasicaComJava.desafiosCodigo.ContaBanco.exceptions.IllegalNumberException;

public class ContaTerminal {

    public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in);
        
        int numero = 0;
        String nomeCliente, agencia;
        double saldo;

        while (true) {
            try {
                System.out.print("Digite o numero da conta: ");
                numero = entrada.nextInt();
                if(numero <= 1000 || numero >= 9999) throw new IllegalNumberException();
                break;                   
            } catch (IllegalNumberException e) { System.out.println(e.getMessage()); 
            } catch (Exception e) { System.out.println("Erro! Apenas números inteiros são aceitos!"); entrada.next(); }
        }

        System.out.print("Digite o nome do cliente: ");
        nomeCliente = entrada.next();

        while (true) {
            System.out.print("Digite o nome da agência: ");
            agencia = entrada.next();
            if(verificarAgencia(agencia)) break;
        }

        while (true) {
            try {
                System.out.print("Digite o saldo inicial: ");
                saldo = entrada.nextDouble();
                if(saldo < 0) throw new Exception();
                break;
            } catch (Exception e) { System.out.println("Erro! Apenas números reais são aceitos!"); entrada.next(); }
        }

        System.out.println("Olá " + nomeCliente + ", obrigado por criar uma conta em nosso banco, sua agência é " + agencia + ", conta " + numero + " e seu saldo " + saldo + " já está disponível para saque.");

        entrada.close();
    }

    public static boolean verificarAgencia(String numero) {

        boolean result = true;

        try {
            if(numero.length() != 5) {
                result = false;
                throw new InvalidKeyException();
            }
            for (int i = 0; i < 4; i++) {
                if(i == 3) if(numero.charAt(i) != '-') result = false;
                else if(numero.charAt(i) - '0' == 0) result = false;
            }
            if(!result) System.out.println("Erro! Verifique o número da agência e tente novamente!");
        } catch (InvalidKeyException e) { System.out.println("Erro! A agência deve conter 5 dígitos!"); } 
        return result;
    }
}