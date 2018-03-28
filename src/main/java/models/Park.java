package models;

import models.paddocks.Paddock;
import models.visitor.Visitor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name= "parks")
public class Park {
    private String name;
    private int id;
    private List<Dino> rampagers;
    private List<Paddock> paddocks;
    private int visitors;

    public Park() {
    }

    public Park(String name) {
        this.name = name;
        this.rampagers = new ArrayList<>();
        this.paddocks = new ArrayList<>();
        this.visitors = 0;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public List<Dino> Rampagers() {
        return rampagers;
    }

    public void addVisitor(Visitor visitor){
        visitors.add(visitor);
    }

    public void setRampagers(List<Dino> rampagers) {
        this.rampagers = rampagers;
    }

    @OneToMany(mappedBy = "park")
    @LazyCollection(LazyCollectionOption.FALSE)
    public List<Paddock> getPaddocks() {
        return paddocks;
    }

    public void setPaddocks(List<Paddock> paddocks) {
        this.paddocks = paddocks;
    }

    @Column(name = "visitors")
    public int getVisitors() {
        return visitors;
    }

    public void setVisitors(int visitors) {
        this.visitors = visitors;
    };

    public int countVisitors(){
      return this.visitors;
    }

    public String checkPaddocksAreSecure(){
        for(Paddock paddock : paddocks){
            if(paddock.isPaddockSecure() == true){
                return "SAFE: PARK OPEN";
            }
            else {

                return "WARNING " + paddock.getName() + " is Not Secure";
            }
        }
        return null;
    }




}
