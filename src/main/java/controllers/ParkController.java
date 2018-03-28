package controllers;
import static spark.Spark.post;

public class ParkController {

    public ParkController() {
        this.setupEndPoints();
    }

    private void setupEndPoints(){

        post("/park/enter", (req,res) ->{

        })
    }
}
