import mensagens.*;
public class Messenger {
    public static void main(String[] args) {

        MSNMessenger messenger = new MSNMessenger();
        messenger.receberMensagem();
        messenger.enviarMensagem("Hello!");

        Facebook face = new Facebook();
        face.receberMensagem();
        face.enviarMensagem("Hello!");

        Telegram telegram = new Telegram();
        telegram.receberMensagem();
        telegram.enviarMensagem("Hello!");
    }
}