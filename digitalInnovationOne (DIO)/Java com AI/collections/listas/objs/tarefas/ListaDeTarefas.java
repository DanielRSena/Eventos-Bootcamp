package objs.tarefas;

import java.util.List;
import java.util.ArrayList;

public class ListaDeTarefas {
    private List<Tarefa> tarefas;

    public ListaDeTarefas() {
        this.tarefas = new ArrayList<>();
    }

    public void adicionarTarefa(String tarefa) {
        tarefas.add(new Tarefa(tarefa));
    }

    public void removerTarefa(String tarefa) {
        //tarefas.removeIf(t -> t.getDescricao().equals(tarefa));

        List<Tarefa> tarefasExcluir = new ArrayList<>();

        for(Tarefa t : tarefas)
            if(t.getDescricao().equalsIgnoreCase(tarefa)) 
                tarefasExcluir.add(t);
        
        tarefas.removeAll(tarefasExcluir);
    }

    public int obterNumeroTarefas() {
        return tarefas.size();
    }

    public void obterTarefas() {
        for(Tarefa tarefa : tarefas)
            System.out.println(tarefa.getDescricao());
    }
}