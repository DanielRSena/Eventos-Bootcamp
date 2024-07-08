package mensagens;

public class Facebook extends AppChat {


    @Override
    public void enviarMensagem(String mensagem) {
        System.out.println("\n- Enviar mensagem -\n");
        System.out.println("'" + mensagem + "' - Mensagem enviada pelo Facebook");
    }

    @Override
    public void receberMensagem() {
        System.out.println("\n- Receber mensagem -\n");
        System.out.println("Mensagem recebida pelo Facebook");
    }
}