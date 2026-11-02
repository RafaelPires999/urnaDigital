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
class MesarioDAO {

    public void inserir(Mesario mesario) throws SQLException {
        mesario.setSenha(Hash.senha(mesario.getUsuario(), mesario.getSenha()));
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();
        em.persist(mesario);
        em.getTransaction().commit();
        em.close();
    }

    public Mesario autenticar(String usuario, String senha) throws SQLException {
        EntityManager em = JPAUtil.getEntityManager();
        List<Mesario> mesarios = em.createQuery("SELECT m FROM Mesario m WHERE m.usuario = :usuario", Mesario.class)
                .setParameter("usuario", usuario)
                .getResultList();
        em.close();
        
        if(!mesarios.isEmpty()){
            Mesario m = mesarios.get(0);
            String hashDigitado = Hash.senha(m.getUsuario(), senha);
            
            if(Hash.iguais(hashDigitado, m.getSenha())){
                m.setSenha(null);
                return m;
            }
        }
        return null;
    }

    public boolean existeUsuario(String usuario) throws SQLException {
        EntityManager em =JPAUtil.getEntityManager();
        List<Mesario> mesarios = em.createQuery("SELECT m FROM Mesario m WHERE m.usuario = :usuario", Mesario.class)
                .setParameter("usuario", usuario)
                .getResultList();
        em.close();
        return !mesarios.isEmpty();
    }

    public boolean existeMatricula(String matricula) throws SQLException {
        EntityManager em = JPAUtil.getEntityManager();
        List<Mesario> mesarios = em.createQuery("SELECT m FROM Mesario m WHERE m.matricula = :matricula", Mesario.class)
                .setParameter("matricula", matricula)
                .getResultList();
        em.close();
        return !mesarios.isEmpty();
    }
}
