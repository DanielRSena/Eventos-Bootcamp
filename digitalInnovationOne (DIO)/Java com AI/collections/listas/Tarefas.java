import objs.tarefas.ListaDeTarefas;

public class Tarefas {
    public static void main(String[] args) {

        System.out.println("\nTarefas \n");

        ListaDeTarefas tarefas = new ListaDeTarefas();
        
        tarefas.adicionarTarefa("Tarefa 1");
        tarefas.adicionarTarefa("Tarefa 2");
        tarefas.adicionarTarefa("Tarefa 3");
        tarefas.adicionarTarefa("Tarefa 4");
        tarefas.adicionarTarefa("Tarefa 3");

        System.out.println("\nTarefas Normais: " + tarefas.obterNumeroTarefas());
        tarefas.obterTarefas();

        tarefas.removerTarefa("Tarefa 3");

        System.out.println("\nTarefas com a remoção: " + tarefas.obterNumeroTarefas());
        tarefas.obterTarefas();
        tarefas.obterNumeroTarefas();

        System.out.println();
    }
}