package models.Visitor;

import javax.persistence.*;

@Entity
@Table(name = "visitors")
public class Visitor {
    int id;
    String name;

    public Visitor() {
    }

    public Visitor(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
