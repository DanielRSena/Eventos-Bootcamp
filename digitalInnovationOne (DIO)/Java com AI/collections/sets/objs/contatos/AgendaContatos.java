package objs.contatos;

import java.util.HashSet;
import java.util.Set;

public class AgendaContatos {
    private Set<Contato> contatos;   

    public AgendaContatos() {
        this.contatos = new HashSet<>();
    }

    public void adicionarContato(String nome, int numero) {
        this.contatos.add(new Contato(nome, numero));
    }

    public void exibirContatos() {
        this.contatos.forEach(contato -> System.out.println(contato.toString()));
    }

    public Set<Contato> procurarPorNome(String nome) {
        Set <Contato> contatosEncontrados = new HashSet<>();
        this.contatos.forEach(contato -> {
            if (contato.getNome().startsWith(nome))
                contatosEncontrados.add(contato);
        });
        return contatosEncontrados;
    }

    public Contato alterarContato(String nome, int novoNumero) {
        Contato contAtualizado = new Contato(nome, novoNumero);
        for(Contato cont : this.contatos)
            if(cont.getNome().equalsIgnoreCase(contAtualizado.getNome())) {
                cont.setNumero(contAtualizado.getNumero());
                contAtualizado = cont;
                break;
            }
        return contAtualizado;
    }
}