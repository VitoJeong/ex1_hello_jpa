package jpabook.jpashop.domain;

import lombok.Data;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@Data
public class OrderItem extends BaseEntity{

    @Id
    @GeneratedValue
    @Column(name = "ORDER_ITEM_ID")
    private long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "ORDER_ID")
    private Order order;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "ITEM_ID")
    private Item item;

    private Integer orderPrice;

    private Integer count;
}
