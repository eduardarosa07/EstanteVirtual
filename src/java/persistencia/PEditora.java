package persistencia;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Editora;
import util.Conexao;

public class PEditora {

    // Conexão com o banco
    private Connection cnn;

    // Construtor
    public PEditora() {
        cnn = Conexao.getConnection();
    }

    // CONSULTAR EDITORA POR CÓDIGO
    public Editora consultar(int codigo) {

        Editora editora = new Editora();

        try {
            String sql = "SELECT * FROM editora WHERE codigo = ?";
            PreparedStatement prd = cnn.prepareStatement(sql);
            prd.setInt(1, codigo);

            ResultSet rst = prd.executeQuery();

            if (rst.next()) {
                editora.setCodigo(rst.getInt("codigo"));
                editora.setNome(rst.getString("nome"));
                editora.setQuantidadeLivros(rst.getInt("quantidadeLivros"));
                editora.setSite(rst.getString("site"));
                editora.setAtiva(rst.getBoolean("ativa"));
            }

            rst.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return editora;
    }

    // LISTAR TODAS AS EDITORAS
    public List<Editora> listar() {

        List<Editora> lista = new ArrayList<>();

        try {
            String sql = "SELECT * FROM editora";
            Statement stm = cnn.createStatement();

            ResultSet rst = stm.executeQuery(sql);

            while (rst.next()) {
                Editora editora = new Editora();

                editora.setCodigo(rst.getInt("codigo"));
                editora.setNome(rst.getString("nome"));
                editora.setQuantidadeLivros(rst.getInt("quantidadeLivros"));
                editora.setSite(rst.getString("site"));
                editora.setAtiva(rst.getBoolean("ativa"));

                lista.add(editora);
            }

            rst.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    // INSERIR EDITORA
    public void incluir(Editora editora) throws SQLException {

        try {
            cnn.setAutoCommit(false);

            String sql = "INSERT INTO editora (nome, quantidadeLivros, site, ativa) VALUES (?, ?, ?, ?)";
            PreparedStatement prd = cnn.prepareStatement(sql);

            prd.setString(1, editora.getNome());
            prd.setInt(2, editora.getQuantidadeLivros());
            prd.setString(3, editora.getSite());
            prd.setBoolean(4, editora.isAtiva());

            prd.executeUpdate();

            // Recupera o código gerado
            String sqlCodigo = "SELECT currval('editora_codigo_seq') as codigo";
            Statement stm = cnn.createStatement();
            ResultSet rst = stm.executeQuery(sqlCodigo);

            if (rst.next()) {
                editora.setCodigo(rst.getInt("codigo"));
            }

            rst.close();
            cnn.commit();

        } catch (Exception e) {
            cnn.rollback();
        } finally {
            cnn.setAutoCommit(true);
        }
    }

    //  ALTERAR EDITORA
    public void alterar(Editora editora) throws SQLException {

        try {
            String sql = "UPDATE editora SET nome=?, quantidadeLivros=?, site=?, ativa=? WHERE codigo=?";
            PreparedStatement prd = cnn.prepareStatement(sql);

            prd.setString(1, editora.getNome());
            prd.setInt(2, editora.getQuantidadeLivros());
            prd.setString(3, editora.getSite());
            prd.setBoolean(4, editora.isAtiva());
            prd.setInt(5, editora.getCodigo());

            prd.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // EXCLUIR EDITORA
    public void excluir(Editora editora) throws SQLException {

        try {
            String sql = "DELETE FROM editora WHERE codigo=?";
            PreparedStatement prd = cnn.prepareStatement(sql);

            prd.setInt(1, editora.getCodigo());

            prd.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}//Fim da classe