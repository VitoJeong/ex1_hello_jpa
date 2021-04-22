package helloMapping;

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
            Team team = new Team();
            team.setName("Team A");
            em.persist(team);

            Member member = new Member();
            member.setUserName("member1");
            member.setTeam(team); // 단방향 연관관계 설정, 참조 저장
            em.persist(member);

            em.flush();
            em.clear();

            Member findMember = em.find(Member.class, member.getId());
            //
            // Long findTeamId = findMember.getTeamId();
            // Team findTeam = em.find(Team.class, findTeamId);

            // 참조를 사용해서 연관관계 조회
            Team findTeam = findMember.getTeam();
            System.out.println("findTeam = " + findTeam.getName());

            // Team newTeam = em.find(Team.class, 100l);
            // member.setTeam(newTeam);

            List<Member> members = findMember.getTeam().getMembers();
            for(Member m : members) {
                System.out.println("m = " + m.getUserName());
            }
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
        emf.close();


        //  select * from member;
        //  select * from team;
        //
        //  select * from member m join team t on t.team_id = m.team_id;
    }
}
