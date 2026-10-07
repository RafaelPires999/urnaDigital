/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fai.aulas.urnafai;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author rafa-pires
 */
public class EleitorDAO {

    private static String limparNumeros(String texto){
        return texto == null ? "" : texto.replaceAll("[^0-9]", "");
    }

    /** Grava o eleitor; o CPF vai para o banco em hash MD5. */
    public void inserir(Eleitor eleitor) throws SQLException{
        String sql = "INSERT INTO eleitores (cpf, titulo, nome, ja_votou) VALUES (?, ?, ?, ?)";

        try(Connection con = Conexao.conectar();
        PreparedStatement ps = con.prepareStatement(sql)){
            ps.setString(1, md5(limparNumeros(eleitor.getCpf())));
            ps.setString(2, limparNumeros(eleitor.getTitulo()));
            ps.setString(3, eleitor.getNome());
            ps.setBoolean(4, false);
            ps.executeUpdate();
        }
    }

    public Eleitor buscarPorCPF(String cpf) throws SQLException {
        String sql = "SELECT cpf, titulo, nome, ja_votou FROM eleitores WHERE cpf = ?";

        try (Connection con = Conexao.conectar();
        PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, md5(limparNumeros(cpf)));

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Eleitor eleitor = new Eleitor();

                    eleitor.setCpf(rs.getString("cpf"));
                    eleitor.setTitulo(rs.getString("titulo"));
                    eleitor.setNome(rs.getString("nome"));
                    eleitor.setJavotou(rs.getBoolean("ja_votou"));

                    return eleitor;
                }
            }
        }
        return null;
    }

    public boolean existeCPF(String cpf) throws SQLException{
        String sql = "SELECT cpf FROM eleitores WHERE cpf = ?";

        try (Connection con = Conexao.conectar();
        PreparedStatement ps = con.prepareStatement(sql)){
            ps.setString(1, md5(limparNumeros(cpf)));

            try(ResultSet rs = ps.executeQuery()){
                return rs.next();
            }
        }
    }

    public boolean existeTitulo(String titulo) throws SQLException{
        String sql = "SELECT titulo FROM eleitores WHERE titulo = ?";

        try(Connection con = Conexao.conectar();
        PreparedStatement ps = con.prepareStatement(sql)){
            ps.setString(1, limparNumeros(titulo));

            try(ResultSet rs = ps.executeQuery()){
                return rs.next();
            }
        }
    }

    /**
     * Marca o eleitor como votante usando a conexão da transação do voto.
     * O "AND ja_votou = FALSE" faz a checagem e a gravação num passo só:
     * se o eleitor já tiver votado, nenhuma linha é alterada e retorna false.
     */
    public boolean marcarComoVotou(Connection con, Eleitor eleitor) throws SQLException {
        String sql = "UPDATE eleitores SET ja_votou = TRUE WHERE cpf = ? AND ja_votou = FALSE";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, eleitor.getCpf());
            return ps.executeUpdate() == 1;
        }
    }

    /**
     * Libera todos os eleitores para uma nova eleição. Recebe a conexão da
     * transação de limpeza, para rodar junto com a exclusão dos votos.
     */
    public void resetarVotos(Connection con) throws SQLException {
        String sql = "UPDATE eleitores SET ja_votou = FALSE";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.executeUpdate();
        }
    }

    private static String md5(String texto){
        return Hash.md5(texto);
    }
}
