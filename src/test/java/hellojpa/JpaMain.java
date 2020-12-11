package hellojpa;

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

           /* // 비영속
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

            /*// Dirty Checking - 변경 감지
            Member member = em.find(Member.class, 150L);
            member.setName("ZZZZZZZ");*/

            /*Member member = new Member(200L, "member200");
            em.persist(member);

            em.flush(); // persistence context 와 DB를 동기화, persistence context를 비우지 않음

            System.out.println("=================");*/

            /*Member member = em.find(Member.class, 150L);
            member.setName("AAAAA");

//            em.detach(member);
            em.clear();

            Member member2 = em.find(Member.class, 150L);
            System.out.println("================");*/

           /* // 테이블 중심 설계 - 잘못됨
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("member1");
            member.setTeamId(team.getId());
            em.persist(member);

            Member findMember = em.find(Member.class, member.getId());

            Long findTeamId = findMember.getTeamId();
            Team findTeam = em.find(Team.class, findTeamId);*/

            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("member1");
            member.changeTeam(team); // 항상 양 객체에 둘 다 추가해주자
            em.persist(member);

            // 항상 양 객체에 둘 다 추가해주자
//            team.getMembers().add(member);
//            team.addMember(member);
            em.flush();
            em.clear();

            Member findMember = em.find(Member.class, member.getId());

            Team findTeam = findMember.getTeam();
            System.out.println("findTeam = " + findTeam.getName());

            // 무한 루프
//            System.out.println(findTeam);

//            // 팀 변경
//            Team newTeam = em.find(Team.class, 100L);
//            findMember.setTeam(newTeam);

            List<Member> members = findMember.getTeam().getMembers();

            for (Member m : members) {
                System.out.println("m = " + m.getUsername());
            }

            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        emf.close();
    }
}
