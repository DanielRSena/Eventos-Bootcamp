package objs.agendaEventos;

import java.time.LocalDate;
import java.util.TreeMap;
import java.util.HashMap;
import java.util.Map;

public class AgendaEventos {
    private Map<LocalDate, Evento> agendaEventosMap;

    public AgendaEventos() {
        this.agendaEventosMap = new HashMap<>();
    }

    public void adicionarEvento(LocalDate data, String nome, String atracao) {
        this.agendaEventosMap.put(data, new Evento(nome, atracao));
    }

    public void exibirAgenda() {
        if(this.agendaEventosMap.isEmpty()) {
            System.out.println("Agenda vazia");
        } else {
           Map<LocalDate, Evento> agendaOrdenada = new TreeMap<>(this.agendaEventosMap);
           System.out.println(agendaOrdenada);
        }
    }

    public void obterProximoEvento() {
        if(this.agendaEventosMap.isEmpty()) {
            System.out.println("Agenda vazia");
        } else {

            LocalDate dataAtual = LocalDate.now();
            LocalDate proximoData = null;
            Evento proximoEvento = null;

            Map<LocalDate, Evento> agendaOrdenada = new TreeMap<>(this.agendaEventosMap);

            for(Map.Entry<LocalDate, Evento> evento : agendaOrdenada.entrySet()) {
                proximoData = evento.getKey();
                proximoEvento = evento.getValue();
                if(evento.getKey().isEqual(dataAtual) || evento.getKey().isAfter(dataAtual)) {
                    System.out.println("O proximo evento é: " + proximoEvento + " na data: " + proximoData);
                    break;
                }
            }
        }
    }

    public void removerEvento(LocalDate of) {
        this.agendaEventosMap.remove(of);
    }
}