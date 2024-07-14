package objs.convidados;
import java.util.HashSet;
import java.util.Set;

public class ConjuntoConvidados {
    private Set<Convidado> convidados;

    public ConjuntoConvidados() {
        this.convidados = new HashSet<>();
    }

    public void adicionarConvidado(String nome, int codConvite) {

        if(convidados.contains(new Convidado(null, codConvite))) {
            System.out.println("Código de convite já existente");
            return;
        }
        this.convidados.add(new Convidado(nome, codConvite));
        //no List o meio é o 'add' e poderia ser sobrecarregado com o index que desejamos colocar, porém no Set, o 'add' não aceita o index.
    }

    public void removerConvidado(int codConvite) {
        Convidado convidadoParaRemover = null;
        for(Convidado convidado : convidados) {
            if(convidado.getCodConvite() == codConvite) {
                convidadoParaRemover = convidado;
                break;
            }
        }
        convidados.remove(convidadoParaRemover);
    }

    public int contarConvidados() {
        return this.convidados.size();
    }

    public void mostrarConvidados() {
        for(Convidado convidado : convidados) {
            System.out.println(convidado.toString());
        }
    }
}