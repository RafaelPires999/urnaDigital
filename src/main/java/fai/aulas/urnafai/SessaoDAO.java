/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fai.aulas.urnafai;

import jakarta.persistence.EntityManager;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author rafa-pires
 */
public class SessaoDAO {
    public int iniciarSessao(String matriculaMesario) throws SQLException {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            em.getTransaction().begin();

            List<Mesario> mesarios = em.createQuery("SELECT m FROM Mesario m WHERE m.matricula = :matricula", Mesario.class)
                    .setParameter("matricula", matriculaMesario)
                    .getResultList();

            if (mesarios.isEmpty()) {
                throw new SQLException("Mesário com matrícula " + matriculaMesario + " não encontrado");
            }

            Sessao sessao = new Sessao();
            sessao.setMesario(mesarios.get(0));
            em.persist(sessao);
            em.getTransaction().commit();

            if (sessao.getIdSessao() == 0) {
                throw new SQLException("Não foi possível obter o id da sessão");
            }
            return sessao.getIdSessao();
        } catch (SQLException | RuntimeException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    public Sessao buscarSessaoAberta() throws SQLException {
        EntityManager em = JPAUtil.getEntityManager();
        List<Sessao> sessoes = em.createQuery(
                  "SELECT s FROM Sessao s "
                + "WHERE s.fim IS NULL "
                + "ORDER BY s.inicio DESC, s.idSessao DESC", Sessao.class)
                .setMaxResults(1)
                .getResultList();
        em.close();

        if (sessoes.isEmpty()) {
            return null;
        }

        Sessao sessao = sessoes.get(0);
        sessao.getMesario().setSenha(null);
        return sessao;
    }

    public void encerrarSessao(int idSessao) throws SQLException {
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();
        em.createQuery("UPDATE Sessao s SET s.fim = CURRENT_TIMESTAMP WHERE s.idSessao = :id")
                .setParameter("id", idSessao)
                .executeUpdate();
        em.getTransaction().commit();
        em.close();
    }
}