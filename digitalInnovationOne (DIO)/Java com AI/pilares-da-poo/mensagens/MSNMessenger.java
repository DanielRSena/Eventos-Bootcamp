package mensagens;

public class MSNMessenger extends AppChat {

    @Override
    public void enviarMensagem(String mensagem) {
        System.out.println("\n- Enviar mensagem -\n");
        validarConexaoAInternet();
        System.out.println(mensagem + " - Enviado por MSNMessenger");
        salvarHistorico();
    }

    @Override
    public void receberMensagem() {
        System.out.println("\n- Receber mensagem -\n");
        validarConexaoAInternet();
        System.out.println("Procurando novas mensagens...");
        System.out.println("Baixando mensagens...");
        salvarHistorico();
    }

    private void salvarHistorico() {
        System.out.println("Salvando historico...");
    }
}