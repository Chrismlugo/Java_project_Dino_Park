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
    private int parkCapacity;

    public Park() {
    }

    public Park(String name) {
        this.name = name;
        this.rampagers = new ArrayList<>();
        this.paddocks = new ArrayList<>();
        this.visitors = 0;
        this.parkCapacity = 50;
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

    @Column(name="park_capacity")
    public int getParkCapacity() {
        return parkCapacity;
    }

    public void setParkCapacity(int park_capacity) {
        this.parkCapacity = park_capacity;
    }

    public List<Dino> Rampagers() {
        return rampagers;
    }

    public void setRampagers(List<Dino> rampagers) {
        this.rampagers = rampagers;
    }

    public void addVisitors(){
        if(this.visitors < parkCapacity) {
            Random ran = new Random();
            int randomNumber = ran.nextInt((15)+10);
            int added = randomNumber;
            this.visitors += added;
        }
    }

    public int countVisitors(){
      return this.visitors;
    }

    public boolean checkPaddocksAreSecure(){
        Boolean secure = true;
        for(Paddock paddock : paddocks){
            if(!paddock.isPaddockSecure()){
                secure = false;
            }
        }
        return secure;
    }

    public String parkSafetyStatus(){
        if(checkPaddocksAreSecure() == true){
            return "All Paddocks Secure. Park Safe to Open";
        }
        else{
            return "Warning! Paddock Breach Detected!";
        }
    }
}
