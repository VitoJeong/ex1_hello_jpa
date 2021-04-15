package helloJPA;

import helloJPA.entity.Member;
import helloJPA.jpaEnum.RoleType;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class EntityMapping {

    public static void main(String[] aaa) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            // SELECT * FROM member;
            Member member = new Member();

            // GeneratedValue
            // member.setId(1l);
            member.setUsername("hello1");
            member.setRoleType(RoleType.USER);

            em.persist(member);
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
