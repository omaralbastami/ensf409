package edu.ucalgary.ensf409;

import java.sql.*;

public class Inventory{

    public final String user;
    public final String password;
    public final String url;

    private Connection dbConnect;

    public Inventory(){

    }

    public Inventory(String user, String password, String url){
        this.user = user;
        this.password = password;
        this.url = url;
    }

    public Chair[] getChair(){
        return 0;
    }

    public Desk[] getDesk(){
        return 0;
    }

    public Lamp[] getLamp(){
        return 0;
    }
    
    public Filing[] getFiling(){
        return 0;
    }

    public Manufacturer getManufacturer(String manuID){
        return 0;
    }

    public void deleteFurniture(String furnitureID, String category){
        
    }

    public Chair[] getCheapestCombination(Chair[] chairs, int quantity){
        return 0;
    }

    public Desk[] getCheapestCombination(Desk[] desks, int quantity){
        return 0;
    }

    public Lamp[] getCheapestCombination(Lamp[] lamps, int quantity){
        return 0;
    }

    public Filing[] getCheapestCombination(Filing[] filings, int quantity){
        return 0;
    }
 

}