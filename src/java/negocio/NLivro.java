package negocio;

import java.sql.SQLException;
import java.util.List;
import model.Livro;
import persistencia.PLivro;

public class NLivro {

    PLivro persistencia;

    public NLivro() {
        persistencia = new PLivro();
    }

    // SALVAR (INSERIR OU ALTERAR)
    public void salvar(Livro livro) throws SQLException, Exception {

        validar(livro);

        if (livro.getCodigo() == 0) {
            persistencia.incluir(livro);
        } else {
            persistencia.alterar(livro);
        }
    }

    // EXCLUIR
    public void excluir(Livro livro) throws SQLException {
        persistencia.excluir(livro);
    }

    // CONSULTAR
    public Livro consultar(int codigo) {
        return persistencia.consultar(codigo);
    }

    // LISTAR
    public List<Livro> listar() {
        return persistencia.listar();
    }

    // VALIDAÇÕES
    private void validar(Livro livro) throws Exception {

        if (livro.getTitulo() == null || livro.getTitulo().isEmpty()) {
            throw new Exception("Informe o título do livro.");
        }

        if (livro.getAutor() == null || livro.getAutor().isEmpty()) {
            throw new Exception("Informe o autor.");
        }

        if (livro.getGenero() == null || livro.getGenero().isEmpty()) {
            throw new Exception("Informe o gênero.");
        }

        if (livro.getAnoPublicacao() <= 0) {
            throw new Exception("Ano de publicação inválido.");
        }

        if (livro.getPaginas() <= 0) {
            throw new Exception("Número de páginas inválido.");
        }

        if (livro.getEstrelas() < 1 || livro.getEstrelas() > 5) {
            throw new Exception("A avaliação deve ser de 1 a 5 estrelas.");
        }
    }
}