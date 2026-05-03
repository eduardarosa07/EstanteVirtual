package teste;

import java.util.List;
import persistencia.PAutor;
import model.Autor;

public class TesteAutor {

    public static void main(String[] args) {

        PAutor persistencia = new PAutor();

        // Cria autor
        Autor autor = new Autor();

        // Define dados
        autor.setCodigo(1); 
        autor.setNome("Autor Teste");
        autor.setQuantidadeLivros(10);

        try {
            // Inserir
            //persistencia.incluir(autor);

            // Alterar
            //persistencia.alterar(autor);

            // Excluir
            //persistencia.excluir(autor);

        } catch (Exception e) {
            e.printStackTrace();
        }

        // CONSULTAR
//        Autor resultado = persistencia.consultar(1);
//        System.out.println("Nome: " + resultado.getNome());

        // LISTAR
//        List<Autor> lista = persistencia.listar();
//        for (Autor a : lista) {
//            System.out.println("Código: " + a.getCodigo());
//            System.out.println("Nome: " + a.getNome());
//           System.out.println("\n");
//        }
    }
}