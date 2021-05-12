import entity.Address;
import entity.AddressEntity;
import entity.Member;
import entity.Period;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Set;

public class valueCollectionMain {

    public static void main(String[] args) {


        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Member member = new Member();
            member.setUserName("member1");
            member.setHomeAddress(new Address("city", "street", "12222"));

            member.getFavoriteFoods().add("치킨");
            member.getFavoriteFoods().add("Hamburger");
            member.getFavoriteFoods().add("피자");

            member.getAddressHistory().add(new AddressEntity("one", "main", "222222"));
            member.getAddressHistory().add(new AddressEntity("two", "ss", "3333333"));

            em.persist(member);

            em.flush();
            em.clear();

            Member findMember = em.find(Member.class, member.getId());

            //System.out.println(findMember);

            //List<Address> addressHistory = findMember.getAddressHistory();
            //for(Address address : addressHistory){
            //    System.out.println("address = " + address.getCity());
            //}
            //
            //Set<String> favoriteFoods = findMember.getFavoriteFoods();
            //for (String favoriteFood : favoriteFoods){
            //    System.out.println("favoriteFood = " + favoriteFood);
            //}

            findMember.getFavoriteFoods().remove("치킨");
            findMember.getFavoriteFoods().add("불고기");

            findMember.getAddressHistory().remove(new AddressEntity("two", "ss", "3333333"));
            findMember.getAddressHistory().add(new AddressEntity("zero", "ss", "3333333"));

            // 식별자가 필요하고, 지속해서 값을 추적, 변경해야 한다면 그것 은 값 타입이 아닌 '엔티티'
            
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
