package controllers;

import com.codeclan.db.DBHelper;
import models.Dino;
import models.Enums.SpeciesType;
import models.dinosaurs.Raptor;
import models.paddocks.Paddock;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;

public class PaddocksController {

    public PaddocksController() {
        this.setupEndPoints();
    }

    private void setupEndPoints() {
        get("/paddocks", (req, res) -> {
            HashMap<String, Object> model = new HashMap<>();
            String loggedInUser = LoginController.getLoggedInUserName(req, res);
            model.put("user", loggedInUser);
            List<Paddock> paddocks = DBHelper.getAll(Paddock.class);
            model.put("paddocks", paddocks);
            model.put("template", "templates/paddocks/index.vtl");

            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        get ("/paddocks/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String loggedInUser = LoginController.getLoggedInUserName(req, res);
            model.put("user", loggedInUser);
            List<SpeciesType> species = new ArrayList<>();
            SpeciesType carn = SpeciesType.CARNIVORE;
            SpeciesType herb = SpeciesType.HERBIVORE;
            species.add(carn);
            species.add(herb);
            model.put("species", species);
            model.put("template", "templates/paddocks/create.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        get("/paddocks/:id/restock", (req,res) ->{
            String strId = req.params(":id");
            Integer intId = Integer.parseInt(strId);
            Paddock paddock = DBHelper.find(Paddock.class,intId);

            Map<String,Object> model = new HashMap<>();
            String loggedInUser = LoginController.getLoggedInUserName(req, res);
            model.put("user", loggedInUser);
            model.put("paddock", paddock);
            model.put("template", "templates/paddocks/restock.vtl");

            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        post("/paddocks/restock/:id", (req,res) ->{
            String strId = req.params(":id");
            Integer intId = Integer.parseInt(strId);
            Paddock paddock = DBHelper.find(Paddock.class,intId);
            paddock.stockPaddock(10);
            DBHelper.saveOrUpdate(paddock);
            paddock.feedDinos();
            DBHelper.saveOrUpdate(paddock);
            res.redirect("/paddocks");
            return null;

        }, new VelocityTemplateEngine());

        post ("/paddocks", (req, res) -> {
            SpeciesType species = SpeciesType.valueOf(req.queryParams("species"));
            String name = req.queryParams("Name");
            Paddock paddock = new Paddock(name,species);
            DBHelper.saveOrUpdate(paddock);
            res.redirect("/paddocks");
            return null;
        }, new VelocityTemplateEngine());

        get("/paddocks/:id", (req,res) ->{
            String strId = req.params(":id");
            Integer intId = Integer.parseInt(strId);
            Paddock paddock = DBHelper.find(Paddock.class,intId);
            List<Dino> dinosaurs = DBHelper.getDinosOfPaddock(paddock);

            Map<String,Object> model = new HashMap<>();
            String loggedInUser = LoginController.getLoggedInUserName(req, res);
            model.put("user", loggedInUser);
            model.put("paddock", paddock);
            model.put("dinosaurs", dinosaurs);
            model.put("template", "templates/paddocks/show.vtl");

            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        get("/paddocks/:id/edit", (req,res) ->{
            String strId = req.params(":id");
            Integer intId = Integer.parseInt(strId);
            Paddock paddock = DBHelper.find(Paddock.class,intId);

            Map<String,Object> model = new HashMap<>();
            String loggedInUser = LoginController.getLoggedInUserName(req, res);
            model.put("user", loggedInUser);

            List<SpeciesType> species = new ArrayList<>();
            SpeciesType carn = SpeciesType.CARNIVORE;
            SpeciesType herb = SpeciesType.HERBIVORE;
            species.add(carn);
            species.add(herb);
            model.put("species", species);
            model.put("paddock", paddock);
            model.put("template", "templates/paddocks/edit.vtl");

            return new ModelAndView(model,"templates/layout.vtl");
        }, new VelocityTemplateEngine());

        post ("/paddocks/:id/delete", (req, res) -> {
            Integer id = Integer.parseInt(req.params(":id"));
            Paddock paddockToDelete = DBHelper.find( Paddock.class, id);
            DBHelper.delete(paddockToDelete);
            res.redirect("/paddocks");
            return null;
        }, new VelocityTemplateEngine());



        post("/paddocks/:id", (req,res) ->{
            String strId = req.params(":id");
            Integer intId = Integer.parseInt(strId);
            Paddock paddock = DBHelper.find(Paddock.class, intId);
            String name = req.queryParams("Name");
            SpeciesType species = SpeciesType.valueOf(req.queryParams("species"));
            paddock.setName(name);
            paddock.setSpecies(species);
            DBHelper.saveOrUpdate(paddock);
            res.redirect("/paddocks");
            return null;

        }, new VelocityTemplateEngine());



    }
}
