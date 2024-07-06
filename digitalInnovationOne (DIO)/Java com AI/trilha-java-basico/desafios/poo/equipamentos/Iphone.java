public class Iphone implements ReprodutorMusical, AparelhoTelefonico, NavegadorInternet {

    @Override
    public void tocar() {
        System.out.println("\nTocando música...\n");
    }

    @Override
    public void pausar() {
        System.out.println("\nPausando música...\n");
    }

    @Override
    public void selecionarMusica(String musica) {
        System.out.println("\nSelecionando música: " + musica + "...\n");
    }

    @Override
    public void ligar(String numero) {
        System.out.println("\nLigando para o número: " + numero + "...\n");
    }

    @Override
    public void atender() {
        System.out.println("\nAtendendo...\n");
    }

    @Override
    public void iniciarCorreioVoz() {
        System.out.println("\nIniciando correio de voz...\n");
    }

    @Override
    public void exibirPagina(String url) {
        System.out.println("\nExibindo página: " + url + "...\n");
    }

    @Override
    public void adicionarNovaAba() {
        System.out.println("\nAdicionando nova aba...\n");
    }

    @Override
    public void atualizarPagina() {
        System.out.println("\nAtualizando página...\n");
    }
}