package controllers;

import com.codeclan.db.DBHelper;
import models.Enums.SpeciesType;
import models.dinosaurs.Raptor;
import models.dinosaurs.TRex;
import models.paddocks.Paddock;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.get;

public class RampageController {

    public RampageController() {
    this.setupEndPoints();
    }

    private void setupEndPoints() {
        get("/rampage", (req,res) ->{
            HashMap<String,Object> model = new HashMap<>();
            String loggedInUser = LoginController.getLoggedInUserName(req,res);
            model.put("user", loggedInUser);

            model.put("template", "templates/rampage.vtl");

            return new ModelAndView(model,"templates/layout.vtl");
        }, new VelocityTemplateEngine());
    }


}
