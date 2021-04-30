package helloMapping;

import helloMapping.Entity.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class ProxyMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Member member1 = new Member();
            member1.setUserName("VITO");
            em.persist(member1);

            Member member2 = new Member();
            member2.setUserName("Hello");
            em.persist(member2);

            em.flush();
            em.clear();

            //Member findMember = em.find(Member.class, member.getId());

            // 값이 실제로 사용되는 시점에 DB 조회
            System.out.println("find=============================");
           Member findMember = em.find(Member.class, member1.getId());
           Member reference2 = em.getReference(Member.class, member2.getId()); // Proxy Class

            System.out.println("findMember = " + findMember.getClass());
            System.out.println("findMember.id = " + findMember.getId());

            // 영속성의 도움을 받을 수 없는 상태에서 초기화 불가능
            // -> org.hibernate.LazyInitializationException 발생
            //em.detach(reference2);

            System.out.println("isLoaded : " + emf.getPersistenceUnitUtil().isLoaded(reference2));

            // 초기화 시점
            System.out.println("proxy============================");
            System.out.println("reference2.username = " + reference2.getUserName()); // 초기화 -> target 호출
            System.out.println("isLoaded : " + emf.getPersistenceUnitUtil().isLoaded(reference2));

            // proxy object는 entity를 상속받음, 타입체크시 instanceof 사용
            System.out.println("findMember.getClass() == reference2.getClass() : " + (findMember.getClass() == reference2.getClass()));
            System.out.println("findMember instanceof Member : " + (findMember instanceof Member));
            System.out.println("reference2 instanceof Member : " + (reference2 instanceof Member));

            Member reference1 = em.getReference(Member.class, member1.getId());

            // 영속성컨텍스트에 entity가 이미 있으면 실제엔티티반환
            System.out.println("reference1 : " + reference1.getClass());

            System.out.println("findMember == reference1 : " + (findMember == reference1));

            Member findMember2 = em.find(Member.class, member2.getId());

            // JPA가 한 영속성컨텍스트 내에서 동일성보장
            System.out.println("reference2 == findMember2 : " + (reference2 == findMember2));

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
