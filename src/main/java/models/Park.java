package models;

import models.paddocks.Paddock;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

    public void addVisitors(){
        Random ran = new Random();
        int randomNumber = ran.nextInt((10 - 5) + 5);
        int visitors = getVisitors();
        int added = visitors + randomNumber;
        this.visitors += added;

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

    public boolean checkPaddocksAreSecure(){
        Boolean secure = true;
        for(Paddock paddock : paddocks){
            if(!paddock.isPaddockSecure()){
                secure = false;
            };
        }
        return secure;
    }




}
