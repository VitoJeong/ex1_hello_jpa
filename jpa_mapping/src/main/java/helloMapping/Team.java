package helloMapping;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Team {

    @Id @GeneratedValue
    @Column(name = "TEAM_ID")
    private Long id;
    private String name;

    // Member의 team 필드로 mapping됨.
    // mappedBy (조회만 가능)
    // 가짜 매핑 : 읽기만 가능
    //@OneToMany(mappedBy = "team")
    //private List<Member> members = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "TEAM_ID")
    private List<Member> members = new ArrayList<>();
    // 일대다 매핑 : 1이 연관관계의 주인
    // @JoinColumn이 없으면 조테이블 생성
    // 왜래키가 다른테이블에 있음 -> 다대일 양방향 권장

    // 연관관계 편의 메서드
    //public void addMember(Member member){
    //    member.setTeam(this);
    //    members.add(member);
    //}
}
