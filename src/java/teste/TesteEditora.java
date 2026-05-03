
package teste;

import java.util.List;
import persistencia.PEditora;
import model.Editora;

public class TesteEditora {

    public static void main(String[] args) {

        PEditora persistencia = new PEditora();

        // Cria editora
        Editora editora = new Editora();

        // Define dados
        editora.setCodigo(1); // usado para alterar/excluir
        editora.setNome("Editora Teste");
        editora.setQuantidadeLivros(50);
        editora.setSite("www.editora.com");
        editora.setAtiva(true);

        try {

            // Inserir
            //persistencia.incluir(editora);

            // Alterar
            //persistencia.alterar(editora);

            // Excluir
            //persistencia.excluir(editora);

        } catch (Exception e) {
            e.printStackTrace();
        }

        // CONSULTAR
//        Editora resultado = persistencia.consultar(1);
//        System.out.println("Nome: " + resultado.getNome());

        // LISTAR
//        List<Editora> lista = persistencia.listar();
//        for (Editora e : lista) {
//            System.out.println("Código: " + e.getCodigo());
//            System.out.println("Nome: " + e.getNome());
//            System.out.println("\n");
//        }
    }
}