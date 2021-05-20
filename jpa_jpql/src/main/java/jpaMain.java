import entity.Member;
import entity.MemberDTO;
import entity.Team;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class jpaMain {

    public static void main(String[] args) {


        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Member member = new Member();
            member.setUserName("member1");
            member.setAge(10);
            em.persist(member);

            em.flush();
            em.clear();

            // 리턴 타입이 명확할 때
            TypedQuery<Member> query1 = em.createQuery("SELECT m FROM Member m", Member.class);
            List<Member> resultList = query1.getResultList();

            for (Member member1 : resultList) {
                System.out.println("member1 = " + member1);
            }

            // 결과가 하나만 있을때 사용! -> 사용하지 않는 것을 권장
            //query1.getSingleResult();

            // 리턴 타입이 명확하지 않을 때
            Query query2 = em.createQuery("SELECT m FROM Member m");

            // 프로젝션 : 조회할 대상을 지정하는 것
            // 영속성 컨텐스트로 관리
            Member findMember1 = resultList.get(0);
            findMember1.setAge(20);

            TypedQuery<Team> teamQuery2 = em.createQuery("SELECT t FROM Member m JOIN m.team t", Team.class);
            List<MemberDTO> memberDTOList = em.createQuery("SELECT new entity.MemberDTO(m.userName, m.age) FROM Member m", MemberDTO.class).getResultList();


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
