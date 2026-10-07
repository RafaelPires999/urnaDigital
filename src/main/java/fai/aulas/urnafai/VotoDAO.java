package fai.aulas.urnafai;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author rafa-pires
 */
public class VotoDAO {

    private final EleitorDAO eleitorDAO = new EleitorDAO();

    public void registrarVotacao(Voto voto, Eleitor eleitor) throws SQLException {
        String sql = "INSERT INTO voto (id_sessao, id_cargo, voto) "
                   + "SELECT ?, id_cargo, ? FROM cargo WHERE nome = ?";

        try (Connection con = Conexao.conectar()) {
            con.setAutoCommit(false);

            try {
                if (!eleitorDAO.marcarComoVotou(con, eleitor)) {
                    throw new SQLException("Eleitor já votou ou não está cadastrado");
                }

                try (PreparedStatement ps = con.prepareStatement(sql)) {
                    for (ItemVoto item : voto.getItens()) {
                        ps.setInt(1, voto.getIdSessao());
                        ps.setString(2, CifraCesar.cifrar(item.getValor()));
                        ps.setString(3, item.getCargo());

                        if (ps.executeUpdate() != 1) {
                            throw new SQLException("Cargo inválido: " + item.getCargo());
                        }
                    }
                }

                con.commit();
                eleitor.setJavotou(true);
            } catch (SQLException | RuntimeException e) {
                con.rollback();
                throw e;
            }
        }
    }

    public Map<String, Integer> apurar(String cargo) throws SQLException {
        String sql = "SELECT v.voto, COUNT(*) AS total "
                   + "FROM voto v "
                   + "JOIN cargo g ON g.id_cargo = v.id_cargo "
                   + "WHERE g.nome = ? "
                   + "GROUP BY v.voto "
                   + "ORDER BY total DESC";
        Map<String, Integer> resultado = new LinkedHashMap<>();

        try (Connection con = Conexao.conectar();
        PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, cargo);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    resultado.put(CifraCesar.decifrar(rs.getString("voto")),
                                  rs.getInt("total"));
                }
            }
        }
        return resultado;
    }

    public void limparVotos() throws SQLException {
        String sql = "DELETE FROM voto";

        try (Connection con = Conexao.conectar()) {
            con.setAutoCommit(false);

            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.executeUpdate();
                eleitorDAO.resetarVotos(con);
                con.commit();
            } catch (SQLException | RuntimeException e) {
                con.rollback();
                throw e;
            }
        }
    }
}
