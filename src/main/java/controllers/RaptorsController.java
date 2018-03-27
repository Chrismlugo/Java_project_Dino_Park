package controllers;

import com.codeclan.db.DBHelper;
import models.Dino;
import models.Enums.SpeciesType;
import models.dinosaurs.Raptor;
import models.paddocks.Paddock;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;

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

        get ("/raptors/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String loggedInUser = LoginController.getLoggedInUserName(req, res);
            model.put("user", loggedInUser);
            SpeciesType species = SpeciesType.CARNIVORE;
            List<Paddock> paddocks = DBHelper.getAllPaddocksOfSpeciesType(SpeciesType.CARNIVORE);
            model.put("species", species);
            model.put("paddocks", paddocks);
            model.put("template", "templates/raptors/create.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        post ("/raptors", (req, res) -> {
            int paddockId = Integer.parseInt(req.queryParams("paddock"));
            SpeciesType species = SpeciesType.valueOf(req.queryParams("species"));
            Paddock paddock = DBHelper.find(Paddock.class, paddockId);
            String name = req.queryParams("Name");
            Raptor raptor = new Raptor(name,paddock);
            DBHelper.saveOrUpdate(raptor);
            res.redirect("/raptors");
            return null;
        }, new VelocityTemplateEngine());

        get("/raptors/:id", (req,res) ->{
            String strId = req.params(":id");
            Integer intId = Integer.parseInt(strId);
            Raptor raptor = DBHelper.find(Raptor.class,intId);

            Map<String,Object> model = new HashMap<>();
            String loggedInUser = LoginController.getLoggedInUserName(req, res);
            model.put("user", loggedInUser);
            model.put("raptor", raptor);
            model.put("template", "templates/raptors/show.vtl");

            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        get("/raptors/:id/edit", (req,res) ->{
            String strId = req.params(":id");
            Integer intId = Integer.parseInt(strId);
            Raptor raptor = DBHelper.find(Raptor.class,intId);

            Map<String,Object> model = new HashMap<>();
            String loggedInUser = LoginController.getLoggedInUserName(req, res);
            model.put("user", loggedInUser);

            List<Paddock> paddocks = DBHelper.getAllPaddocksOfSpeciesType(SpeciesType.CARNIVORE);
            List<SpeciesType> species = new ArrayList<>();
            SpeciesType carn = SpeciesType.CARNIVORE;
            species.add(carn);
            model.put("paddocks", paddocks);
            model.put("species", species);
            model.put("raptor", raptor);
            model.put("template", "templates/raptors/edit.vtl");

            return new ModelAndView(model,"templates/layout.vtl");
        }, new VelocityTemplateEngine());


            post("/raptors/:id", (req,res) ->{
                String strId = req.params(":id");
                Integer intId = Integer.parseInt(strId);
                Integer paddockId = Integer.parseInt(req.queryParams("paddock"));
                Paddock paddock = DBHelper.find(Paddock.class,paddockId);
                Raptor raptor = DBHelper.find(Raptor.class, intId);
                String name = req.queryParams("Name");
                SpeciesType species = SpeciesType.valueOf(req.queryParams("species"));
                raptor.setName(name);
                raptor.setSpecies(species);
                DBHelper.saveOrUpdate(raptor);
                res.redirect("/raptors");
                return null;

            }, new VelocityTemplateEngine());

        post ("/raptors/:id/delete", (req, res) -> {
            Integer id = Integer.parseInt(req.params(":id"));
            Raptor raptorToDelete = DBHelper.find( Raptor.class, id);
            DBHelper.delete(raptorToDelete);
            res.redirect("/raptors");
            return null;
        }, new VelocityTemplateEngine());

    }
}
