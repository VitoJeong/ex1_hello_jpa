package helloMapping;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class OneToOneMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            // 일대일 연관관계 매핑
            // 외래키에 unique제약조건 추가
            // 외래키가 있는곳이 연관관계의 주인

            Locker locker = new Locker();
            locker.setNumber("927330");
            em.persist(locker);

            // 주 테이블(자주 접근)에 외래 키를 두고 대상 테이블을 찾음
            Player player = new Player();
            player.setName("Reus");
            player.setLocker(locker);
            em.persist(player);

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
