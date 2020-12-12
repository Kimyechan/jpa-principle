package com.hellojpa;

import org.hibernate.Hibernate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Set;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        tx.begin();
        try {
            Member member = new Member();
            member.setUsername("member1");
            member.setAddress(new Address("city1", "street", "10"));

            member.getFavoriteFoods().add("치킨");
            member.getFavoriteFoods().add("피자");
            member.getFavoriteFoods().add("족발");

//            member.getAddressesHistory().add(new Address("old1", "street1", "10"));
//            member.getAddressesHistory().add(new Address("old2", "street2", "20"));

            member.getAddressHistory().add(new AddressEntity("old1", "street1", "10"));
            member.getAddressHistory().add(new AddressEntity("old2", "street2", "20"));

            em.persist(member);

            em.flush();
            em.clear();
            System.out.println("====================================");
            /*Member findMember = em.find(Member.class, member.getId());

            List<Address> addressHistory = findMember.getAddressesHistory();
            for (Address address : addressHistory) {
                System.out.println(address);
            }

            Set<String> favoriteFoods = findMember.getFavoriteFoods();
            for (String favoriteFood: favoriteFoods) {
                System.out.println(favoriteFood);
            }*/

            Member findMember = em.find(Member.class, member.getId());

            // homeCity -> newCity
//            findMember.getHomeAddress().setCity("newCity");

            Address a = findMember.getAddress();
            findMember.setAddress(new Address("newCity", a.getStreet(), a.getZipcode()));

            // 치킨 -> 한식
            findMember.getFavoriteFoods().remove("치킨");
            findMember.getFavoriteFoods().add("한식");

            findMember.getAddressHistory().remove(new AddressEntity("old1", "street1", "10"));
            findMember.getAddressHistory().add(new AddressEntity("newCity", "street1", "10"));

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
