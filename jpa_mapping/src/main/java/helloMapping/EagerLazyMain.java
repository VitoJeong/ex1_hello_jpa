package helloMapping;

import helloMapping.Entity.Member;
import helloMapping.Entity.Team;

import javax.persistence.*;
import java.util.List;

public class EagerLazyMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Team team = new Team();
            team.setName("IMS");
            em.persist(team);

            Team teamB = new Team();
            teamB.setName("TEAM B");
            em.persist(teamB);

            Member member1 = new Member();
            member1.setUserName("VITO");
            member1.setTeam(team);
            em.persist(member1);

            Member member2 = new Member();
            member2.setUserName("HELLO");
            member2.setTeam(teamB);
            em.persist(member2);

            em.flush();
            em.clear();

            //Member m = em.find(Member.class, member1.getId());
            //System.out.println("m.team.getClass() : " + m.getTeam().getClass()); // proxy로 조회

            /*
            System.out.println("=============="); // 초기화
            System.out.println("team Name : " + m.getTeam().getName());
            System.out.println("==============");
            */

            List<Member> members = em.createQuery("SELECT m FROM Member m", Member.class)
                    .getResultList();

            // EAGER
            // SELECT * FROM member;
            // SELECT * FROM team WHERE team_id = ?  N+1 문제 발생!!


            // Member와 Team을 자주 함께 사용한다면?
            // -> (fetch = FetchType.EAGER) 즉시로딩 사용
            // -> JOIN을 통해 한번에 조회

            // 가급적 지연로딩만 사용!!(특히 실무)
            // @ManyToOne, @OneToOne은 Default : EAGER -> LAZY로 변경
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
        emf.close();

    }
}
