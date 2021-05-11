package entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

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

}
