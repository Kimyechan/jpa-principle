package com.hellojpa;

import org.hibernate.Hibernate;

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
//            Member member = em.find(Member.class, 1L);
//
//            printMember(member);
//            printMemberAndTeam(member);

           /* Member member = new Member();
            member.setUsername("hello");

            em.persist(member);

            em.flush();
            em.clear();

            //
//            Member findMember = em.find(Member.class, member.getId());
            Member findMember = em.getReference(Member.class, member.getId());
            System.out.println(findMember.getClass());
            System.out.println(findMember.getId());
            System.out.println(findMember.getUsername());
            System.out.println(findMember.getUsername());*/

//            Member member1 = new Member();
//            member1.setUsername("member1");
//            em.persist(member1);
//
//            Member member2 = new Member();
//            member1.setUsername("member2");
//            em.persist(member2);
//
//            em.flush();
//            em.clear();
//
//            Member m1 = em.find(Member.class, member1.getId());
//            Member m2 = em.getReference(Member.class, member2.getId());
//
//            System.out.println("m1 == m2" + (m1.getClass() == m2.getClass()));
//            System.out.println(m1 instanceof Member);
//            System.out.println(m2 instanceof Member);

            /*Member member1 = new Member();
            member1.setUsername("member1");
            em.persist(member1);

            em.flush();
            em.clear();

            Member m1 = em.find(Member.class, member1.getId());
            System.out.println(m1.getClass());

            Member reference = em.getReference(Member.class, member1.getId());
            System.out.println("reference = " + reference.getClass());
            System.out.println("a == a : " + (m1 == reference));*/

            /*Member member1 = new Member();
            member1.setUsername("member1");
            em.persist(member1);

            em.flush();
            em.clear();

            Member refMember = em.getReference(Member.class, member1.getId());
            System.out.println(refMember.getClass());

            Member findMember = em.find(Member.class, member1.getId());
            System.out.println("reference = " + findMember.getClass());
            System.out.println("a == a : " + (refMember == findMember));*/

            /*Member member1 = new Member();
            member1.setUsername("member1");
            em.persist(member1);

            em.flush();
            em.clear();

            Member refMember = em.getReference(Member.class, member1.getId());
            System.out.println(refMember.getClass()); // proxy

            em.detach(refMember);

            System.out.println(refMember.getUsername());*/

            Member member1 = new Member();
            member1.setUsername("member1");
            em.persist(member1);

            em.flush();
            em.clear();

            Member refMember = em.getReference(Member.class, member1.getId());
            System.out.println(refMember.getClass()); // proxy
//            refMember.getUsername();
            Hibernate.initialize(refMember); // 강제 초기화
            System.out.println("isLoaded = " + emf.getPersistenceUnitUtil().isLoaded(refMember));

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

    private static void printMemberAndTeam(Member member) {
        String username = member.getUsername();
        System.out.println("username = " + username);

        Team team = member.getTeam();
        System.out.println("team = " + team.getName());
    }
}
