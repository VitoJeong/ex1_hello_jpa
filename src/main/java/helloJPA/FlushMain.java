package helloJPA;

import helloJPA.entity.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class FlushMain {
    public static void main(String[] args){

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            /*
                플러시
            영속성 컨텍스트의 변경내용을 DB에 반영
            변경감지(Dirty Checking)
            수정된 엔티티 쓰기지연 sql 저장소에 등록
            .commit();
            저장된 쓰기지연 SQL 저장의 쿼리를 DB에 전송 (등록, 수정, 삭제 Query)
             */
            Member member = new Member(123l, "123member");
            //em.persist(member);

            em.flush();
            member = em.find(Member.class, 123l);
            member.setUsername("member123");
            System.out.println("======================");
            /*
                플러시 호출 방법
            em.flush() : 직접호출
            트랜잭션 커밋 : 자동호출
            JPQL 실행 : 자동호출
             */

            /*
                플러시는 영속성 컨텍스트를 비우지않음 -> 변경내용을 DB에 동기화
                커밋 직전에만 동기화 하면 됨
                -> 트랜잭션이랑는 작업 단위가 중요!
             */
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
