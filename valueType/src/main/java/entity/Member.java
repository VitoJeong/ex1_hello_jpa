package entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @Column(name = "user_name")
    private String userName;

    // 값타입 @Embeddable @Embedded으로 사용
    // 공유 하면 안됨 -> 사이드 이펙트 발생
    // 기간 period
    @Embedded
    private Period workPeriod;

    // 주소 address
    @Embedded
    private Address homeAddress;
    // 임베디드 타입을 사용하기 전과 후에 매핑하는 테이블은 같다!

    @ElementCollection
    @CollectionTable(name = "favorite_food", joinColumns =
        @JoinColumn(name = "member_id"))
    @Column(name = "food_name")
    private Set<String> favoriteFoods = new HashSet<>();

    //@ElementCollection
    //@CollectionTable(name = "address", joinColumns =
    //    @JoinColumn(name = "member_id"))
    //private List<Address> addressHistory = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "member_id")
    private List<AddressEntity> addressHistory = new ArrayList<>();


}
