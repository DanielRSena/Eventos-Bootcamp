package objs.contatos;

import java.util.Objects;

public class Contato {
    private String nome;
    private int numero;

    public Contato(String nome, int numero) {
        this.nome = nome;
        this.numero = numero;
    }

    public String getNome() {
        return nome;
    }

    public int getNumero() {
        return numero;
    }

    @Override
    public String toString() {
        return "nome: '" + nome + "', numero: '" + numero + "'";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Contato contato)) return false;
        return getNome() == contato.getNome();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNome());
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }
}