package helloJPA.entity;

import helloJPA.jpaEnum.RoleType;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@SequenceGenerator(name = "member_seq_generator", allocationSize = 1, sequenceName = "member_seq")
public class Member {

    @Id // @Id만 사용 : 직접할당
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_seq") // 자동생성
    private Long id;
    // IDENTITY 전략은 em.persist() 시점에 즉시 INSERT SQL 실행 하고 DB에서 식별자를 조회

    // 컬럼 매핑
    @Column(name = "name")
    private String username;

    private Integer age;

    // enum 타입 매핑 (DB에는 enum이 없음)
    // EnumType.ORDINAL 사용 X -> 순서로 저장
    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    // 날짜 매핑
    // 최싱하이버네이트는 LocalDate LocalDateTime으로 가능
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    // 긴 글
    @Lob
    private String description;

    // @Transient 필드를 컬럼에 매핑하지 않음

    public Member() {
    }

    public Member(Long id, String username) {
        this.id = id;
        this.username = username;
    }

    /*
    create table Member (
            id bigint not null,
            age integer,
            createdDate timestamp,
            description clob,
            lastModifiedDate timestamp,
            roleType varchar(255),
    name varchar(255),
    primary key (id)
    )
    */
}
