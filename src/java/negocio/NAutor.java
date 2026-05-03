package negocio;

import java.sql.SQLException;
import java.util.List;
import model.Autor;
import persistencia.PAutor;

public class NAutor {

    PAutor persistencia;

    public NAutor() {
        persistencia = new PAutor();
    }

    // SALVAR )
    public void salvar(Autor autor) throws SQLException, Exception {

        // Valida antes de salvar
        validar(autor);

        // Se não tem código → novo
        if (autor.getCodigo() == 0) {
            persistencia.incluir(autor);
        } 
        // Se já tem → atualizar
        else {
            persistencia.alterar(autor);
        }
    }

    // EXCLUIR autor
    public void excluir(Autor autor) throws SQLException {
        persistencia.excluir(autor);
    }

    // CONSULTAR autor
    public Autor consultar(int codigo) {
        return persistencia.consultar(codigo);
    }

    // LISTAR autores
    public List<Autor> listar() {
        return persistencia.listar();
    }

    // VALIDAÇÃO
    private void validar(Autor autor) throws Exception {

        // Nome obrigatório
        if (autor.getNome() == null || autor.getNome().isEmpty()) {
            throw new Exception("Informe o nome do autor.");
        }

        // Quantidade não pode ser negativa
        if (autor.getQuantidadeLivros() < 0) {
            throw new Exception("Quantidade de livros inválida.");
        }
    }
}