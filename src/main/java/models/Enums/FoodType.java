package models.Enums;

public enum FoodType {
    VEGETARIAN("Veggie"),
    PEPPERONI("pepperoni"),
    MEATYLICIOUS("meatylicious");

    private final String type;

    FoodType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
