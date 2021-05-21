import entity.Member;
import entity.MemberDTO;
import entity.MemberType;
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
            member.setAge(10);
            member.setType(MemberType.ADMIN);

            member.changeTeam(team);
            em.persist(member);

            em.flush();
            em.clear();

            // 타입표현
            String query = "SELECT m FROM Member m, Team t " +
                    "WHERE m.type = entity.MemberType.ADMIN";
            List<Member> result = em.createQuery(query, Member.class).getResultList();

            // 조건식
            query = "SELECT " +
                        "CASE " +
                        "   WHEN m.age <= 10 THEN '학생요금' " +
                        "   WHEN m.age >= 60 THEN '경로요금' " +
                        "ELSE '일반요금' " +
                    "END " +
                    "FROM Member m";
            List<String> resultList = em.createQuery(query, String.class).getResultList();

            query = "SELECT COALESCE(m.userName, '이름 없는 회원') " +
                    "FROM Member m";
            resultList = em.createQuery(query, String.class).getResultList();

            for (String str : resultList) {
                System.out.println("str = " + str);
            }
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
