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
            /*Address address = new Address("city", "street", "10");

            Member member = new Member();
            member.setUsername("member1");
            member.setAddress(address);
            em.persist(member);

            Address address1 = new Address(address.getCity(), address.getStreet(), address.getZipcode());
            Member member2 = new Member();
            member2.setUsername("member2");
            member2.setHomeAddress(address1);
            em.persist(member2);

            //
//            member.getAddress().setCity("newCity"); // 값 타입 setter 없애기*/


            //
            Address address = new Address("city", "street", "10");

            Member member = new Member();
            member.setUsername("member1");
            member.setAddress(address);
            em.persist(member);

            Address newAddress = new Address("NewCity", address.getStreet(), address.getZipcode());
            member.setAddress(newAddress);


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
