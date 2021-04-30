package helloMapping.Entity;

import helloMapping.Entity.Item;
import lombok.Data;

import javax.persistence.Entity;

@Entity
@Data
public class Movie extends Item {

    private String director;
    private String actor;
}
