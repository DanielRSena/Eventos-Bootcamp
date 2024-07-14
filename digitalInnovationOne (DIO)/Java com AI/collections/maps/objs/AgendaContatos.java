package objs;

import java.util.HashMap;
import java.util.Map;

public class AgendaContatos {

    private Map<String, Integer> agendaContatosMap;

    public AgendaContatos() {
        this.agendaContatosMap = new HashMap<>(); //hashmap é o mais comum com map
    }

    public void adicionarContato(String nome, int numero) {
        this.agendaContatosMap.put(nome, numero);
        //detalhe que o put serve para adicionar ou substituir valores.
    }

    public void removerContato(String nome) {
        if(!agendaContatosMap.isEmpty()) {
            if(agendaContatosMap.containsKey(nome)) 
                this.agendaContatosMap.remove(nome);
            else 
                System.out.println("Contato na agenda inexistente!");

        } 
        else System.out.println("Agenda vazia!");
    }    

    public void exibirContatos() {
        if(!agendaContatosMap.isEmpty()) {
            this.agendaContatosMap.forEach((nome, numero) -> System.out.println("Nome: " + nome + " | Telefone: " + numero));
        }
        else System.out.println("Agenda vazia!");
    }

    public Integer procurarPorNome(String nome) {
        Integer numeroPorNome = null;
        if(!agendaContatosMap.isEmpty()) {
            if(agendaContatosMap.containsKey(nome))  return this.agendaContatosMap.get(nome);
            else System.out.println("Contato na agenda inexistente!");

        } 
        else 
            System.out.println("Agenda vazia!");
        return numeroPorNome;
    }
}