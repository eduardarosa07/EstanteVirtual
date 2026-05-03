package negocio;

import java.sql.SQLException;
import java.util.List;
import model.Editora;
import persistencia.PEditora;

public class NEditora {
    
    PEditora persistencia;

    public NEditora() {
        persistencia = new PEditora();
    }

    // SALVAR 
    public void salvar(Editora editora) throws SQLException, Exception {

        // Valida antes de salvar
        validar(editora);

        // Novo registro
        if (editora.getCodigo() == 0) {
            persistencia.incluir(editora);
        } 
        // Atualização
        else {
            persistencia.alterar(editora);
        }
    }

    // EXCLUIR editora
    public void excluir(Editora editora) throws SQLException {
        persistencia.excluir(editora);
    }

    // CONSULTAR editora
    public Editora consultar(int codigo) {
        return persistencia.consultar(codigo);
    }

    // LISTAR editoras
    public List<Editora> listar() {
        return persistencia.listar();
    }

    // VALIDAÇÃO
    private void validar(Editora editora) throws Exception {

        // Nome obrigatório
        if (editora.getNome() == null || editora.getNome().isEmpty()) {
            throw new Exception("Informe o nome da editora.");
        }

        // Quantidade não pode ser negativa
        if (editora.getQuantidadeLivros() < 0) {
            throw new Exception("Quantidade de livros inválida.");
        }

        // Site obrigatório
        if (editora.getSite() == null || editora.getSite().isEmpty()) {
            throw new Exception("Informe o site da editora.");
        }
    }
}