package objs.convidados;

public class Convidado {
    private String nome;
    private int codConvite;

    public Convidado(String nome, int codConvite) {
        this.nome = nome;
        this.codConvite = codConvite;
    }

    public String getNome() {
        return nome;
    }

    public int getCodConvite() {
        return codConvite;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Convidado convidado)) return false;
        return getCodConvite() == convidado.getCodConvite();
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(codConvite);
    }

    @Override
    public String toString() {
        return "nome: '" + nome + '\'' + ", codConvite: " + codConvite;
    }
}