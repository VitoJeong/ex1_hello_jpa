package helloJPA;

import helloJPA.entity.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class detachMain {
    public static void main(String[] aaa) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            /*
                준영속 상태
            영속 상태의 엔티티가 영속성 컨텍스트에서 분리
            -> 영속성 컨텍스트의 기능을 사용 불가
             */

            // 영속상태
            Member member = em.find(Member.class, 111l);

            member.setUsername("AAAAA");

            // 준영속
            em.detach(member);
            em.clear();

            Member member1 = em.find(Member.class, 111l);


            System.out.println("======================");
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            // 영속성 컨텍스트 종료
            em.close();
        }

        emf.close();
    }
}
