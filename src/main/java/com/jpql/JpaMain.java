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
            em.persist(team);

            Member member = new Member();
            member.setAge(10);
            member.setUsername("member1");
            member.setType(MemberType.ADMIN);

            member.changeTeam(team);

            em.persist(member);

            em.flush();
            em.clear();

//            String query =
//                    "select " +
//                            "case when m.age <= 10 then '학생요금' " +
//                            "     when m.age >= 60 then '경로요금' " +
//                            "     else '일반요금' " +
//                            "end " +
//                    "from Member m";

//            String query = "select coalesce(m.username, '이름 없는 회원') from Member m";
            String query = "select nullif(m.username, '관리자') from Member m";
            List<String> result = em.createQuery(query, String.class)
                    .getResultList();

            for (String s: result) {
                System.out.println("s = " + s);
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
