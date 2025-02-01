package produtividadeComStreamAI.functionalInterface;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class ConsumerExample {
    public static void main(String[] args) {

        
        List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        //funcional interface Consumer
        Consumer<Integer> pares = numero -> {
            if (numero % 2 == 0) System.out.println(numero);
        };

        numeros.stream().forEach(pares);


        //OUOUOUOUOU


        numeros.stream().forEach(new Consumer<Integer>() {
           @Override
           public void accept(Integer integer) {
               if (integer % 2 == 0) System.out.println(integer);
           } 
        });


        //OUOUOUOUOU


        numeros.forEach(n -> {
            if (n % 2 == 0) System.out.println(n);
        });
    }
}