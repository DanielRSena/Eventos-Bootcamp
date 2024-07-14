import java.util.List;

import objs.livros.CatalogoLivros;
import objs.livros.Livro;

public class Livros {
    public static void main(String[] args) {
        CatalogoLivros catalogo = new CatalogoLivros();

        catalogo.adicionarLivro("1984", "George Orwell", 1949);
        catalogo.adicionarLivro("A revolução dos bichos", "George Orwell", 1945);
        catalogo.adicionarLivro("O poderoso chefão", "Mario Puzo", 1950);
        catalogo.adicionarLivro("Dom Casmurro", "Machado de Assis", 1899);
        catalogo.adicionarLivro("A menina que roubava livros", "Stephanie Meyer", 2000);
        catalogo.adicionarLivro("O senhor dos anéis", "J. R. R. Tolkien", 1954);

        System.out.println("\n\nLivros \n");
        catalogo.getLivros();

        List<Livro> livrosAutor = catalogo.pesquisarPorAutor("George Orwell");
        System.out.println("\n\nLivros do autor 'George Orwell' \n");
        for (Livro livro : livrosAutor)
            System.out.println(livro.toString(livro));

        List<Livro> livrosIntervalo = catalogo.pesquisarPorIntervaloDePublicacao(1900, 2000);
        System.out.println("\n\nLivros publicados entre 1900 e 2000 \n");
        for (Livro livro : livrosIntervalo)
            System.out.println(livro.toString(livro));

        Livro casmurro = catalogo.pesquisarPorTitulo("Dom Casmurro");
        System.out.println("\n\nLivro 'Dom Casmurro' \n" + casmurro.toString(casmurro) + "\n");
    }
}