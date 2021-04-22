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
    @OneToMany(mappedBy = "team")
    private List<Member> members = new ArrayList<>();

    // 연관관계 편의 메서드
    //public void addMember(Member member){
    //    member.setTeam(this);
    //    members.add(member);
    //}
}
