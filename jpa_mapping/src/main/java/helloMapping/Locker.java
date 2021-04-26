package helloMapping;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Locker {

    @Id @GeneratedValue
    @Column(name = "LOCKER_ID")
    private Long id;

    private String number;

    @OneToOne(mappedBy = "locker")
    private Player player;
}
