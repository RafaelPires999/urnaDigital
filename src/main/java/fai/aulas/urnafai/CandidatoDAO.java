/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fai.aulas.urnafai;

import jakarta.persistence.EntityManager;
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

    public void inserir(Candidato candidato){
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();
        em.persist(candidato);
        em.getTransaction().commit();
        em.close();
    }
    
    public Integer buscarIdCargo(String nomeCargo) {
        EntityManager em = JPAUtil.getEntityManager();
        List<Cargo> cargos = em.createQuery("SELECT c FROM Cargo c WHERE c.nome = :nome", Cargo.class)
                .setParameter("nome", nomeCargo)
                .getResultList();
        em.close();
        return cargos.isEmpty() ? null : cargos.get(0).getIdCargo();
    }

    public Candidato buscaPorNumero(String numero) throws SQLException {
        EntityManager em = JPAUtil.getEntityManager();
        Candidato c = em.find(Candidato.class, numero);
        if (c != null) {
            Cargo cargo = em.find(Cargo.class, c.getIdCargo());
            c.setCargo(cargo.getNome());
        }
        em.close();
        return c;
    }

    public List<Candidato> listarTodos() throws SQLException{
        EntityManager em = JPAUtil.getEntityManager();
        List<Candidato> candidatos = em.createQuery("SELECT c FROM Candidato c ORDER BY c.numero", Candidato.class)
                .getResultList();
        for(Candidato c : candidatos){
            Cargo cargo = em.find(Cargo.class, c.getIdCargo());
            c.setCargo(cargo.getNome());
        }
        
        em.close();
        return candidatos;
    }

    public boolean existeNumero(String numero) throws SQLException {
        EntityManager em = JPAUtil.getEntityManager();
        Candidato c = em.find(Candidato.class, numero);
        em.close();
        return c != null;
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
