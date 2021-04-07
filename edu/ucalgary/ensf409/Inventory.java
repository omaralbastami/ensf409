package edu.ucalgary.ensf409;

import java.sql.*;
import java.util.*;

/**
 * The Inventory is responsible for interfacing with the MySQL database to
 * retrieve relevant inventory information
 */
public class Inventory {

    private final String URL = "jdbc:mysql://localhost/inventory";
    private final String USER = "finalProject";
    private final String PASSWORD = "Password#1234";
    private Connection dbConnect;

    /**
     * Initialize connection with inventory database
     */
    public void initializeConnection() {
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            this.dbConnect = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.err.println("Unable to connect to inventory");
        }
    }

    /**
     * Retrieve chairs of specified type
     * 
     * @param type type of chair
     * @return chairs with associated type
     */
    public Chair[] getChairs(String type) throws SQLException {
        PreparedStatement myStmt = dbConnect.prepareStatement("SELECT * FROM chair WHERE Type = ?");
        try {
            List<Chair> result = new ArrayList<>();

            myStmt.setString(1, type);
            ResultSet queryResults = myStmt.executeQuery();

            while (queryResults.next()) {
                String id = queryResults.getString("ID");
                char legs = queryResults.getString("Legs").charAt(0);
                char arms = queryResults.getString("Arms").charAt(0);
                char seat = queryResults.getString("Seat").charAt(0);
                char cushion = queryResults.getString("Cushion").charAt(0);
                int price = queryResults.getInt("Price");
                String manuID = queryResults.getString("ManuID");
                Manufacturer manufacturer = getManufacturer(manuID);

                Chair chair = new Chair(legs, arms, seat, cushion, type, id, price, manufacturer);
                result.add(chair);
            }

            return result.toArray(new Chair[0]);

        } finally {
            myStmt.close();
        }
    }

    /**
     * Retrieve desks of specified type
     * 
     * @param type type of desk
     * @return desks with associated type
     */
    public Desk[] getDesks(String type) throws SQLException {
        PreparedStatement myStmt = dbConnect.prepareStatement("SELECT * FROM desk WHERE Type = ?");
        try {
            List<Desk> result = new ArrayList<>();

            myStmt.setString(1, type);
            ResultSet queryResults = myStmt.executeQuery();

            while (queryResults.next()) {
                String id = queryResults.getString("ID");
                char legs = queryResults.getString("Legs").charAt(0);
                char top = queryResults.getString("Top").charAt(0);
                char drawer = queryResults.getString("Drawer").charAt(0);
                int price = queryResults.getInt("Price");
                String manuID = queryResults.getString("ManuID");
                Manufacturer manufacturer = getManufacturer(manuID);

                Desk desk = new Desk(legs, top, drawer, type, id, price, manufacturer);
                result.add(desk);
            }

            return result.toArray(new Desk[0]);

        } finally {
            myStmt.close();
        }
    }

    /**
     * Retrieve lamps of specified type
     * 
     * @param type type of lamp
     * @return lamps with associated type
     */
    public Lamp[] getLamps(String type) throws SQLException {
        PreparedStatement myStmt = dbConnect.prepareStatement("SELECT * FROM lamp WHERE Type = ?");
        try {
            List<Lamp> result = new ArrayList<>();

            myStmt.setString(1, type);
            ResultSet queryResults = myStmt.executeQuery();

            while (queryResults.next()) {
                String id = queryResults.getString("ID");
                char base = queryResults.getString("Base").charAt(0);
                char bulb = queryResults.getString("Bulb").charAt(0);
                int price = queryResults.getInt("Price");
                String manuID = queryResults.getString("ManuID");
                Manufacturer manufacturer = getManufacturer(manuID);

                Lamp lamp = new Lamp(base, bulb, type, id, price, manufacturer);
                result.add(lamp);
            }

            return result.toArray(new Lamp[0]);

        } finally {
            myStmt.close();
        }
    }

    /**
     * Retrieve filings of specified type
     * 
     * @param type type of filing
     * @return filings with associated type
     */
    public Filing[] getFilings(String type) throws SQLException {
        PreparedStatement myStmt = dbConnect.prepareStatement("SELECT * FROM lamp WHERE Type = ?");
        try {
            List<Filing> result = new ArrayList<>();

            myStmt.setString(1, type);
            ResultSet queryResults = myStmt.executeQuery();

            while (queryResults.next()) {
                String id = queryResults.getString("ID");
                char rails = queryResults.getString("Rails").charAt(0);
                char drawers = queryResults.getString("Drawers").charAt(0);
                char cabinet = queryResults.getString("Cabinet").charAt(0);
                int price = queryResults.getInt("Price");
                String manuID = queryResults.getString("ManuID");
                Manufacturer manufacturer = getManufacturer(manuID);

                Filing filing = new Filing(rails, drawers, cabinet, type, id, price, manufacturer);
                result.add(filing);
            }

            return result.toArray(new Filing[0]);

        } finally {
            myStmt.close();
        }
    }

    /**
     * Retrieve desk manufacturers
     * 
     * @return array of desk manufacturers
     */
    public Manufacturer[] getDeskManufacturers() throws SQLException {
        return getManufacturersFromTable("desk");
    }

    /**
     * Retrieve chair manufacturers
     * 
     * @return array of chair manufacturers
     */
    public Manufacturer[] getChairManufacturers() throws SQLException {
        return getManufacturersFromTable("chair");
    }

    /**
     * Retrieve lamp manufacturers
     * 
     * @return array of lamp manufacturers
     */
    public Manufacturer[] getLampManufacturers() throws SQLException {
        return getManufacturersFromTable("lamp");
    }

    /**
     * Retrieve filing manufacturers
     * 
     * @return array of filing manufacturers
     */
    public Manufacturer[] getFilingManufacturers() throws SQLException {
        return getManufacturersFromTable("filing");
    }

    /**
     * Helper function to retrieve manufacturers from specified table
     * 
     * @param table name of table
     * @return array of manufacturers
     */
    private Manufacturer[] getManufacturersFromTable(String table) throws SQLException {
        Statement myStmt = dbConnect.createStatement();
        try {
            List<Manufacturer> result = new ArrayList<>();
            ResultSet queryResults = myStmt.executeQuery("SELECT ManuID FROM " + table + " GROUP BY(ManuID);");

            while (queryResults.next()) {
                String manuID = queryResults.getString("ManuID");
                Manufacturer manufacturer = getManufacturer(manuID);
                result.add(manufacturer);
            }

            return result.toArray(new Manufacturer[0]);

        } finally {
            myStmt.close();
        }
    }

    /**
     * Retrieve manufacturer information given the ID
     * 
     * @param manuID ID of manufacturer
     * @return manufacturer
     */
    public Manufacturer getManufacturer(String manuID) throws SQLException {
        PreparedStatement myStmt = dbConnect.prepareStatement("SELECT * FROM lamp WHERE ManuID = ?");
        try {
            myStmt.setString(1, manuID);
            ResultSet queryResults = myStmt.executeQuery();
            Manufacturer manufacturer = null;

            while (queryResults.next()) {
                String name = queryResults.getString("Name");
                String phone = queryResults.getString("Phone");
                String province = queryResults.getString("Province");
                manufacturer = new Manufacturer(manuID, name, phone, province);
            }

            return manufacturer;

        } finally {
            myStmt.close();
        }
    }

    public void deleteFurniture(String furnitureID, String category) throws SQLException {

    }

    /**
     * Get cheapest chair combination given quantity
     * 
     * @param chairs   chairs available
     * @param quantity number of chairs needed
     * @return chairs that produce the cheapest combination. Empty array if cannot
     *         be produced
     */
    public Chair[] getCheapestCombination(Chair[] chairs, int quantity) {
        return null;
    }

    /**
     * Get cheapest desk combination given quantity
     * 
     * @param chairs desks available
     * @param desks  number of chairs needed
     * @return desks that produce the cheapest combination. Empty if cannot be
     *         produced
     */
    public Desk[] getCheapestCombination(Desk[] desks, int quantity) {
        return null;
    }

    /**
     * Get cheapest lamp combination given quantity. Empty if cannot be produced
     * 
     * @param lamps    lamps available
     * @param quantity number of chairs needed
     * @return lamps that produce the cheapest combination
     */
    public Lamp[] getCheapestCombination(Lamp[] lamps, int quantity) {
        return null;
    }

    /**
     * Get cheapest filing combination given quantity. Empty if cannot be produced
     * 
     * @param filings  filings available
     * @param quantity number of chairs needed
     * @return filings that produce the cheapest combination
     */
    public Filing[] getCheapestCombination(Filing[] filings, int quantity) {
        return null;
    }

}