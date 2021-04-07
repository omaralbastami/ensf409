package edu.ucalgary.ensf409;

public class Desk extends Furniture {
    private char legs;
    private char top;
    private char drawer;

    public Desk(char legs, char top, char drawer, String type, String id, int price, Manufacturer manufacturer) {
        super(id, type, price, manufacturer);
        this.legs = legs;
        this.top = top;
        this.drawer = drawer;
    }

    public char getLegs() {
        return this.legs;
    }

    public char getTop() {
        return this.top;
    }

    public char getDrawer() {
        return this.drawer;
    }

}
