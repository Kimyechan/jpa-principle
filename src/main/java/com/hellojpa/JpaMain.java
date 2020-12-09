package com.hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        tx.begin();
        try {
            // INSERT
            /*Member member = new Member();
            member.setName("HelloA");
            em.persist(member);*/

            // SELECT
            Member findMember = em.find(Member.class, 1L);
            List<Member> result =  em.createQuery("select m from Member as m", Member.class)
                    .setFirstResult(5) // Paging
                    .setMaxResults(8)
                    .getResultList();

            // UPDATE
            findMember.setName("HelloJPA");

            // DELETE
//            em.remove(findMember);

            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        emf.close();
    }
}
