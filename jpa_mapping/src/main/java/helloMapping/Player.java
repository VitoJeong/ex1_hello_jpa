package helloMapping;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Player {

    @Id @GeneratedValue
    private Long id;

    private String name;

    @OneToOne
    @JoinColumn(name = "LOCKER_ID")
    private Locker locker;
}
