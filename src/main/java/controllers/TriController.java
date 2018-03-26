package controllers;

import com.codeclan.db.DBHelper;
import models.Enums.SpeciesType;
import models.dinosaurs.TRex;
import models.dinosaurs.Triceratops;
import models.paddocks.Paddock;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;

public class TriController {

    public TriController() {
        this.setupEndPoints();
    }

    private void setupEndPoints(){
        get("/triceratops", (req,res) ->{
            HashMap<String,Object> model = new HashMap<>();
            String loggedInUser = LoginController.getLoggedInUserName(req,res);
            model.put("user", loggedInUser);
            List<Triceratops> triceratops = DBHelper.getAll(Triceratops.class);
            model.put("triceratops", triceratops);
            model.put("template", "templates/triceratops/index.vtl");

            return new ModelAndView(model,"templates/layout.vtl");
        }, new VelocityTemplateEngine());

        get ("/triceratops/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String loggedInUser = LoginController.getLoggedInUserName(req, res);
            model.put("user", loggedInUser);
            SpeciesType species = SpeciesType.HERBIVORE;
            List<Paddock> paddocks = DBHelper.getAllPaddocksOfSpeciesType(SpeciesType.HERBIVORE);
            model.put("species", species);
            model.put("paddocks", paddocks);
            model.put("template", "templates/triceratops/create.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        post ("/triceratops", (req, res) -> {
            int paddockId = Integer.parseInt(req.queryParams("paddock"));
            SpeciesType species = SpeciesType.valueOf(req.queryParams("species"));
            Paddock paddock = DBHelper.find(Paddock.class, paddockId);
            String name = req.queryParams("Name");
            Triceratops tri = new Triceratops(name,species,paddock);
            DBHelper.saveOrUpdate(tri);
            res.redirect("/triceratops");
            return null;
        }, new VelocityTemplateEngine());

        get("/triceratops/:id", (req,res) ->{
            String strId = req.params(":id");
            Integer intId = Integer.parseInt(strId);
            Triceratops tri = DBHelper.find(Triceratops.class,intId);

            Map<String,Object> model = new HashMap<>();
            String loggedInUser = LoginController.getLoggedInUserName(req, res);
            model.put("user", loggedInUser);
            model.put("tri", tri);
            model.put("template", "templates/triceratops/show.vtl");

            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        get("/triceratops/:id/edit", (req,res) ->{
            String strId = req.params(":id");
            Integer intId = Integer.parseInt(strId);
            Triceratops tri = DBHelper.find(Triceratops.class,intId);

            Map<String,Object> model = new HashMap<>();
            String loggedInUser = LoginController.getLoggedInUserName(req, res);
            model.put("user", loggedInUser);

            List<Paddock> paddocks = DBHelper.getAllPaddocksOfSpeciesType(SpeciesType.HERBIVORE);
            List<SpeciesType> species = new ArrayList<>();
            SpeciesType carn = SpeciesType.CARNIVORE;
            SpeciesType herb = SpeciesType.HERBIVORE;
            species.add(carn);
            species.add(herb);
            model.put("paddocks", paddocks);
            model.put("species", species);
            model.put("tri", tri);
            model.put("template", "templates/triceratops/edit.vtl");

            return new ModelAndView(model,"templates/layout.vtl");
        }, new VelocityTemplateEngine());


        post("/triceratops/:id", (req,res) ->{
            String strId = req.params(":id");
            Integer intId = Integer.parseInt(strId);
            Integer paddockId = Integer.parseInt(req.queryParams("paddock"));
            Paddock paddock = DBHelper.find(Paddock.class,paddockId);
            Triceratops tri = DBHelper.find(Triceratops.class, intId);
            String name = req.queryParams("Name");
            SpeciesType species = SpeciesType.valueOf(req.queryParams("species"));
            tri.setName(name);
            tri.setSpecies(species);
            DBHelper.saveOrUpdate(tri);
            res.redirect("/triceratops");
            return null;

        }, new VelocityTemplateEngine());

        post ("/triceratops/:id/delete", (req, res) -> {
            Integer id = Integer.parseInt(req.params(":id"));
            Triceratops triToDelete = DBHelper.find( Triceratops.class, id);
            DBHelper.delete(triToDelete);
            res.redirect("/triceratops");
            return null;
        }, new VelocityTemplateEngine());

        get("/triceratops/transfer/:id", (req,res) ->{
            String strId = req.params(":id");
            Integer intId = Integer.parseInt(strId);
            Triceratops tri = DBHelper.find(Triceratops.class,intId);

            List<Paddock> paddocks = DBHelper.getAllPaddocksOfSpeciesType(SpeciesType.HERBIVORE);
            Map<String,Object> model = new HashMap<>();
            String loggedInUser = LoginController.getLoggedInUserName(req, res);
            model.put("user", loggedInUser);
            model.put("paddocks", paddocks);
            model.put("template", "templates/triceratops/transfer.vtl");
            return  new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());


    }
}
