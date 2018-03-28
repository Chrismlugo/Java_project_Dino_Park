package controllers;
import com.codeclan.db.DBHelper;
import models.Park;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.post;

public class ParkController {

    public ParkController() {
        this.setupEndPoints();
    }

    private void setupEndPoints(){

        post("/park/enter", (req,res) ->{
            Map<String,Object> model = new HashMap<>();
            List<Park> parks = DBHelper.getAll(Park.class);
            Park found = parks.remove(0);
            Park park = DBHelper.find(Park.class, found.getId());
            park.addVisitors();
            DBHelper.saveOrUpdate(park);
            res.redirect("/");
            return  null;
        }, new VelocityTemplateEngine());
    }
}
