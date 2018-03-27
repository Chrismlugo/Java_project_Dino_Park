package models.Enums;

public enum StomachSize {
    LARGE(5),
    SMALL(2);

    private final int size;

    StomachSize(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }
}
