/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fai.aulas.urnafai;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author rafa-pires
 */
public class SessaoDAO {
    public int iniciarSessao(String matriculaMesario) throws SQLException {
        String sql = "INSERT INTO sessao (id_mesario) "
                   + "SELECT id_mesario FROM mesarios WHERE matricula = ?";

        try (Connection con = Conexao.conectar();
        PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, matriculaMesario);

            if (ps.executeUpdate() == 0) {
                throw new SQLException("Mesário com matrícula " + matriculaMesario + " não encontrado");
            }

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }
        throw new SQLException("Não foi possível obter o id da sessão");
    }

    /**
     * Procura uma sessão que foi iniciada e ainda não foi encerrada
     * (fim IS NULL), por exemplo depois de uma queda de energia.
     * Se houver mais de uma, retorna a mais recente.
     *
     * @return a sessão aberta, ou null se não houver nenhuma
     */
    public Sessao buscarSessaoAberta() throws SQLException {
        String sql = "SELECT s.id_sessao, s.inicio, m.nome, m.matricula, m.usuario "
                   + "FROM sessao s "
                   + "JOIN mesarios m ON m.id_mesario = s.id_mesario "
                   + "WHERE s.fim IS NULL "
                   + "ORDER BY s.inicio DESC, s.id_sessao DESC "
                   + "LIMIT 1";

        try (Connection con = Conexao.conectar();
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                Mesario mesario = new Mesario();
                mesario.setNome(rs.getString("nome"));
                mesario.setMatricula(rs.getString("matricula"));
                mesario.setUsuario(rs.getString("usuario"));

                Sessao sessao = new Sessao();
                sessao.setIdSessao(rs.getInt("id_sessao"));
                sessao.setInicio(rs.getTimestamp("inicio").toLocalDateTime());
                sessao.setMesario(mesario);
                return sessao;
            }
        }
        return null;
    }

    public void encerrarSessao(int idSessao) throws SQLException {
        String sql = "UPDATE sessao SET fim = NOW() WHERE id_sessao = ?";

        try (Connection con = Conexao.conectar();
        PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idSessao);
            ps.executeUpdate();
        }
    }
}
