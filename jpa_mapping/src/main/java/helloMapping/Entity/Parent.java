package helloMapping.Entity;

import helloMapping.Child;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Parent {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @OneToMany(mappedBy = "parent",
            orphanRemoval = true)
    private List<Child> childList = new ArrayList<>();

    public void addChild(Child child){
        this.childList.add(child);
        child.setParent(this);
    }
}
