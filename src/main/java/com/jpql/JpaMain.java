package com.jpql;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        tx.begin();
        try {
            Member member = new Member();
            member.setAge(10);
            member.setUsername("member1");

            em.persist(member);

            TypedQuery<Member> query = em.createQuery("select m from Member m", Member.class);
            TypedQuery<String> query1 = em.createQuery("select m.username from Member m", String.class);
            Query query2 = em.createQuery("select m.username, m.age from Member m");

            List<Member> members = query.getResultList();

            TypedQuery<Member> query3 = em.createQuery("select m from Member m where m.id = 1", Member.class);
            Member member1 = query3.getSingleResult();

            TypedQuery<Member> query4 = em.createQuery("select m from Member m where m.id = :username", Member.class);
            query4.setParameter("username", "member1");
            Member member2 = query4.getSingleResult();

            Member member3 = em.createQuery("select m from Member m where m.id = :username", Member.class)
                    .setParameter("username", "member1")
                    .getSingleResult();

            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        emf.close();
    }

    private static void printMember(Member member) {
        System.out.println("member = " + member.getUsername());
    }
}
