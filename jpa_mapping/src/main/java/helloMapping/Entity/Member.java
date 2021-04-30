package helloMapping.Entity;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Member extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String userName;

    //@Column(name = "TEAM_ID")
    //private Long teamId;

    // [member : Many] to [team : One]
    // JoinColumn : 연관관계의 주인(Owner)
    //@ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "TEAM_ID")
    //private Team team;

    // 다대일 양방향 사용!!!
    //@JoinColumn(name = "TEAM_ID", insertable = false, updatable = false)
    // 일대다 양방향 -> 읽기 전용필드 사용 (지연로딩 사용 불가능)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    // 연관관계 편의 메서드
    //public void changeTeam(Team team){
    //    this.team = team;
    //    team.getMembers().add(this);
    //}
}
