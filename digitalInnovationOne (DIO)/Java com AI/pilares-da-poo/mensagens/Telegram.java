package mensagens;

public class Telegram extends AppChat {
    
    @Override
    public void enviarMensagem(String mensagem) {
        System.out.println("\n- Enviar mensagem -\n");
        validarConexaoAInternet();
        System.out.println("'" + mensagem + "' - Mensagem enviada pelo Telegram");
    }

    @Override
    public void receberMensagem() {
        System.out.println("\n- Receber mensagem -\n");
        System.out.println("Mensagem recebida pelo Telegram");
        validarConexaoAInternet();
    }
}
