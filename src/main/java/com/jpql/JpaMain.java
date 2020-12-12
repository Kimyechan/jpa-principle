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

            Member member = new Member();
            member.setAge(10);
            member.setUsername("member1");
            member.setTeam(team);

            em.persist(member);
            em.persist(team);

            em.flush();
            em.clear();

            List<Member> members = em.createQuery("select m from Member m join m.team t", Member.class)
                                        .getResultList();

            Member member1 = members.get(0);
            member1.setAge(20);

            em.createQuery("select o.address from  Order o", Address.class)
                    .getResultList();

            List result = em.createQuery("select distinct m.username, m.age from  Member m")
                    .getResultList();

            Object o = result.get(0);
            Object[] member2 = (Object[])o;
            System.out.println("username = " + member2[0]);
            System.out.println("age = " + member2[1]);

            List<Object[]> result1 = em.createQuery("select distinct m.username, m.age from  Member m")
                    .getResultList();

            List<MemberDTO> result2 = em.createQuery("select new com.jpql.MemberDTO(m.username, m.age) from  Member m", MemberDTO.class)
                    .getResultList();



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
