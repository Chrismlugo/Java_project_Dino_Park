package controllers;

import com.codeclan.db.DBHelper;
import models.Dino;
import models.Park;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.List;

import static spark.Spark.get;

public class DinosController {

    public DinosController() {
        this.setUpEndPoints();
    }

    private void setUpEndPoints() {
        get("/dinosaurs", (req, res) -> {
            HashMap<String, Object> model = new HashMap<>();
            List<Park> parks = DBHelper.getAll(Park.class);
            Park parkFound = parks.remove(0);
            Park park = DBHelper.find(Park.class,parkFound.getId());
            model.put("park", park);
            String loggedInUser = LoginController.getLoggedInUserName(req, res);
            model.put("user", loggedInUser);
            List<Dino> dinosaurs = DBHelper.getAll(Dino.class);
            model.put("dinosaurs", dinosaurs);
            model.put("template", "templates/dinosaurs/index.vtl");

            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

    }

}
