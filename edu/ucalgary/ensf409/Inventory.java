package edu.ucalgary.ensf409;

import java.sql.*;

public class Inventory {

    public final String USER;
    public final String PASSWORD;
    public final String URL;
    private Connection dbConnect;

    public Inventory(String user, String password, String url) {
        this.USER = user;
        this.PASSWORD = password;
        this.URL = url;
    }

    public Chair[] getChair() {
        return null;
    }

    public Desk[] getDesk() {
        return null;
    }

    public Lamp[] getLamp() {
        return null;
    }

    public Filing[] getFiling() {
        return null;
    }

    public Manufacturer getManufacturer(String manuID) {
        return null;
    }

    public void deleteFurniture(String furnitureID, String category) {

    }

    public Chair[] getCheapestCombination(Chair[] chairs, int quantity) {
        return null;
    }

    public Desk[] getCheapestCombination(Desk[] desks, int quantity) {
        return null;
    }

    public Lamp[] getCheapestCombination(Lamp[] lamps, int quantity) {
        return null;
    }

    public Filing[] getCheapestCombination(Filing[] filings, int quantity) {
        return null;
    }

}