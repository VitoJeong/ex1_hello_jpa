package helloMapping;

import helloMapping.Entity.Item;
import helloMapping.Entity.Movie;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class SupSubMapping {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Movie movie = new Movie();
            movie.setDirector("Quentin Tarantino");
            movie.setActor("Leo");
            movie.setName("장고");
            movie.setPrice(10000);

            em.persist(movie);

            // 영속성 컨텍스트 DB 반영
            em.flush();
            // 영속성 컨텍스트 초기
            em.clear();

            //Movie findMovie = em.find(Movie.class, movie.getId());
            Item item = em.find(Item.class, movie.getId());
            System.out.println("findMovie = " + item);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
        emf.close();


        // select * from item;
        // select * from movie;
    }

}
