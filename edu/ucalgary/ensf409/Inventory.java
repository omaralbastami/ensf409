package edu.ucalgary.ensf409;

import java.sql.*;
import java.util.*;

/**
 * The Inventory is responsible for interfacing with the MySQL database to
 * retrieve relevant inventory information
 */
public class Inventory {

    private final String URL = "jdbc:mysql://localhost/inventory";
    private final String USER = "scm";
    private final String PASSWORD = "ensf409";
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
        PreparedStatement myStmt = dbConnect.prepareStatement("SELECT * FROM filing WHERE Type = ?");
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
        PreparedStatement myStmt = dbConnect.prepareStatement("SELECT * FROM manufacturer WHERE ManuID = ?");
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

    /**
     * Delete furniture with given ID from the furniture of specified category
     * 
     * @param furnitureID ID to furniture to delete
     * @param category    category of furniture
     */
    private void deleteFurniture(String furnitureID, String category) throws SQLException {
        PreparedStatement myStmt = dbConnect.prepareStatement("DELETE FROM " + category + " WHERE ID = ?");
        try {
            myStmt.setString(1, furnitureID);
            myStmt.executeUpdate();

        } finally {
            myStmt.close();
        }
    }

    /**
     * Get cheapest chair combination given quantity
     * 
     * @param chairs   chairs available
     * @param quantity number of chairs needed
     * @return chairs that produce the cheapest combination. Empty array if cannot
     *         be produced
     */
    public Chair[] getCheapestCombination(Chair[] chairs, int quantity) throws SQLException {
        if (chairs.length < quantity) {
            return new Chair[0];
        }

        List<HashSet<Chair>> chairTable = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            chairTable.add(new HashSet<Chair>());
        }

        for (Chair chair : chairs) {
            addChairToTable(chairTable, chair);
        }

        Chair[] cheapest = getCheapestCombinationFromTable(chairTable, quantity).toArray(new Chair[0]);

        for (Chair chair : cheapest) {
            deleteFurniture(chair.getId(), "CHAIR");
        }

        return cheapest;
    }

    /**
     * Get cheapest desk combination given quantity
     * 
     * @param desks    desks available
     * @param quantity number of desks needed
     * @return desks that produce the cheapest combination. Empty if cannot be
     *         produced
     */
    public Desk[] getCheapestCombination(Desk[] desks, int quantity) throws SQLException {
        if (desks.length < quantity) {
            return new Desk[0];
        }

        List<HashSet<Desk>> deskTable = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            deskTable.add(new HashSet<Desk>());
        }

        for (Desk desk : desks) {
            addDeskToTable(deskTable, desk);
        }

        Desk[] cheapest = getCheapestCombinationFromTable(deskTable, quantity).toArray(new Desk[0]);

        for (Desk desk : cheapest) {
            deleteFurniture(desk.getId(), "DESK");
        }

        return cheapest;
    }

    /**
     * Get cheapest lamp combination given quantity. Empty if cannot be produced
     * 
     * @param lamps    lamps available
     * @param quantity number of lamps needed
     * @return lamps that produce the cheapest combination
     */
    public Lamp[] getCheapestCombination(Lamp[] lamps, int quantity) throws SQLException {
        if (lamps.length < quantity) {
            return new Lamp[0];
        }

        List<HashSet<Lamp>> lampTable = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            lampTable.add(new HashSet<Lamp>());
        }

        for (Lamp lamp : lamps) {
            addLampToTable(lampTable, lamp);
        }

        Lamp[] cheapest = getCheapestCombinationFromTable(lampTable, quantity).toArray(new Lamp[0]);

        for (Lamp lamp : cheapest) {
            deleteFurniture(lamp.getId(), "LAMP");
        }

        return cheapest;
    }

    /**
     * Get cheapest filing combination given quantity. Empty if cannot be produced
     * 
     * @param filings  filings available
     * @param quantity number of filings needed
     * @return filings that produce the cheapest combination
     */
    public Filing[] getCheapestCombination(Filing[] filings, int quantity) throws SQLException {
        if (filings.length < quantity) {
            return new Filing[0];
        }

        List<HashSet<Filing>> filingTable = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            filingTable.add(new HashSet<Filing>());
        }

        for (Filing filing : filings) {
            addFilingToTable(filingTable, filing);
        }

        Filing[] cheapest = getCheapestCombinationFromTable(filingTable, quantity).toArray(new Filing[0]);

        for (Filing filing : cheapest) {
            deleteFurniture(filing.getId(), "FILING");
        }

        return cheapest;
    }

    /**
     * Will attempt to gather parts that will satisfy the quantity, then will
     * iterate through these parts to get the cheapest possible combinations
     * 
     * @param <T>        class extending furniture
     * @param pieceTable the metatable of pieces created by getCheapestCombination
     * @param quantity   quantity requested
     * @return collection of the pieces to purchase, or empty if cannot be fulfilled
     */
    private <T extends Furniture> HashSet<T> getCheapestCombinationFromTable(List<HashSet<T>> pieceTable,
            int quantity) {
        List<List<T>> possibleCombinations = new ArrayList<>();

        getCheapestCombinationUtil(pieceTable, possibleCombinations, quantity, new Stack<T>());

        if (possibleCombinations.size() == 0)
            return new HashSet<T>();

        int minPrice = Integer.MAX_VALUE;
        List<T> result = null;

        for (List<T> combination : possibleCombinations) {
            int currentTotal = getTotalPrice(combination);
            if (currentTotal < minPrice) {
                result = combination;
                minPrice = currentTotal;
            }
        }

        return new HashSet<T>(result);
    }

    /**
     * Calculates total price for specified furniture. Ignores duplicates.
     * 
     * @param <T>        class extending furniture
     * @param furnitures furniture to calculate total price for
     * @return total price
     */
    private <T extends Furniture> int getTotalPrice(List<T> furnitures) {
        HashSet<T> seen = new HashSet<>();
        int price = 0;

        for (T furniture : furnitures) {
            if (seen.add(furniture))
                price += furniture.getPrice();
        }

        return price;
    }

    /**
     * Helper method to get cheapest combination
     * 
     * @param <T>          class extending furniture
     * @param pieceTable   the metatable of pieces created by getCheapestCombination
     * @param combinations list that will store all the possible combinations
     * @param quantity     quantity requested
     * @param current      helper stack to track current combination
     */
    private <T extends Furniture> void getCheapestCombinationUtil(List<HashSet<T>> pieceTable,
            List<List<T>> combinations, int quantity, Stack<T> current) {
        if (quantity == 0) {
            combinations.add(new ArrayList<T>(current));
            return;
        }

        List<List<T>> currentCombinations = getAllCombinationsFromTable(pieceTable);

        for (List<T> combination : currentCombinations) {
            for (int i = 0; i < combination.size(); i++) {
                current.add(combination.get(i));
                pieceTable.get(i).remove(combination.get(i));
            }

            getCheapestCombinationUtil(pieceTable, combinations, quantity - 1, current);

            for (int i = 0; i < combination.size(); i++) {
                current.pop();
                pieceTable.get(i).add(combination.get(i));
            }
        }
    }

    /**
     * Get all possible combinations of pieces, regardless of quantity
     * 
     * @param <T>        class extending furniture
     * @param pieceTable the metatable of pieces created by getCheapestCombination
     * @return list of all combinations
     */
    private <T extends Furniture> List<List<T>> getAllCombinationsFromTable(List<HashSet<T>> pieceTable) {
        List<List<T>> combinations = new ArrayList<>();

        getCombinationsUtil(pieceTable, combinations, 0, pieceTable.size(), new Stack<T>());

        return combinations;
    }

    /**
     * Helper class to retrieve all combinations
     * 
     * @param <T>          class extending furniture
     * @param pieceTable   the metatable of pieces created by getCheapestCombination
     * @param combinations list to store all combinations
     * @param currentIndex track which pieceTable index the method has reached
     * @param maxIndex     track the maximum index in pieceTable
     * @param current      helper stack to track current combination
     */
    private <T extends Furniture> void getCombinationsUtil(List<HashSet<T>> pieceTable, List<List<T>> combinations,
            int currentIndex, int maxIndex, Stack<T> current) {
        if (currentIndex == maxIndex) {
            combinations.add(new ArrayList<T>(current));
            return;
        }

        for (T chair : pieceTable.get(currentIndex)) {
            current.push(chair);
            getCombinationsUtil(pieceTable, combinations, currentIndex + 1, maxIndex, current);
            current.pop();
        }
    }

    /**
     * Add specified chair to chair metatable
     * 
     * @param table the metatable of chairs created by getCheapestCombination
     * @param chair chair to add
     */
    private void addChairToTable(List<HashSet<Chair>> table, Chair chair) {
        if (chair.pieceIsAvailable(chair.getArms())) {
            table.get(0).add(chair);
        }
        if (chair.pieceIsAvailable(chair.getLegs())) {
            table.get(1).add(chair);
        }
        if (chair.pieceIsAvailable(chair.getCushion())) {
            table.get(2).add(chair);
        }
        if (chair.pieceIsAvailable(chair.getSeat())) {
            table.get(3).add(chair);
        }
    }

    /**
     * Add specified desk to desk metatable
     * 
     * @param table the metatable of desks created by getCheapestCombination
     * @param desk  desk to add
     */
    private void addDeskToTable(List<HashSet<Desk>> table, Desk desk) {
        if (desk.pieceIsAvailable(desk.getDrawer())) {
            table.get(0).add(desk);
        }
        if (desk.pieceIsAvailable(desk.getLegs())) {
            table.get(1).add(desk);
        }
        if (desk.pieceIsAvailable(desk.getTop())) {
            table.get(2).add(desk);
        }
    }

    /**
     * Add specified filing to filing metatable
     * 
     * @param table  the metatable of filings created by getCheapestCombination
     * @param filing filing to add
     */
    private void addFilingToTable(List<HashSet<Filing>> table, Filing filing) {
        if (filing.pieceIsAvailable(filing.getCabinet())) {
            table.get(0).add(filing);
        }
        if (filing.pieceIsAvailable(filing.getDrawers())) {
            table.get(1).add(filing);
        }
        if (filing.pieceIsAvailable(filing.getRails())) {
            table.get(2).add(filing);
        }
    }

    /**
     * Add specified lamp to lamp metatable
     * 
     * @param table the metatable of lamps created by getCheapestCombination
     * @param lamp  lamp to add
     */
    private void addLampToTable(List<HashSet<Lamp>> table, Lamp lamp) {
        if (lamp.pieceIsAvailable(lamp.getBase())) {
            table.get(0).add(lamp);
        }
        if (lamp.pieceIsAvailable(lamp.getBulb())) {
            table.get(1).add(lamp);
        }
    }
}