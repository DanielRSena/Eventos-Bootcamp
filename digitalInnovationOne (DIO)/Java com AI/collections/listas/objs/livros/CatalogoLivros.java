package objs.livros;

import java.util.List;
import java.util.ArrayList;

public class CatalogoLivros {

    private List<Livro> livros;

    public CatalogoLivros() {
        this.livros = new ArrayList<>();
    }

    public void adicionarLivro(String titulo, String autor, int anoPublicacao) {
        livros.add(new Livro(titulo, autor, anoPublicacao));
    }

    public List<Livro> pesquisarPorAutor(String autorBusca) {

        List <Livro> livrosAutor = new ArrayList<>();
        
        if(!livros.isEmpty()) {
           for (Livro livro : livros)
               if (livro.getAutor().equalsIgnoreCase(autorBusca))
                   livrosAutor.add(livro);
        }
        if(livrosAutor.isEmpty()) System.out.println("Autor não encontrado");
        return livrosAutor;
    }

    public List<Livro> pesquisarPorIntervaloDePublicacao(int anoInicial, int anoFinal) {

        List <Livro> livrosIntervalo = new ArrayList<>();
        if(!livros.isEmpty()) {
            for (Livro livro : livros)
                if (livro.getAnoPublicacao() >= anoInicial && livro.getAnoPublicacao() <= anoFinal)
                    livrosIntervalo.add(livro);
        }
        return livrosIntervalo;
    }

    public Livro pesquisarPorTitulo(String titulo) {
        Livro livroEncontrado = null;
        if(!livros.isEmpty()) {
            for (Livro livro : livros)
                if (livro.getTitulo().equalsIgnoreCase(titulo)) livroEncontrado = livro;
        }
        if (livroEncontrado == null) System.out.println("livro não encontrado");
        return livroEncontrado;
    }

    public void getLivros() {
        for (Livro livro : livros)
            System.out.println(livro.toString(livro));
    }
}