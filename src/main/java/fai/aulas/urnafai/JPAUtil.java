/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fai.aulas.urnafai;

import jakarta.persistence.*;

public class JPAUtil {

    private static final EntityManagerFactory EMF =
        Persistence.createEntityManagerFactory("exemplo-jpa");

    public static EntityManager getEntityManager() {
        return EMF.createEntityManager();
    }

    public static void fechar() { EMF.close(); }
}
