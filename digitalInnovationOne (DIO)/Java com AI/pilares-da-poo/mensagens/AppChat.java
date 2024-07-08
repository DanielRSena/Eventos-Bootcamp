package mensagens;

public abstract class AppChat {
    public abstract void enviarMensagem(String mensagem);
    public abstract void receberMensagem();

    protected void validarConexaoAInternet() {
        System.out.println("Verificando conexão ao Internet...");
    }
}