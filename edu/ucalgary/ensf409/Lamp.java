package edu.ucalgary.ensf409;

public class Lamp extends Furniture {
    private char base;
    private char bulb;

    public Lamp(char base, char bulb, String id, int price, Manufacturer manufacturer) {
        super(id, "Lamp", price, manufacturer);
        this.base = base;
        this.bulb = bulb;
    }

    public char getBase() {
        return this.base;
    }

    public char getBulb() {
        return this.bulb;
    }

}
