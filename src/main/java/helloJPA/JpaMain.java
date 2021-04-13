package helloJPA;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        // EntityManagerFactory : 애플리케이션을 실행할때 하나만 생성되서 전체에서 공유
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        // EntityManager : 요청이 들어올때마다 생성되고 close로 자원반납
        // 스레드간 공유 x
        EntityManager em = emf.createEntityManager();
        // 데이터 변경은 트랜잭션 내에서 실행해야함!!
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            // Member member = em.find(Member.class, 1l);

            // JPQL : 테이블이 아닌 '엔티티' 객체를 대상으로 검색
            List<Member> result = em.createQuery("SELECT m FROM Member m", Member.class)
                    .setFirstResult(4)
                    .setMaxResults(10)
                    .getResultList();

            for(Member member : result) {
                System.out.printf("member.id : %d member.name : %s \n", member.getId(), member.getName());
            }

            // 영속성 컨텍스트(Persistence Context)
            // 생명주
            // 비영속
            Member member = new Member();
            member.setId(111l);
            member.setName("PersistenceContext");

            System.out.println("=== Before ===");
            // 영속 : 영속성 컨텍스트에서 관리
            // em.persist(member);
            // 준영속 : 영속성 컨텍스트에서 저장되었다가 분리됨
            // em.detach(member);
            // 삭제
            // em.remove(member);
            System.out.println("=== After ===");

            Member member1 = new Member(22l, "kong");
            Member member2 = new Member(29l, "BEAN");

            //em.persist(member1);
            //em.persist(member2);
            System.out.println("=======================");
            // 커밋하는 순간 sql을 DB에 보낸다.
            // commit() -> flush -> commit

            member = em.find(Member.class, 111l);
            member.setName("Persistence Context");
            // flush() 할때에 스냅샷을 비교하여 update sql 생성
            /*
            ** 영속성 컨텍스트 장점 **
            1차 캐싱(트랜잭션 내에서)
            동일성 보장
            쓰기 지연
            변경 감지
            지연 로딩
            */
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();

    }
}
