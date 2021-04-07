package edu.ucalgary.ensf409;

public abstract class Furniture {
    private String id;
    private String type;
    private int price;
    private Manufacturer manufacturer;

    public Furniture(String id, String type, int price, Manufacturer manufacturer) {
        this.id = id;
        this.type = type;
        this.price = price;
        this.manufacturer = manufacturer;
    }

    public String getId() {
        return this.id;
    }

    public int getPrice() {
        return this.price;
    }

    public String getType() {
        return this.type;
    }

    public Manufacturer getManufacturer() {
        return this.manufacturer;
    }

    public boolean pieceIsAvailable(char piece) {
        return piece == 'Y';
    }
}