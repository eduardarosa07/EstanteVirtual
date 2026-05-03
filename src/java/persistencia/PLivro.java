package persistencia;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Livro;
import util.Conexao;

public class PLivro {

    // Conexão com o banco
    private Connection cnn;

    // Construtor pega a conexão
    public PLivro() {
        cnn = Conexao.getConnection();
    }

    // CONSULTAR UM LIVRO PELO CÓDIGO
    public Livro consultar(int codigo) {

        Livro livro = new Livro();

        try {
            String sql = "SELECT * FROM livro WHERE codigo = ?";
            PreparedStatement prd = cnn.prepareStatement(sql);
            prd.setInt(1, codigo);

            ResultSet rst = prd.executeQuery();

            if (rst.next()) {
                // Preenche o objeto com os dados do banco
                livro.setCodigo(rst.getInt("codigo"));
                livro.setTitulo(rst.getString("titulo"));
                livro.setGenero(rst.getString("genero"));
                livro.setAutor(rst.getString("autor"));
                livro.setAnoPublicacao(rst.getInt("anoPublicacao"));
                livro.setPaginas(rst.getInt("paginas"));
                livro.setEstrelas(rst.getInt("estrelas"));
            }

            rst.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return livro;
    }

    //  LISTAR TODOS OS LIVROS
    public List<Livro> listar() {

        List<Livro> lista = new ArrayList<>();

        try {
            String sql = "SELECT * FROM livro";
            Statement stm = cnn.createStatement();

            ResultSet rst = stm.executeQuery(sql);

            while (rst.next()) {
                Livro livro = new Livro();

                livro.setCodigo(rst.getInt("codigo"));
                livro.setTitulo(rst.getString("titulo"));
                livro.setGenero(rst.getString("genero"));
                livro.setAutor(rst.getString("autor"));
                livro.setAnoPublicacao(rst.getInt("anoPublicacao"));
                livro.setPaginas(rst.getInt("paginas"));
                livro.setEstrelas(rst.getInt("estrelas"));

                lista.add(livro);
            }

            rst.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    //  INSERIR LIVRO
    public void incluir(Livro livro) throws SQLException {

        try {
            cnn.setAutoCommit(false);

            String sql = "INSERT INTO livro (titulo, genero, autor, anoPublicacao, paginas, estrelas) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement prd = cnn.prepareStatement(sql);

            prd.setString(1, livro.getTitulo());
            prd.setString(2, livro.getGenero());
            prd.setString(3, livro.getAutor());
            prd.setInt(4, livro.getAnoPublicacao());
            prd.setInt(5, livro.getPaginas());
            prd.setInt(6, livro.getEstrelas());

            prd.executeUpdate();

            // Pega o código gerado automaticamente
            String sqlCodigo = "SELECT currval('livro_codigo_seq') as codigo";
            Statement stm = cnn.createStatement();
            ResultSet rst = stm.executeQuery(sqlCodigo);

            if (rst.next()) {
                livro.setCodigo(rst.getInt("codigo"));
            }

            rst.close();
            cnn.commit();

        } catch (Exception e) {
            cnn.rollback();
        } finally {
            cnn.setAutoCommit(true);
        }
    }//Fim do método

    //  ALTERAR LIVRO
    public void alterar(Livro livro) throws SQLException {

        try {
            String sql = "UPDATE livro SET titulo=?, genero=?, autor=?, anoPublicacao=?, paginas=?, estrelas=? WHERE codigo=?";
            PreparedStatement prd = cnn.prepareStatement(sql);

            prd.setString(1, livro.getTitulo());
            prd.setString(2, livro.getGenero());
            prd.setString(3, livro.getAutor());
            prd.setInt(4, livro.getAnoPublicacao());
            prd.setInt(5, livro.getPaginas());
            prd.setInt(6, livro.getEstrelas());
            prd.setInt(7, livro.getCodigo());

            prd.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //  EXCLUIR LIVRO
    public void excluir(Livro livro) throws SQLException {

        try {
            String sql = "DELETE FROM livro WHERE codigo=?";
            PreparedStatement prd = cnn.prepareStatement(sql);

            prd.setInt(1, livro.getCodigo());

            prd.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
} //Fim da classe