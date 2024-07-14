import objs.agendaEventos.AgendaEventos;

import java.time.LocalDate;

public class MapsAgendaEventos {
    public static void main(String[] args) {

        AgendaEventos agenda = new AgendaEventos();

        System.out.println("\nAdicionando eventos: ");
        agenda.adicionarEvento(LocalDate.of(2025, 12, 10), "Cinema", "Meu Malfeitor predileto");
        agenda.adicionarEvento(LocalDate.of(2025, 12, 11), "Futebol", "Mis FC x Tura FC");
        agenda.adicionarEvento(LocalDate.of(2023, 12, 11), "Cinema", "Fifito, um cãozinho da pesada");
        agenda.adicionarEvento(LocalDate.of(2025, 12, 12), "Teatro", "Cinema de cachorro");
        agenda.adicionarEvento(LocalDate.of(2025, 07, 15), "Seminário", "Como eu programar");

        System.out.println("\nExibindo agenda: ");
        agenda.exibirAgenda();
        System.out.println();

        System.out.println("\nObtendo proximo evento: ");
        agenda.obterProximoEvento();

        System.out.println("\nRemovendo evento: ");
        agenda.removerEvento(LocalDate.of(2025, 12, 10));

        agenda.exibirAgenda();
    }
}