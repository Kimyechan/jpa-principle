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
            /*// INSERT
            *//*Member member = new Member();
            member.setName("HelloA");
            em.persist(member);*//*

            // SELECT
            Member findMember = em.find(Member.class, 1L);
            List<Member> result =  em.createQuery("select m from Member as m", Member.class)
                    .setFirstResult(5) // Paging
                    .setMaxResults(8)
                    .getResultList();

            // UPDATE
            findMember.setName("HelloJPA");

            // DELETE
//            em.remove(findMember);*/

            /*// 비영속
            Member member = new Member();
            member.setId(101L);
            member.setName("HelloJPA");

            // 영속
            System.out.println("========Before==========");
            em.persist(member);
            System.out.println("========After===========");

            Member findMember = em.find(Member.class, 101L);

            System.out.println("findMember.id = " + findMember.getId());
            System.out.println("findMember.name = " + findMember.getName());

            Member findMember1 = em.find(Member.class, 101L);

            System.out.println("result = " + (findMember == findMember1));

            // 쓰기 지연
            Member member1 = new Member(150L, "A");
            Member member2 = new Member(160L, "B");

            em.persist(member1);
            em.persist(member2);

            System.out.println("==================");*/

            // Dirty Checking - 변경 감지
            Member member = em.find(Member.class, 150L);
            member.setName("ZZZZZZZ");

            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        emf.close();
    }
}
