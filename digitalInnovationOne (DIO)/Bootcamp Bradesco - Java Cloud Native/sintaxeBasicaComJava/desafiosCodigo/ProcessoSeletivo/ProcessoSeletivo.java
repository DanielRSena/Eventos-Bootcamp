package sintaxeBasicaComJava.desafiosCodigo.ProcessoSeletivo;

public class ProcessoSeletivo {
    public static void main(String[] args) {

        System.out.println("\t -Processo Seletivo - \n");
        analisarCandidato(2000.0);
        analisarCandidato(1000.0);
        analisarCandidato(3000.0);
    }

    static void selecaoCandidatos () {
        
        String [] candidatos = {"Felipe", "Gabriel", "Guilherme", "Cinthia", "Paulo", "Joaquim", "Marcos", "Lucas", "Rafael", "Pedro"};
        int candidatosSelecionados = 0, candidatosAtuais = 0;
        double salarioBase = 2000.0, salarioPretendido = 0.0;

        while (candidatosSelecionados < 5) {
            String candidato = candidatos[candidatosAtuais];
            System.out.println("Candidato selecionado: " + candidato + ", salario pretendido: " + salarioPretendido);
            salarioPretendido = Math.random() * 3000.0 + 1000.0;
            if(salarioPretendido > salarioBase) {
                System.out.println("Candidato" + candidato + " aceito: " + salarioPretendido);
                candidatosSelecionados++;
            } else {
                System.out.println("Salario rejeitado: " + salarioPretendido);
            }
            candidatosAtuais++;
        }
    }

    static void analisarCandidato (double salarioPretendido) {

        double salarioBase = 2000.0;
        String resultado = "";

        if(salarioBase > salarioPretendido) resultado = "LIGAR PARA O CANDIDATO";
        else if(salarioPretendido == salarioBase) resultado = "LIGAR PARA O CANDIDATO COM CONTRA PROPOSTA";
        else resultado = "AGUARDANDO O RESULTADO DOS DEMAIS CANDIDATOS";

        System.out.println(resultado);
    }
}
