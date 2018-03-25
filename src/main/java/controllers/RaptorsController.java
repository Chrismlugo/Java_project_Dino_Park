package controllers;

import com.codeclan.db.DBHelper;
import models.dinosaurs.Raptor;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.List;

import static spark.Spark.get;

public class RaptorsController {

    public RaptorsController() {
        this.setupEndPoints();
    }

    private void setupEndPoints(){
        get("/raptors", (req,res) ->{
            HashMap<String,Object> model = new HashMap<>();
            String loggedInUser = LoginController.getLoggedInUserName(req,res);
            model.put("user", loggedInUser);
            List<Raptor> raptors = DBHelper.getAll(Raptor.class);
            model.put("raptors", raptors);
            model.put("template", "templates/raptors/index.vtl");

            return new ModelAndView(model,"templates/layout.vtl");
        }, new VelocityTemplateEngine());
    }
}
