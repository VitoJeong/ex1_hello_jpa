package helloMapping;

import helloMapping.Entity.Member;
import helloMapping.Entity.Parent;
import helloMapping.Entity.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class CascadeMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Parent parent = new Parent();

            Child child1 = new Child();
            Child child2 = new Child();

            parent.addChild(child1);
            parent.addChild(child2);

            em.persist(parent);
            em.persist(child1);
            em.persist(child2);

            em.flush();
            em.clear();

            Parent findParent = em.find(Parent.class, parent.getId());
            findParent.getChildList().remove(0);

            em.remove(findParent);

            // CascadeType.ALL 영속성 전이
            // -> 엔티티 하나에 종속적일때만 사용!!!(단일 소유)

            // orphanRemoval = true 고아객 제거기능(CascadeType.REMOVE)
            // -> 엔티티 하나에 종속적일때만 사용!!!(단일 소유)

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
