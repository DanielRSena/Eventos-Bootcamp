import multifuncionais.Copiadora;
import multifuncionais.Digitalis;
import multifuncionais.Digitalizadora;
import multifuncionais.Epson;
import multifuncionais.Impressora;
import multifuncionais.Multifuncional;
import multifuncionais.Xerox;

public class Interface {
    public static void main(String[] args) {

        Epson epson = new Epson();
        Xerox xerox = new Xerox();
        Digitalis digitalis = new Digitalis();

        Multifuncional multifuncional = new Multifuncional();

        epson.imprimir();
        xerox.copiar();
        digitalis.digitalizar();

        System.out.println("\n");

        multifuncional.digitalizar();
        multifuncional.imprimir();
        multifuncional.copiar();

        System.out.println("\n");

        //Comparações

        //Como esses objetos já estão estanciados, não são a 'mesma coisa'. Faça o comentário abaixo para ver o restante.
        /*multifuncional = epson;
        multifuncional = xerox;
        multifuncional = digitalis;*/
        
        //Como o multifuncional implementa esses tres objetos, pode ser usado para compar os mesmos, tanto que suas funções sao iguais
        Impressora impressora = multifuncional;
        Digitalizadora digitalizadora = multifuncional;
        Copiadora copiadora = multifuncional;

        impressora.imprimir();
        digitalizadora.digitalizar();
        copiadora.copiar();

        System.out.println("\n");
    }
}