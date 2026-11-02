/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fai.aulas.urnafai;

import jakarta.persistence.EntityManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
/**
 *
 * @author rafa-pires
 */
public class EleitorDAO {

    private static String limparNumeros(String texto){
        return texto == null ? "" : texto.replaceAll("[^0-9]", "");
    }

    public void inserir(Eleitor eleitor) throws SQLException{
        eleitor.setCpf(md5(limparNumeros(eleitor.getCpf())));
        eleitor.setTitulo(limparNumeros(eleitor.getTitulo()));
        eleitor.setJavotou(false);
        
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();
        em.persist(eleitor);
        em.getTransaction().commit();
        em.close();
    }

    public Eleitor buscarPorCPF(String cpf) throws SQLException {
        EntityManager em = JPAUtil.getEntityManager();
        List<Eleitor> eleitores = em.createQuery("SELECT e FROM Eleitor e WHERE e.cpf = :cpf", Eleitor.class)
                .setParameter("cpf", md5(limparNumeros(cpf)))
                .getResultList();
        em.close();
        return eleitores.isEmpty() ? null : eleitores.get(0);
    }

    public boolean existeCPF(String cpf) throws SQLException{
        EntityManager em = JPAUtil.getEntityManager();
        List<Eleitor> eleitores = em.createQuery("SELECT e FROM Eleitor e WHERE e.cpf = :cpf", Eleitor.class)
                .setParameter("cpf", md5(limparNumeros(cpf)))
                .getResultList();
        em.close();
        return !eleitores.isEmpty();
    }

    public boolean existeTitulo(String titulo) throws SQLException{
        EntityManager em = JPAUtil.getEntityManager();
        List<Eleitor> eleitores = em.createQuery("SELECT e FROM Eleitor e WHERE titulo = :titulo", Eleitor.class)
                .setParameter("titulo", limparNumeros(titulo))
                .getResultList();
        em.close();
        return !eleitores.isEmpty();
    }

    public boolean marcarComoVotou(EntityManager em, Eleitor eleitor){
        int linha = em.createQuery("UPDATE Eleitor e SET e.javotou = true WHERE e.cpf = :cpf AND e.javotou = false")
                .setParameter("cpf", eleitor.getCpf())
                .executeUpdate();
        return linha == 1;
    }

    public void resetarVotos(EntityManager em){
        em.createQuery("UPDATE Eleitor e SET e.javotou = false").executeUpdate();
    }
    
    private static String md5(String texto){
        return Hash.md5(texto);
    }
}
