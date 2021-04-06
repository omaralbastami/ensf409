package edu.ucalgary.ensf409;

public class Filing extends Furniture{
    private char rails;
    private char drawers;
    private char cabinet;


    public Filing(char rails, char drawers, char cabinet, String id, int price, Manufacturer manufacturer){
        super(id,"Filing",price,manufacturer);
        this.rails = rails;
        this.drawers = drawers;
        this.cabinet = cabinet;
    }

    public char getRails(){
        return this.rails;
    }

    public char getDrawers(){
        return this.drawers;
    }

    public char getCabinet(){
        return this.cabinet;
    }
    
}
