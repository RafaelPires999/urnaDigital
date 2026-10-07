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
class MesarioDAO {

    /** Grava o mesário com a senha em hash SHA-256, nunca em texto puro. */
    public void inserir(Mesario mesario) throws SQLException {
        String sql = "INSERT INTO mesarios (nome, matricula, usuario, senha) VALUES (?, ?, ?, ?)";

        try (Connection con = Conexao.conectar();
        PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, mesario.getNome());
            ps.setString(2, mesario.getMatricula());
            ps.setString(3, mesario.getUsuario());
            ps.setString(4, Hash.senha(mesario.getUsuario(), mesario.getSenha()));
            ps.executeUpdate();
        }
    }

    /**
     * Login do mesário: busca pelo usuário e compara o hash da senha digitada
     * com o hash gravado. Retorna o mesário (sem a senha) ou null.
     */
    public Mesario autenticar(String usuario, String senha) throws SQLException {
        String sql = "SELECT nome, matricula, usuario, senha FROM mesarios WHERE usuario = ?";

        try (Connection con = Conexao.conectar();
        PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, usuario);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String hashDigitado = Hash.senha(rs.getString("usuario"), senha);

                    if (Hash.iguais(hashDigitado, rs.getString("senha"))) {
                        Mesario mesario = new Mesario();
                        mesario.setNome(rs.getString("nome"));
                        mesario.setMatricula(rs.getString("matricula"));
                        mesario.setUsuario(rs.getString("usuario"));
                        return mesario;
                    }
                }
            }
        }
        return null;
    }

    public boolean existeUsuario(String usuario) throws SQLException {
        String sql = "SELECT usuario FROM mesarios WHERE usuario = ?";
        try (Connection con = Conexao.conectar();
        PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, usuario);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }

    public boolean existeMatricula(String matricula) throws SQLException {
        String sql = "SELECT matricula FROM mesarios WHERE matricula = ?";
        try (Connection con = Conexao.conectar();
        PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, matricula);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }
}
