package helloMapping.Entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn // Dtype 생성 엔티티 구분
public abstract class Item extends BaseEntity{

    @Id @GeneratedValue
    private Long id;

    private String name;
    private int price;
}
