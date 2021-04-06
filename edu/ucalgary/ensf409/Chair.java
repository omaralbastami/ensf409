package edu.ucalgary.ensf409;

public class Chair extends Furniture {
    private char legs;
    private char arms;
    private char seat;
    private char cushion;

    public Chair(char legs, char arms, char seat, char cushion, String id, int price, Manufacturer manufacturer) {
        super(id, "Chair", price, manufacturer);
        this.legs = legs;
        this.arms = arms;
        this.seat = seat;
        this.cushion = cushion;
    }

    public char getLegs() {
        return this.legs;
    }

    public char getArms() {
        return this.arms;
    }

    public char getSeat() {
        return this.seat;
    }

    public char getCushion() {
        return this.cushion;
    }

}
