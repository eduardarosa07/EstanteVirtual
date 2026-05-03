package teste;

import java.util.List;
import persistencia.PLivro;
import model.Livro;

public class TesteLivro {

    public static void main(String[] args) {

        PLivro persistencia = new PLivro();

        Livro livro = new Livro();

        // Define os dados do livro
        livro.setCodigo(1); 
        livro.setTitulo("Jovens de Elite");
        livro.setGenero("Fantasia");
        livro.setAutor("Marie Lu");
        livro.setAnoPublicacao(2016);
        livro.setPaginas(339);
        livro.setEstrelas(5);

        try {
            //  ESCOLHA UMA OPERAÇÃO POR VEZ:

            // Inserir novo livro
            //persistencia.incluir(livro);

            // Alterar livro existente
            //persistencia.alterar(livro);

            // Excluir livro
            //persistencia.excluir(livro);

        } catch (Exception e) {
            e.printStackTrace();
        }

        //CONSULTAR UM LIVRO
//        Livro resultado = persistencia.consultar(1);
//        System.out.println("Titulo: " + resultado.getTitulo());

        // LISTAR TODOS OS LIVROS
//        List<Livro> lista = persistencia.listar();
//        for (Livro l : lista) {
//            System.out.println("Código: " + l.getCodigo());
//            System.out.println("Título: " + l.getTitulo());
//            System.out.println("Autor: " + l.getAutor());
//            System.out.println("\n");
//        }
    }
}