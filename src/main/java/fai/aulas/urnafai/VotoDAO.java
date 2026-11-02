package fai.aulas.urnafai;

import jakarta.persistence.EntityManager;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author rafa-pires
 */
public class VotoDAO {

    private final EleitorDAO eleitorDAO = new EleitorDAO();

    public void registrarVotacao(Voto voto, Eleitor eleitor) throws SQLException {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            em.getTransaction().begin();

            if (!eleitorDAO.marcarComoVotou(em, eleitor)) {
                throw new SQLException("Eleitor já votou ou não está cadastrado");
            }

            for (ItemVoto item : voto.getItens()) {
                List<Cargo> cargos = em.createQuery("SELECT c FROM Cargo c WHERE c.nome = :nome", Cargo.class)
                        .setParameter("nome", item.getCargo())
                        .getResultList();

                if (cargos.isEmpty()) {
                    throw new SQLException("Cargo inválido: " + item.getCargo());
                }

                em.persist(new RegistroVoto(voto.getIdSessao(),
                                            cargos.get(0).getIdCargo(),
                                            CifraCesar.cifrar(item.getValor())));
            }

            em.getTransaction().commit();
            eleitor.setJavotou(true);
        } catch (SQLException | RuntimeException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    public Map<String, Integer> apurar(String cargo) throws SQLException {
        EntityManager em = JPAUtil.getEntityManager();
        List<Object[]> linhas = em.createQuery(
                  "SELECT v.voto, COUNT(v) "
                + "FROM RegistroVoto v, Cargo g "
                + "WHERE g.idCargo = v.idCargo AND g.nome = :cargo "
                + "GROUP BY v.voto "
                + "ORDER BY COUNT(v) DESC", Object[].class)
                .setParameter("cargo", cargo)
                .getResultList();
        em.close();

        Map<String, Integer> resultado = new LinkedHashMap<>();
        for (Object[] linha : linhas) {
            resultado.put(CifraCesar.decifrar((String) linha[0]),
                          ((Long) linha[1]).intValue());
        }
        return resultado;
    }

    public void limparVotos() throws SQLException {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            em.getTransaction().begin();
            em.createQuery("DELETE FROM RegistroVoto").executeUpdate();
            eleitorDAO.resetarVotos(em);
            em.getTransaction().commit();
        } catch (RuntimeException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }
}