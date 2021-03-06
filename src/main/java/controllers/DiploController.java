package controllers;

import com.codeclan.db.DBHelper;
import models.Enums.SpeciesType;
import models.Park;
import models.dinosaurs.Diplodocus;
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

public class DiploController {
    public DiploController() {
        this.setupEndPoints();
    }

    private void setupEndPoints(){
        get("/diplos", (req,res) ->{
            HashMap<String,Object> model = new HashMap<>();
            List<Park> parks = DBHelper.getAll(Park.class);
            Park parkFound = parks.remove(0);
            Park park = DBHelper.find(Park.class,parkFound.getId());
            model.put("park", park);
            String loggedInUser = LoginController.getLoggedInUserName(req,res);
            model.put("user", loggedInUser);
            List<Diplodocus> diplos = DBHelper.getAll(Diplodocus.class);
            model.put("diplos", diplos);
            model.put("template", "templates/diplos/index.vtl");

            return new ModelAndView(model,"templates/layout.vtl");
        }, new VelocityTemplateEngine());

        get ("/diplos/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Park> parks = DBHelper.getAll(Park.class);
            Park parkFound = parks.remove(0);
            Park park = DBHelper.find(Park.class,parkFound.getId());
            model.put("park", park);
            String loggedInUser = LoginController.getLoggedInUserName(req, res);
            model.put("user", loggedInUser);
            SpeciesType species = SpeciesType.HERBIVORE;
            List<Paddock> paddocks = DBHelper.getAllPaddocksOfSpeciesType(SpeciesType.HERBIVORE);
            model.put("species", species);
            model.put("paddocks", paddocks);
            model.put("template", "templates/diplos/create.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        post ("/diplos", (req, res) -> {
            int paddockId = Integer.parseInt(req.queryParams("paddock"));
            Paddock paddock = DBHelper.find(Paddock.class, paddockId);
            String name = req.queryParams("Name");
            Diplodocus diplo = new Diplodocus(name,paddock);
            DBHelper.saveOrUpdate(diplo);
            res.redirect("/diplos");
            return null;
        }, new VelocityTemplateEngine());

        get("/diplos/:id", (req,res) ->{
            String strId = req.params(":id");
            Integer intId = Integer.parseInt(strId);
            Diplodocus diplo = DBHelper.find(Diplodocus.class,intId);

            Map<String,Object> model = new HashMap<>();
            List<Park> parks = DBHelper.getAll(Park.class);
            Park parkFound = parks.remove(0);
            Park park = DBHelper.find(Park.class,parkFound.getId());
            model.put("park", park);
            String loggedInUser = LoginController.getLoggedInUserName(req, res);
            model.put("user", loggedInUser);
            model.put("diplo", diplo);
            model.put("template", "templates/diplos/show.vtl");

            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        get("/diplos/:id/edit", (req,res) ->{
            String strId = req.params(":id");
            Integer intId = Integer.parseInt(strId);
            Diplodocus diplo = DBHelper.find(Diplodocus.class,intId);

            Map<String,Object> model = new HashMap<>();
            List<Park> parks = DBHelper.getAll(Park.class);
            Park parkFound = parks.remove(0);
            Park park = DBHelper.find(Park.class,parkFound.getId());
            model.put("park", park);
            String loggedInUser = LoginController.getLoggedInUserName(req, res);
            model.put("user", loggedInUser);

            List<Paddock> paddocks = DBHelper.getAllPaddocksOfSpeciesType(SpeciesType.HERBIVORE);
            List<SpeciesType> species = new ArrayList<>();
            SpeciesType herb = SpeciesType.HERBIVORE;
            species.add(herb);
            model.put("paddocks", paddocks);
            model.put("species", species);
            model.put("diplo", diplo);
            model.put("template", "templates/diplos/edit.vtl");

            return new ModelAndView(model,"templates/layout.vtl");
        }, new VelocityTemplateEngine());


        post("/diplos/:id", (req,res) ->{
            String strId = req.params(":id");
            Integer intId = Integer.parseInt(strId);
            Integer paddockId = Integer.parseInt(req.queryParams("paddock"));
            Paddock paddock = DBHelper.find(Paddock.class,paddockId);
            Diplodocus diplo = DBHelper.find(Diplodocus.class, intId);
            String name = req.queryParams("Name");
            SpeciesType species = SpeciesType.valueOf(req.queryParams("species"));
            diplo.setName(name);
            diplo.setSpecies(species);
            DBHelper.saveOrUpdate(diplo);
            res.redirect("/diplos");
            return null;

        }, new VelocityTemplateEngine());

        post ("/diplos/:id/delete", (req, res) -> {
            Integer id = Integer.parseInt(req.params(":id"));
            Diplodocus diploToDelete = DBHelper.find( Diplodocus.class, id);
            DBHelper.delete(diploToDelete);
            res.redirect("/diplos");
            return null;
        }, new VelocityTemplateEngine());

        get("/diplos/transfer/:id", (req,res) ->{
            String strId = req.params(":id");
            Integer intId = Integer.parseInt(strId);
            Diplodocus diplo = DBHelper.find(Diplodocus.class,intId);

            List<Paddock> paddocks = DBHelper.getAllPaddocksOfSpeciesType(SpeciesType.HERBIVORE);
            List<SpeciesType> species = new ArrayList<>();
            SpeciesType herb = SpeciesType.HERBIVORE;
            species.add(herb);
            Map<String,Object> model = new HashMap<>();
            List<Park> parks = DBHelper.getAll(Park.class);
            Park parkFound = parks.remove(0);
            Park park = DBHelper.find(Park.class,parkFound.getId());
            model.put("park", park);
            String loggedInUser = LoginController.getLoggedInUserName(req, res);
            model.put("user", loggedInUser);
            model.put("diplo", diplo);
            model.put("species", species);
            model.put("paddocks", paddocks);
            model.put("template", "templates/diplos/transfer.vtl");
            return  new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        post("/diplos/transfer/:id", (req,res) ->{
            String strId = req.params(":id");
            Integer intId = Integer.parseInt(strId);
            Integer paddockId = Integer.parseInt(req.queryParams("paddock"));
            Paddock paddock = DBHelper.find(Paddock.class,paddockId);
            Diplodocus diplo = DBHelper.find(Diplodocus.class, intId);
            String name = req.queryParams("Name");
            SpeciesType species = SpeciesType.valueOf(req.queryParams("species"));
            diplo.setName(name);
            diplo.setSpecies(species);
            diplo.setPaddock(paddock);
            DBHelper.saveOrUpdate(diplo);
            res.redirect("/diplos");
            return null;
        }, new VelocityTemplateEngine());


    }


}
