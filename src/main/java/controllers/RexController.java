package controllers;

import com.codeclan.db.DBHelper;
import models.Enums.SpeciesType;
import models.dinosaurs.Raptor;
import models.dinosaurs.TRex;
import models.paddocks.Paddock;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;

public class RexController {

    public RexController() {
        this.setupEndPoints();
    }

    private void setupEndPoints(){
        get("/rexes", (req,res) ->{
            HashMap<String,Object> model = new HashMap<>();
            String loggedInUser = LoginController.getLoggedInUserName(req,res);
            model.put("user", loggedInUser);
            List<TRex> rexes = DBHelper.getAll(TRex.class);
            model.put("rexes", rexes);
            model.put("template", "templates/rexes/index.vtl");

            return new ModelAndView(model,"templates/layout.vtl");
        }, new VelocityTemplateEngine());

        get ("/rexes/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String loggedInUser = LoginController.getLoggedInUserName(req, res);
            model.put("user", loggedInUser);
            SpeciesType species = SpeciesType.CARNIVORE;
            List<Paddock> paddocks = DBHelper.getAllPaddocksOfSpeciesType(SpeciesType.CARNIVORE);
            model.put("species", species);
            model.put("paddocks", paddocks);
            model.put("template", "templates/rexes/create.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        post ("/rexes", (req, res) -> {
            int paddockId = Integer.parseInt(req.queryParams("paddock"));
            SpeciesType species = SpeciesType.valueOf(req.queryParams("species"));
            Paddock paddock = DBHelper.find(Paddock.class, paddockId);
            String name = req.queryParams("Name");
            TRex rex = new TRex(name,species,paddock);
            DBHelper.saveOrUpdate(rex);
            res.redirect("/rexes");
            return null;
        }, new VelocityTemplateEngine());

        get("/rexes/:id", (req,res) ->{
            String strId = req.params(":id");
            Integer intId = Integer.parseInt(strId);
            TRex rex = DBHelper.find(TRex.class,intId);

            Map<String,Object> model = new HashMap<>();
            String loggedInUser = LoginController.getLoggedInUserName(req, res);
            model.put("user", loggedInUser);
            model.put("rex", rex);
            model.put("template", "templates/rexes/show.vtl");

            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        get("/rexes/:id/edit", (req,res) ->{
            String strId = req.params(":id");
            Integer intId = Integer.parseInt(strId);
            TRex rex = DBHelper.find(TRex.class,intId);

            Map<String,Object> model = new HashMap<>();
            String loggedInUser = LoginController.getLoggedInUserName(req, res);
            model.put("user", loggedInUser);

            List<Paddock> paddocks = DBHelper.getAllPaddocksOfSpeciesType(SpeciesType.CARNIVORE);
            List<SpeciesType> species = new ArrayList<>();
            SpeciesType carn = SpeciesType.CARNIVORE;
            species.add(carn);
            model.put("paddocks", paddocks);
            model.put("species", species);
            model.put("rex", rex);
            model.put("template", "templates/rexes/edit.vtl");

            return new ModelAndView(model,"templates/layout.vtl");
        }, new VelocityTemplateEngine());



        post("/rexes/:id", (req,res) ->{
            String strId = req.params(":id");
            Integer intId = Integer.parseInt(strId);
            Integer paddockId = Integer.parseInt(req.queryParams("paddock"));
            Paddock paddock = DBHelper.find(Paddock.class,paddockId);
            TRex rex = DBHelper.find(TRex.class, intId);
            String name = req.queryParams("Name");
            SpeciesType species = SpeciesType.valueOf(req.queryParams("species"));
            rex.setName(name);
            rex.setSpecies(species);
            DBHelper.saveOrUpdate(rex);
            res.redirect("/rexes");
            return null;

        }, new VelocityTemplateEngine());

        post ("/rexes/:id/delete", (req, res) -> {
            Integer id = Integer.parseInt(req.params(":id"));
            TRex rexToDelete = DBHelper.find( TRex.class, id);
            DBHelper.delete(rexToDelete);
            res.redirect("/rexes");
            return null;
        }, new VelocityTemplateEngine());

    }
}
