import entity.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class jpqlMain {

    public static void main(String[] args) {


        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            // flush -> commit, query
            List<Member> resultList =
                    em.createQuery("SELECT m FROM Member m WHERE m.userName LIKE '%Hello%'",
                            Member.class)
                            .getResultList();

            //Criteria 사용 준비
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Member> query = cb.createQuery(Member.class);

            //루트 클래스 (조회를 시작할 클래스)
            Root<Member> m = query.from(Member.class);

            //쿼리 생성
            CriteriaQuery<Member> cq = query.select(m).where(cb.equal(m.get("userName"), "kim"));
            List<Member> resultListByCriteria = em.createQuery(cq).getResultList();

            // Paging API
            for(int i = 0; i < 100; i++){
                Member member = new Member();
                member.setUserName("member"+i);
                member.setAge(i);
                em.persist(member);
            }

            em.flush();
            em.clear();

            List<Member> resultList1 = em.createQuery("SELECT m FROM Member m ORDER BY m.age DESC", Member.class)
                    .setFirstResult(1)
                    .setMaxResults(10)
                    .getResultList();

            for (Member member : resultList1) {
                System.out.println("member = " + member);
            }

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
