package models.Enums;

public enum SpeciesType {
    CARNIVORE("Carnivore"),
    HERBIVORE("Herbivore");

    private final String species;

    SpeciesType(String species) {
        this.species = species;
    }


    public String getSpecies() {
        return species;
    }
}
