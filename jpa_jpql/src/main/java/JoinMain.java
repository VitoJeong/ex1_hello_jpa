import entity.Member;
import entity.MemberDTO;
import entity.Team;

import javax.persistence.*;
import java.util.List;

public class JoinMain {

    public static void main(String[] args) {


        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Team team = new Team();
            team.setName("teamA");
            em.persist(team);

            Member member = new Member();
            member.setUserName("member1");
            member.setAge(12);

            member.changeTeam(team);
            em.persist(member);

            em.flush();
            em.clear();

            String query = "SELECT m FROM Member m, Team t where m.userName = t.name";
            List<Member> result = em.createQuery(query, Member.class).getResultList();

            tx.commit();
        } catch (Exception e){
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }

        emf.close();
    }
}
