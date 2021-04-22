package jpabook.jpashop.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class OrderItem {

    @Id
    @GeneratedValue
    @Column(name = "ORDER_ITEM_ID")
    private long id;

    @ManyToOne
    @JoinColumn(name = "ORDER_ID")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "ITEM_ID")
    private Item item;

    private Integer orderPrice;

    private Integer count;
}
