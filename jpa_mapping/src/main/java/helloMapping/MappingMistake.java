package helloMapping;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class MappingMistake {

    public static void main(String[] args){

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{

            Team team = new Team();
            team.setName("RealMadrid");
            em.persist(team);

            Member member1 = new Member();
            member1.setUserName("Ramos");
            Member member2 = new Member();
            member2.setUserName("Kroos");

            // 가짜 매핑에만 연관관계 설
            // team.getMembers().add(member);

            // 연관관계의 주인에 값 설정
            member1.setTeam(team);
            em.persist(member1);
            em.persist(member2);

            team.getMembers().add(member1);
            member2.changeTeam(team);

            //em.flush();
            //em.clear();

            team = em.find(Team.class, team.getId());
            List<Member> members = team.getMembers();

            System.out.println("=======================");
            for(Member m : members) {
                System.out.println("m = " + m.getUserName());
            }
            //System.out.println(team);
            // -> StackOverflowError 발생 toString()으로 인해서
            System.out.println("=======================");

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
