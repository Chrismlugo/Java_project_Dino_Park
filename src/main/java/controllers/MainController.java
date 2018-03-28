package controllers;
import com.codeclan.db.DBHelper;
import com.codeclan.db.Seeds;
import models.Park;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.before;
import static spark.Spark.get;
import static spark.Spark.halt;
import static spark.SparkBase.port;
import static spark.SparkBase.staticFileLocation;

public class MainController {

    public static void main(String[] args) {
        Seeds.SeedData();
        staticFileLocation("/public");

        LoginController loginController = new LoginController();
        DinosController dinosController = new DinosController();
        RaptorsController raptorsController = new RaptorsController();
        PaddocksController paddocksController = new PaddocksController();
        RexController rexController = new RexController();
        DiploController diploController = new DiploController();
        TriController triController = new TriController();
        RampageController rampageController = new RampageController();
        ParkController parkController = new ParkController();


        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Park> parks = DBHelper.getAll(Park.class);
            Park parkFound = parks.remove(0);
            Park park = DBHelper.find(Park.class,parkFound.getId());
            String loggedInUser = LoginController.getLoggedInUserName(req, res);
            model.put("park", park);
            model.put("user", loggedInUser);
            model.put("template","templates/main.vtl");

            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

    }
}
