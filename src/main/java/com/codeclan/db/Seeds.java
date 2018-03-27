package com.codeclan.db;

import models.Dino;
import models.Enums.SpeciesType;
import models.dinosaurs.Diplodocus;
import models.dinosaurs.Raptor;
import models.dinosaurs.TRex;
import models.dinosaurs.Triceratops;
import models.paddocks.Paddock;

import java.util.List;

public class Seeds {
    public static void SeedData() {
        DBHelper.deleteAll(Diplodocus.class);
        DBHelper.deleteAll(Raptor.class);
        DBHelper.deleteAll(TRex.class);
        DBHelper.deleteAll(Triceratops.class);
        DBHelper.deleteAll(Paddock.class);

        Paddock paddock1 = new Paddock("Herby's Green Patch", SpeciesType.HERBIVORE);
        DBHelper.saveOrUpdate(paddock1);
        Paddock paddock9 = new Paddock("OPEN PADDOCK 9", SpeciesType.CARNIVORE);
        DBHelper.saveOrUpdate(paddock9);
        Paddock paddock3 = new Paddock("Here be Raptors", SpeciesType.CARNIVORE);
        DBHelper.saveOrUpdate(paddock3);

        Paddock foundPaddock = DBHelper.find(Paddock.class, paddock1.getId());

        Diplodocus dippy1 = new Diplodocus("LittleFoot", paddock1);
        DBHelper.saveOrUpdate(dippy1);
        Triceratops tri1 = new Triceratops("Cera", paddock1);
        DBHelper.saveOrUpdate(tri1);
        Triceratops tri2 = new Triceratops("Woog",paddock1);
        DBHelper.saveOrUpdate(tri2);
        TRex bigT1 = new TRex("Rex",paddock9);
        DBHelper.saveOrUpdate(bigT1);
        Raptor raptor1 = new Raptor("Blue",paddock3);
        DBHelper.saveOrUpdate(raptor1);
        Raptor raptor2 = new Raptor("Charlie",paddock3);
        DBHelper.saveOrUpdate(raptor2);
        Raptor raptor3 = new Raptor("Delta", paddock3);
        DBHelper.saveOrUpdate(raptor3);
        Raptor raptor4 = new Raptor("Echo",paddock3);
        DBHelper.saveOrUpdate(raptor4);


    }
}
