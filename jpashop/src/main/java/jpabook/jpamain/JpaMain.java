package jpabook.jpamain;


import jpabook.jpashop.domain.*;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            //
            //Order order = new Order();
            //em.persist(order);
            //
            //OrderItem orderItem = new OrderItem();
            //// 주인에 값을 설정!!
            //orderItem.setOrder(order);
            //em.persist(orderItem);

            // order.addOrderItem(new OrderItem());

            Book book = new Book();
            book.setName("JPA");
            book.setAuthor("KYH");

            em.persist(book);

            em.createQuery("SELECT i FROM Item i WHERE TYPE(i) = Book ", Item.class).getResultList();

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
