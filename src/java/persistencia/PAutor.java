package persistencia;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Autor;
import util.Conexao;

public class PAutor {

    // Conexão com o banco
    private Connection cnn;

    // Construtor
    public PAutor() {
        cnn = Conexao.getConnection();
    }

    // CONSULTAR AUTOR POR CÓDIGO
    public Autor consultar(int codigo) {

        Autor autor = new Autor();

        try {
            String sql = "SELECT * FROM autor WHERE codigo = ?";
            PreparedStatement prd = cnn.prepareStatement(sql);
            prd.setInt(1, codigo);

            ResultSet rst = prd.executeQuery();

            if (rst.next()) {
                autor.setCodigo(rst.getInt("codigo"));
                autor.setNome(rst.getString("nome"));
                autor.setQuantidadeLivros(rst.getInt("quantidadeLivros"));
            }

            rst.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return autor;
    }

    //  LISTAR TODOS OS AUTORES
    public List<Autor> listar() {

        List<Autor> lista = new ArrayList<>();

        try {
            String sql = "SELECT * FROM autor";
            Statement stm = cnn.createStatement();

            ResultSet rst = stm.executeQuery(sql);

            while (rst.next()) {
                Autor autor = new Autor();

                autor.setCodigo(rst.getInt("codigo"));
                autor.setNome(rst.getString("nome"));
                autor.setQuantidadeLivros(rst.getInt("quantidadeLivros"));

                lista.add(autor);
            }

            rst.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    // INSERIR AUTOR
    public void incluir(Autor autor) throws SQLException {

        try {
            cnn.setAutoCommit(false);

            String sql = "INSERT INTO autor (nome, quantidadeLivros) VALUES (?, ?)";
            PreparedStatement prd = cnn.prepareStatement(sql);

            prd.setString(1, autor.getNome());
            prd.setInt(2, autor.getQuantidadeLivros());

            prd.executeUpdate();

            // Recupera o código gerado
            String sqlCodigo = "SELECT currval('autor_codigo_seq') as codigo";
            Statement stm = cnn.createStatement();
            ResultSet rst = stm.executeQuery(sqlCodigo);

            if (rst.next()) {
                autor.setCodigo(rst.getInt("codigo"));
            }

            rst.close();
            cnn.commit();

        } catch (Exception e) {
            cnn.rollback();
        } finally {
            cnn.setAutoCommit(true);
        }
    }

    //  ALTERAR AUTOR
    public void alterar(Autor autor) throws SQLException {

        try {
            String sql = "UPDATE autor SET nome=?, quantidadeLivros=? WHERE codigo=?";
            PreparedStatement prd = cnn.prepareStatement(sql);

            prd.setString(1, autor.getNome());
            prd.setInt(2, autor.getQuantidadeLivros());
            prd.setInt(3, autor.getCodigo());

            prd.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //  EXCLUIR AUTOR
    public void excluir(Autor autor) throws SQLException {

        try {
            String sql = "DELETE FROM autor WHERE codigo=?";
            PreparedStatement prd = cnn.prepareStatement(sql);

            prd.setInt(1, autor.getCodigo());

            prd.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}//Fim da classe