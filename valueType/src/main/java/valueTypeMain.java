import entity.Address;
import entity.Member;
import entity.Period;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class valueTypeMain {

    public static void main(String[] args) {


        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Address address = new Address("city", "street", "10000");

            Member member1 = new Member();
            member1.setUserName("Hello");
            member1.setHomeAddress(address);
            member1.setWorkPeriod(new Period());
            em.persist(member1);

            Address copiedAddress = new Address(address.getCity(), address.getStreet(), address.getZipcode());

            Member member2 = new Member();
            member2.setUserName("member2");
            member2.setHomeAddress(copiedAddress);
            member2.setWorkPeriod(new Period());
            em.persist(member2);


            // member1.getHomeAddress().setCity("NewYork");
            // -> side effect!!!
            // 객체의 공유 참조는 피할 수 없다. (기본타입은 값을 복사)

            // 해결책
            // immutable object로 설계함 -> 생성자로만 값을 설정

            // 값 타입 비교
            System.out.println("address == copiedAddress : " + (address == copiedAddress));
            System.out.println("address.equals(copiedAddress) : " + (address.equals(copiedAddress)));

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
