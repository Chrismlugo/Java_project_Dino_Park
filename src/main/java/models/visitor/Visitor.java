package models.visitor;

import models.Park;

import javax.persistence.*;

@Entity
@Table(name = "visitors")
public class Visitor {
   private int id;
   private Park park;
   private String name;

    public Visitor() {
    }

    public Visitor(String name) {
        this.name = name;
        this.park = park;
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

    @ManyToOne
    @JoinColumn(name = "park_id", nullable = false)
    public Park getPark() {
        return park;
    }

    public void setPark(Park park) {
        this.park = park;
    }
}
