/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fai.aulas.urnafai;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author rafa-pires
 */
public class CandidatoDAO {

    private static final String SELECT_CANDIDATO =
            "SELECT c.numero, c.nome, c.partido, c.vice, c.foto, g.nome AS cargo "
          + "FROM candidatos c "
          + "JOIN cargo g ON g.id_cargo = c.id_cargo ";

    /**
     * Grava o candidato. O id do cargo é buscado no banco pelo nome
     * (PREFEITO/VEREADOR), sem número fixo no código.
     */
    public void inserir(Candidato candidato) throws SQLException{
        String sql = "INSERT INTO candidatos (numero, nome, partido, vice, id_cargo, foto) "
                   + "SELECT ?, ?, ?, ?, id_cargo, ? FROM cargo WHERE nome = ?";

        try(Connection con = Conexao.conectar();
        PreparedStatement ps = con.prepareStatement(sql)){
            ps.setString(1, candidato.getNumero());
            ps.setString(2, candidato.getNome());
            ps.setString(3, candidato.getPartido());
            ps.setString(4, candidato.getVice());
            ps.setString(5, candidato.getFoto());
            ps.setString(6, candidato.getCargo());

            if(ps.executeUpdate() != 1){
                throw new SQLException("Cargo inválido: " + candidato.getCargo());
            }
        }
    }

    public Candidato buscaPorNumero(String numero) throws SQLException{
        String sql = SELECT_CANDIDATO + "WHERE c.numero = ?";

        try(Connection con = Conexao.conectar();
        PreparedStatement ps = con.prepareStatement(sql)){
            ps.setString(1, numero);

            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    return montarCandidato(rs);
                }
            }
        }
        return null;
    }

    public List<Candidato> listarTodos() throws SQLException{
        String sql = SELECT_CANDIDATO + "ORDER BY c.numero";
        List<Candidato> candidatos = new ArrayList<>();

        try(Connection con = Conexao.conectar();
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery()){
            while(rs.next()){
                candidatos.add(montarCandidato(rs));
            }
        }
        return candidatos;
    }

    public boolean existeNumero(String numero) throws SQLException {
        String sql = "SELECT numero FROM candidatos WHERE numero = ?";

        try(Connection con = Conexao.conectar();
        PreparedStatement ps = con.prepareStatement(sql)){
            ps.setString(1, numero);

            try(ResultSet rs = ps.executeQuery()){
                return rs.next();
            }
        }
    }

    private Candidato montarCandidato(ResultSet rs) throws SQLException{
        Candidato candidato = new Candidato();
        candidato.setNumero(rs.getString("numero"));
        candidato.setNome(rs.getString("nome"));
        candidato.setPartido(rs.getString("partido"));
        candidato.setVice(rs.getString("vice"));
        candidato.setCargo(rs.getString("cargo"));
        candidato.setFoto(rs.getString("foto"));
        return candidato;
    }
}
