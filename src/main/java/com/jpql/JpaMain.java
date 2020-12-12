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
            Team team = new Team();
            team.setName("team1");
            for (int i = 0; i < 100; i++) {
                Member member = new Member();
                member.setAge(10);
                member.setUsername("member" + i);
                member.setTeam(team);

                em.persist(member);
            }
            em.persist(team);

            em.flush();
            em.clear();

            List<Member> result = em.createQuery("select m from Member m order by m.id desc ", Member.class)
                    .setFirstResult(10)
                    .setMaxResults(20)
                    .getResultList();

            System.out.println("result.size = " + result.size());
            for ( Member member1 : result) {
                System.out.println("member = " + member1);
            }



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
