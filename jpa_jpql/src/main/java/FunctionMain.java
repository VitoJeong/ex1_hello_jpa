import entity.Member;
import entity.MemberDTO;
import entity.Team;

import javax.persistence.*;
import java.util.List;

public class FunctionMain {

    public static void main(String[] args) {


        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Member member1 = new Member();
            member1.setUserName("member1");
            member1.setAge(10);
            em.persist(member1);
            Member member2 = new Member();
            member2.setUserName("member2");
            member2.setAge(15);
            em.persist(member2);

            em.flush();
            em.clear();


            //language=HQL
            String query = "SELECT LOCATE('mb', m.userName) FROM Member m";
            List<Integer> result = em.createQuery(query, Integer.class).getResultList();

            for (Integer integer : result) {
                System.out.println("integer = " + integer);
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
