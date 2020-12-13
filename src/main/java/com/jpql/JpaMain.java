package com.jpql;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Collection;
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
            em.persist(team);

            Member member = new Member();
            member.setAge(10);
            member.setUsername("member1");
            member.setType(MemberType.ADMIN);

            member.changeTeam(team);

            em.persist(member);

            em.flush();
            em.clear();

//            String query = "select m.username from Member m";
//            String query = "select m.team.name from Member m";
//            String query = "select t.members.size from Team t";
            String query = "select m.username from Team t join t.members m";
//            List<String> result = em.createQuery(query, String.class)
//                    .getResultList();
            String result = em.createQuery(query, String.class)
                    .getSingleResult();

//            for (String s: result) {
//                System.out.println("s = " + s);
//            }

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
