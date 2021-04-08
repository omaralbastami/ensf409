package edu.ucalgary.ensf409;

import org.junit.*;

import edu.ucalgary.ensf409.Exceptions.*;

import static org.junit.Assert.*;

import java.io.*;
import java.sql.*;
import java.util.*;

public class SupplyChainManagementTest {
        private final String URL = "jdbc:mysql://localhost/inventory";
        private final String USER = "scm";
        private final String PASSWORD = "ensf409";
        private final String fileName = "orderform.txt";

        @Test
        public void inventory_getChairs() throws SQLException {
                Inventory inventory = new Inventory();
                inventory.initializeConnection();

                HashMap<String, Chair> expectedChairs = new HashMap<>();
                expectedChairs.put("C0942", new Chair('Y', 'N', 'Y', 'Y', "Mesh", "C0942", 175,
                                new Manufacturer("005", "Fine Office Supplies", "403-980-9876", "AB")));
                expectedChairs.put("C6748", new Chair('Y', 'N', 'N', 'N', "Mesh", "C6748", 75,
                                new Manufacturer("003", "Chairs R Us", "705-667-9481", "ON")));
                expectedChairs.put("C8138", new Chair('N', 'N', 'Y', 'N', "Mesh", "C8138", 75,
                                new Manufacturer("005", "Fine Office Supplies", "403-980-9876", "AB")));
                expectedChairs.put("C9890", new Chair('N', 'Y', 'N', 'Y', "Mesh", "C9890", 50,
                                new Manufacturer("003", "Chairs R Us", "705-667-9481", "ON")));

                Chair[] actual = inventory.getChairs("mesh");

                assertEquals("Expected 4 Mesh chairs", 4, actual.length);

                for (Chair chair : actual) {
                        boolean exists = expectedChairs.containsKey(chair.getId());

                        assertTrue("Unexpected chair with ID " + chair.getId(), exists);
                        if (exists) {
                                Chair expected = expectedChairs.get(chair.getId());
                                assertEquals("Mismatching legs value for chair with ID " + chair.getId(),
                                                expected.getLegs(), chair.getLegs());
                                assertEquals("Mismatching arms value for chair with ID " + chair.getId(),
                                                expected.getArms(), chair.getArms());
                                assertEquals("Mismatching seat value for chair with ID " + chair.getId(),
                                                expected.getSeat(), chair.getSeat());
                                assertEquals("Mismatching cushion value for chair with ID " + chair.getId(),
                                                expected.getCushion(), chair.getCushion());
                                assertEquals("Mismatching price value for chair with ID " + chair.getId(),
                                                expected.getPrice(), chair.getPrice());
                                assertEquals("Mismatching manu ID value for chair with ID " + chair.getId(),
                                                expected.getManufacturer().getManuID(),
                                                chair.getManufacturer().getManuID());
                        }
                }

                Chair[] fakeType = inventory.getChairs("random");
                assertEquals("Expected empty array with fake type", 0, fakeType.length);
        }

        @Test
        public void inventory_getDesks() throws SQLException {
            Inventory inventory = new Inventory();
            inventory.initializeConnection();
    
            HashMap<String, Desk> expectedDesks = new HashMap<>();
            expectedDesks.put("D3820", new Desk('Y', 'N', 'N', "Standing", "D3820", 150,
                    new Manufacturer("001", "Academic Desks", "236-145-2542", "BC")));
            expectedDesks.put("D2341", new Desk('N', 'Y', 'N', "Standing", "D2341", 100,
                    new Manufacturer("001", "Academic Desks", "236-145-2542", "BC")));
            expectedDesks.put("D9387", new Desk('Y', 'Y', 'N', "Standing", "D9387", 250,
                    new Manufacturer("004", "Furniture Goods", "306-512-5508", "SK")));
            expectedDesks.put("D1927", new Desk('Y', 'N', 'Y', "Standing", "D1927", 200,
                    new Manufacturer("005", "Fine Office Supplies", "403-980-9876", "AB")));
            expectedDesks.put("D4438", new Desk('N', 'Y', 'Y', "Standing", "D4438", 150,
                    new Manufacturer("004", "Furniture Goods", "306-512-5508", "SK")));
    
            Desk[] actual = inventory.getDesks("standing");
    
            assertEquals("Expected 5 Standing desks", 5, actual.length);
    
            for (Desk desk : actual) {
                boolean exists = expectedDesks.containsKey(desk.getId());
    
                assertTrue("Unexpected desk with ID " + desk.getId(), exists);
                if (exists) {
                    Desk expected = expectedDesks.get(desk.getId());
                    assertEquals("Mismatching legs value for desk with ID " + desk.getId(), expected.getLegs(),
                            desk.getLegs());
                    assertEquals("Mismatching top value for desk with ID " + desk.getId(), expected.getTop(),
                            desk.getTop());
                    assertEquals("Mismatching drawer value for desk with ID " + desk.getId(), expected.getDrawer(),
                            desk.getDrawer());
                    assertEquals("Mismatching price value for desk with ID " + desk.getId(), expected.getPrice(),
                            desk.getPrice());
                    assertEquals("Mismatching manu ID value for desk with ID " + desk.getId(),
                            expected.getManufacturer().getManuID(), desk.getManufacturer().getManuID());
                }
            }
    
            Desk[] fakeType = inventory.getDesks("random");
            assertEquals("Expected empty array with fake type", 0, fakeType.length);
        }
    
        @Test
        public void inventory_getLamps() throws SQLException {
            Inventory inventory = new Inventory();
            inventory.initializeConnection();
    
            HashMap<String, Lamp> expectedLamps = new HashMap<>();
            expectedLamps.put("L980", new Lamp('N', 'Y', "Study", "L980", 2,
                    new Manufacturer("004", "Furniture Goods", "306-512-5508", "SK")));
            expectedLamps.put("L982", new Lamp('Y', 'N', "Study", "L982", 8,
                    new Manufacturer("002", "Office Furnishings", "587-890-4387", "AB")));
            expectedLamps.put("L223", new Lamp('N', 'Y', "Study", "L223", 2,
                    new Manufacturer("005", "Fine Office Supplies", "403-980-9876", "AB")));
            expectedLamps.put("L928", new Lamp('Y', 'Y', "Study", "L928", 10,
                    new Manufacturer("002", "Office Furnishings", "587-890-4387", "AB")));
    
            Lamp[] actual = inventory.getLamps("study");
    
            assertEquals("Expected 4 Study lamps", 4, actual.length);
    
            for (Lamp lamp : actual) {
                boolean exists = expectedLamps.containsKey(lamp.getId());
    
                assertTrue("Unexpected lamp with ID " + lamp.getId(), exists);
                if (exists) {
                    Lamp expected = expectedLamps.get(lamp.getId());
                    assertEquals("Mismatching base value for lamp with ID " + lamp.getId(), expected.getBase(),
                            lamp.getBase());
                    assertEquals("Mismatching bulb value for lamp with ID " + lamp.getId(), expected.getBulb(),
                            lamp.getBulb());
                    assertEquals("Mismatching price value for lamp with ID " + lamp.getId(), expected.getPrice(),
                            lamp.getPrice());
                    assertEquals("Mismatching manu ID value for lamp with ID " + lamp.getId(),
                            expected.getManufacturer().getManuID(), lamp.getManufacturer().getManuID());
                }
            }
    
            Lamp[] fakeType = inventory.getLamps("random");
            assertEquals("Expected empty array with fake type", 0, fakeType.length);
        }
    
        @Test
        public void inventory_getFilings() throws SQLException {
            Inventory inventory = new Inventory();
            inventory.initializeConnection();
    
            HashMap<String, Filing> expectedFilings = new HashMap<>();
            expectedFilings.put("F002", new Filing('N', 'N', 'Y', "Medium", "F002", 100,
                    new Manufacturer("004", "Furniture Goods", "306-512-5508", "SK")));
            expectedFilings.put("F007", new Filing('N', 'Y', 'Y', "Medium", "F007", 150,
                    new Manufacturer("002", "Office Furnishings", "587-890-4387", "AB")));
            expectedFilings.put("F008", new Filing('Y', 'N', 'N', "Medium", "F008", 50,
                    new Manufacturer("005", "Fine Office Supplies", "403-980-9876", "AB")));
            expectedFilings.put("F009", new Filing('Y', 'Y', 'N', "Medium", "F009", 100,
                    new Manufacturer("004", "Furniture Goods", "306-512-5508", "SK")));
            expectedFilings.put("F014", new Filing('Y', 'Y', 'Y', "Medium", "F014", 200,
                    new Manufacturer("002", "Office Furnishings", "587-890-4387", "AB")));
    
            Filing[] actual = inventory.getFilings("medium");
    
            assertEquals("Expected 5 Medium filings", 5, actual.length);
    
            for (Filing filing : actual) {
                boolean exists = expectedFilings.containsKey(filing.getId());
    
                assertTrue("Unexpected filing with ID " + filing.getId(), exists);
                if (exists) {
                    Filing expected = expectedFilings.get(filing.getId());
                    assertEquals("Mismatching rails value for filing with ID " + filing.getId(), expected.getRails(),
                            filing.getRails());
                    assertEquals("Mismatching drawers value for filing with ID " + filing.getId(), expected.getDrawers(),
                            filing.getDrawers());
                    assertEquals("Mismatching cabinet value for filing with ID " + filing.getId(), expected.getCabinet(),
                            filing.getCabinet());
                    assertEquals("Mismatching price value for filing with ID " + filing.getId(), expected.getPrice(),
                            filing.getPrice());
                    assertEquals("Mismatching manu ID value for filing with ID " + filing.getId(),
                            expected.getManufacturer().getManuID(), filing.getManufacturer().getManuID());
                }
            }
    
            Filing[] fakeType = inventory.getFilings("random");
            assertEquals("Expected empty array with fake type", 0, fakeType.length);
    
        }

        @Test
        public void inventory_getChairManufacturers() throws SQLException {
                Inventory inventory = new Inventory();
                inventory.initializeConnection();

                HashMap<String, Manufacturer> expectedManufacturers = new HashMap<>();
                expectedManufacturers.put("002", new Manufacturer("002", "Office Furnishings", "587-890-4387", "AB"));
                expectedManufacturers.put("003", new Manufacturer("003", "Chairs R Us", "705-667-9481", "ON"));
                expectedManufacturers.put("004", new Manufacturer("004", "Furniture Goods", "306-512-5508", "SK"));
                expectedManufacturers.put("005", new Manufacturer("005", "Fine Office Supplies", "403-980-9876", "AB"));

                Manufacturer[] actual = inventory.getChairManufacturers();

                assertEquals("Expected 4 chair manufacturers", 4, actual.length);

                for (Manufacturer manu : actual) {
                        boolean exists = expectedManufacturers.containsKey(manu.getManuID());

                        assertTrue("Received manufacturer with ID " + manu.getManuID()
                                        + " which is not a chair manufacturer", exists);
                        if (exists) {
                                Manufacturer expected = expectedManufacturers.get(manu.getManuID());
                                assertEquals("Mismatching name value for manufacturer with ID " + manu.getManuID(),
                                                expected.getName(), manu.getName());
                                assertEquals("Mismatching province value for manufacturer with ID " + manu.getManuID(),
                                                expected.getProvince(), manu.getProvince());
                                assertEquals("Mismatching phone value for manufacturer with ID " + manu.getManuID(),
                                                expected.getPhone(), manu.getPhone());
                        }
                }
        }

        @Test
        public void inventory_getDeskManufacturers() throws SQLException {
                Inventory inventory = new Inventory();
                inventory.initializeConnection();

                HashMap<String, Manufacturer> expectedManufacturers = new HashMap<>();
                expectedManufacturers.put("001", new Manufacturer("001", "Academic Desks", "236-145-2542", "BC"));
                expectedManufacturers.put("002", new Manufacturer("002", "Office Furnishings", "587-890-4387", "AB"));
                expectedManufacturers.put("004", new Manufacturer("004", "Furniture Goods", "306-512-5508", "SK"));
                expectedManufacturers.put("005", new Manufacturer("005", "Fine Office Supplies", "403-980-9876", "AB"));

                Manufacturer[] actual = inventory.getDeskManufacturers();

                assertEquals("Expected 4 Desk manufacturers", 4, actual.length);

                for (Manufacturer manu : actual) {
                        boolean exists = expectedManufacturers.containsKey(manu.getManuID());

                        assertTrue("Received manufacturer with ID " + manu.getManuID()
                                        + " which is not a desk manufacturer", exists);
                        if (exists) {
                                Manufacturer expected = expectedManufacturers.get(manu.getManuID());
                                assertEquals("Mismatching name value for manufacturer with ID " + manu.getManuID(),
                                                expected.getName(), manu.getName());
                                assertEquals("Mismatching province value for manufacturer with ID " + manu.getManuID(),
                                                expected.getProvince(), manu.getProvince());
                                assertEquals("Mismatching phone value for manufacturer with ID " + manu.getManuID(),
                                                expected.getPhone(), manu.getPhone());
                        }
                }
        }

        @Test
        public void inventory_getLampManufacturers() throws SQLException {
                Inventory inventory = new Inventory();
                inventory.initializeConnection();

                HashMap<String, Manufacturer> expectedManufacturers = new HashMap<>();
                expectedManufacturers.put("002", new Manufacturer("002", "Office Furnishings", "587-890-4387", "AB"));
                expectedManufacturers.put("004", new Manufacturer("004", "Furniture Goods", "306-512-5508", "SK"));
                expectedManufacturers.put("005", new Manufacturer("005", "Fine Office Supplies", "403-980-9876", "AB"));

                Manufacturer[] actual = inventory.getLampManufacturers();

                assertEquals("Expected 3 Lamp manufacturers", 3, actual.length);

                for (Manufacturer manu : actual) {
                        boolean exists = expectedManufacturers.containsKey(manu.getManuID());

                        assertTrue("Received manufacturer with ID " + manu.getManuID()
                                        + " which is not a lamp manufacturer", exists);
                        if (exists) {
                                Manufacturer expected = expectedManufacturers.get(manu.getManuID());
                                assertEquals("Mismatching name value for manufacturer with ID " + manu.getManuID(),
                                                expected.getName(), manu.getName());
                                assertEquals("Mismatching province value for manufacturer with ID " + manu.getManuID(),
                                                expected.getProvince(), manu.getProvince());
                                assertEquals("Mismatching phone value for manufacturer with ID " + manu.getManuID(),
                                                expected.getPhone(), manu.getPhone());
                        }
                }
        }

        @Test
        public void inventory_getFilingManufacturers() throws SQLException {
                Inventory inventory = new Inventory();
                inventory.initializeConnection();

                HashMap<String, Manufacturer> expectedManufacturers = new HashMap<>();
                expectedManufacturers.put("002", new Manufacturer("002", "Office Furnishings", "587-890-4387", "AB"));
                expectedManufacturers.put("004", new Manufacturer("004", "Furniture Goods", "306-512-5508", "SK"));
                expectedManufacturers.put("005", new Manufacturer("005", "Fine Office Supplies", "403-980-9876", "AB"));

                Manufacturer[] actual = inventory.getFilingManufacturers();

                assertEquals("Expected 3 Filing manufacturers", 3, actual.length);

                for (Manufacturer manu : actual) {
                        boolean exists = expectedManufacturers.containsKey(manu.getManuID());

                        assertTrue("Received manufacturer with ID " + manu.getManuID()
                                        + " which is not a filing manufacturer", exists);
                        if (exists) {
                                Manufacturer expected = expectedManufacturers.get(manu.getManuID());
                                assertEquals("Mismatching name value for manufacturer with ID " + manu.getManuID(),
                                                expected.getName(), manu.getName());
                                assertEquals("Mismatching province value for manufacturer with ID " + manu.getManuID(),
                                                expected.getProvince(), manu.getProvince());
                                assertEquals("Mismatching phone value for manufacturer with ID " + manu.getManuID(),
                                                expected.getPhone(), manu.getPhone());
                        }
                }
        }

        @Test
        public void inventory_getManufacturer() throws SQLException {
                Inventory inventory = new Inventory();
                inventory.initializeConnection();

                Manufacturer expected = new Manufacturer("005", "Fine Office Supplies", "403-980-9876", "AB");
                Manufacturer actual = inventory.getManufacturer("005");

                assertEquals("Mismatching name value for manufacturer with ID " + actual.getManuID(),
                                expected.getName(), actual.getName());
                assertEquals("Mismatching province value for manufacturer with ID " + actual.getManuID(),
                                expected.getProvince(), actual.getProvince());
                assertEquals("Mismatching phone value for manufacturer with ID " + actual.getManuID(),
                                expected.getPhone(), actual.getPhone());

                Manufacturer fakeManufacturer = inventory.getManufacturer("fake");
                assertNull("Unexpected result received from fake manufacturer ID", fakeManufacturer);
        }

        @Test
        public void inventory_getCheapestChairCombination() throws SQLException, OrderFormException {
                Inventory inventory = new Inventory();
                inventory.initializeConnection();

                Chair[] chairs = inventory.getChairs("mesh");

                HashMap<String, Chair> expectedChairs = new HashMap<>();
                expectedChairs.put("C6748", new Chair('Y', 'N', 'N', 'N', "Mesh", "C6748", 75,
                                new Manufacturer("003", "Chairs R Us", "705-667-9481", "ON")));
                expectedChairs.put("C8138", new Chair('N', 'N', 'Y', 'N', "Mesh", "C8138", 75,
                                new Manufacturer("005", "Fine Office Supplies", "403-980-9876", "AB")));
                expectedChairs.put("C9890", new Chair('N', 'Y', 'N', 'Y', "Mesh", "C9890", 50,
                                new Manufacturer("003", "Chairs R Us", "705-667-9481", "ON")));

                Chair[] actual = inventory.getCheapestCombination(chairs, 1);

                assertEquals("Expected " + expectedChairs.size() + " Mesh chairs", expectedChairs.size(),
                                actual.length);

                for (Chair chair : actual) {
                        boolean exists = expectedChairs.containsKey(chair.getId());

                        assertTrue("Unexpected chair with ID " + chair.getId(), exists);
                        if (exists) {
                                Chair expected = expectedChairs.get(chair.getId());
                                assertEquals("Mismatching legs value for chair with ID " + chair.getId(),
                                                expected.getLegs(), chair.getLegs());
                                assertEquals("Mismatching arms value for chair with ID " + chair.getId(),
                                                expected.getArms(), chair.getArms());
                                assertEquals("Mismatching seat value for chair with ID " + chair.getId(),
                                                expected.getSeat(), chair.getSeat());
                                assertEquals("Mismatching cushion value for chair with ID " + chair.getId(),
                                                expected.getCushion(), chair.getCushion());
                                assertEquals("Mismatching price value for chair with ID " + chair.getId(),
                                                expected.getPrice(), chair.getPrice());
                                assertEquals("Mismatching manu ID value for chair with ID " + chair.getId(),
                                                expected.getManufacturer().getManuID(),
                                                chair.getManufacturer().getManuID());
                        }
                }

                Chair[] impossibleOrder = inventory.getCheapestCombination(chairs, 10);
                assertEquals("Expected empty array with chair order that cannot be fulfilled", 0,
                                impossibleOrder.length);
        }

        @Test
        public void inventory_getCheapestLampCombination() throws SQLException, OrderFormException {
                Inventory inventory = new Inventory();
                inventory.initializeConnection();

                Lamp[] lamps = inventory.getLamps("study");

                HashMap<String, Lamp> expectedLamps = new HashMap<>();
                expectedLamps.put("L928", new Lamp('Y', 'Y', "Desk", "L928", 10,
                                new Manufacturer("002", "Office Furnishings", "587-890-4387", "AB")));

                Lamp[] actual = inventory.getCheapestCombination(lamps, 1);

                assertEquals("Expected " + expectedLamps.size() + " Desk lamps", expectedLamps.size(), actual.length);

                for (Lamp lamp : actual) {
                        boolean exists = expectedLamps.containsKey(lamp.getId());

                        assertTrue("Unexpected lamp with ID " + lamp.getId(), exists);
                        if (exists) {
                                Lamp expected = expectedLamps.get(lamp.getId());
                                assertEquals("Mismatching base value for lamp with ID " + lamp.getId(),
                                                expected.getBase(), lamp.getBase());
                                assertEquals("Mismatching bulb value for lamp with ID " + lamp.getId(),
                                                expected.getBulb(), lamp.getBulb());
                                assertEquals("Mismatching price value for lamp with ID " + lamp.getId(),
                                                expected.getPrice(), lamp.getPrice());
                                assertEquals("Mismatching manu ID value for lamp with ID " + lamp.getId(),
                                                expected.getManufacturer().getManuID(),
                                                lamp.getManufacturer().getManuID());
                        }
                }

                Lamp[] impossibleOrder = inventory.getCheapestCombination(lamps, 10);
                assertEquals("Expected empty array with lamp order that cannot be fulfilled", 0,
                                impossibleOrder.length);
        }

        @Test
        public void inventory_getCheapestDeskCombination() throws SQLException, OrderFormException {
                Inventory inventory = new Inventory();
                inventory.initializeConnection();

                Desk[] desks = inventory.getDesks("Traditional");

                HashMap<String, Desk> expectedDesks = new HashMap<>();
                expectedDesks.put("D8675", new Desk('Y', 'Y', 'N', "Traditional", "D8675", 75,
                                new Manufacturer("001", "Academic Desks", "236-145-2542", "BC")));
                expectedDesks.put("D0890", new Desk('N', 'N', 'Y', "Traditional", "D0890", 25,
                                new Manufacturer("002", "Office Furnishings", "587-890-4387", "AB")));

                Desk[] actual = inventory.getCheapestCombination(desks, 1);

                assertEquals("Expected " + expectedDesks.size() + " Traditional desks", expectedDesks.size(),
                                actual.length);

                for (Desk desk : actual) {
                        boolean exists = expectedDesks.containsKey(desk.getId());

                        assertTrue("Unexpected desk with ID " + desk.getId(), exists);
                        if (exists) {
                                Desk expected = expectedDesks.get(desk.getId());
                                assertEquals("Mismatching leg value for desk with ID " + desk.getId(),
                                                expected.getLegs(), desk.getLegs());
                                assertEquals("Mismatching top value for desk with ID " + desk.getId(),
                                                expected.getTop(), desk.getTop());
                                assertEquals("Mismatching drawer value for desk with ID " + desk.getId(),
                                                expected.getDrawer(), desk.getDrawer());
                                assertEquals("Mismatching price value for desk with ID " + desk.getId(),
                                                expected.getPrice(), desk.getPrice());
                                assertEquals("Mismatching manu ID value for desk with ID " + desk.getId(),
                                                expected.getManufacturer().getManuID(),
                                                desk.getManufacturer().getManuID());
                        }
                }

                Desk[] impossibleOrder = inventory.getCheapestCombination(desks, 10);
                assertEquals("Expected empty array with desk order that cannot be fulfilled", 0,
                                impossibleOrder.length);
        }

        @Test
        public void inventory_getCheapestFilingCombination() throws SQLException, OrderFormException {
                Inventory inventory = new Inventory();
                inventory.initializeConnection();

                Filing[] filings = inventory.getFilings("Medium");

                HashMap<String, Filing> expectedFilings = new HashMap<>();
                expectedFilings.put("F002", new Filing('N', 'N', 'Y', "Medium", "F002", 100,
                                new Manufacturer("004", "Chairs R Us", "705-667-9481", "ON")));
                expectedFilings.put("F007", new Filing('N', 'Y', 'Y', "Medium", "F007", 150,
                                new Manufacturer("002", "Office Furnishings", "587-890-4387", "AB")));
                expectedFilings.put("F008", new Filing('Y', 'N', 'N', "Medium", "F008", 50,
                                new Manufacturer("005", "Fine Office Supplies", "403-980-9876", "AB")));
                expectedFilings.put("F009", new Filing('Y', 'Y', 'N', "Medium", "F009", 100,
                                new Manufacturer("004", "Chairs R Us", "705-667-9481", "ON")));
                expectedFilings.put("F014", new Filing('Y', 'Y', 'Y', "Medium", "F014", 200,
                                new Manufacturer("002", "Office Furnishings", "587-890-4387", "AB")));

                Filing[] actual = inventory.getCheapestCombination(filings, 3);

                assertEquals("Expected " + expectedFilings.size() + " Medium filings", expectedFilings.size(),
                                actual.length);

                for (Filing filing : actual) {
                        boolean exists = expectedFilings.containsKey(filing.getId());

                        assertTrue("Unexpected Filing with ID " + filing.getId(), exists);
                        if (exists) {
                                Filing expected = expectedFilings.get(filing.getId());
                                assertEquals("Mismatching rails value for filing with ID " + filing.getId(),
                                                expected.getRails(), filing.getRails());
                                assertEquals("Mismatching drawers value for filing with ID " + filing.getId(),
                                                expected.getDrawers(), filing.getDrawers());
                                assertEquals("Mismatching cabinet value for filing with ID " + filing.getId(),
                                                expected.getCabinet(), filing.getCabinet());
                                assertEquals("Mismatching price value for filing with ID " + filing.getId(),
                                                expected.getPrice(), filing.getPrice());
                                assertEquals("Mismatching manu ID value for filing with ID " + filing.getId(),
                                                expected.getManufacturer().getManuID(),
                                                filing.getManufacturer().getManuID());
                        }
                }

                Filing[] impossibleOrder = inventory.getCheapestCombination(filings, 10);
                assertEquals("Expected empty array with Filing order that cannot be fulfilled", 0,
                                impossibleOrder.length);
        }

        @Test
        public void orderForm_createOrderForm() throws OrderFormException {
                String category = "Chair";
                int quantity = 1;
                Furniture[] furnitures = {
                                new Chair('N', 'Y', 'N', 'Y', "Mesh", "C9890", 50,
                                                new Manufacturer("003", "Chairs R Us", "705-667-9481", "ON")),
                                new Chair('N', 'N', 'Y', 'N', "Mesh", "C8138", 75,
                                                new Manufacturer("005", "Fine Office Supplies", "403-980-9876", "AB")),
                                new Chair('N', 'Y', 'N', 'Y', "Mesh", "C9890", 50,
                                                new Manufacturer("003", "Chairs R Us", "705-667-9481", "ON")) };

                OrderForm orderForm = new OrderForm(furnitures, category, quantity);
                orderForm.createOrderForm();

                assertTrue("orderform.txt was not created", orderFileExists());

                String content = getOrderFileContent();

                StringBuilder expected = new StringBuilder();
                expected.append("Furniture Order Form\n\nFaculty Name:\nContact:\nDate:\n\n")
                                .append("Original Request: " + furnitures[0].getType() + " " + category + ", "
                                                + quantity + "\n\n")
                                .append("Items Ordered\n");
                for (Furniture furniture : furnitures) {
                        expected.append("ID: " + furniture.getId() + "\n");
                }
                expected.append("\n");
                expected.append("Total Price: $" + getTotalPrice(furnitures));

                String expectedString = expected.toString();

                assertEquals("orderform.txt content is formatted incorrectly", expectedString.trim(), content.trim());
        }

        @Before
        public void start() {
                removeOrderData();
                resetDatabase();
        }

        @After
        public void end() {
                removeOrderData();
                resetDatabase();
        }

        private int getTotalPrice(Furniture[] furnitures) {
                int price = 0;
                for (int i = 0; i < furnitures.length; i++) {
                        price += furnitures[i].getPrice();
                }
                return price;
        }

        private boolean orderFileExists() {
                String absolute = System.getProperty("user.dir");
                File path = new File(absolute);
                File file = new File(path, fileName);
                return file.exists();
        }

        private String getOrderFileContent() {
                try {
                        BufferedReader br = new BufferedReader(new FileReader(fileName));
                        StringBuilder sb = new StringBuilder();
                        String line = br.readLine();

                        while (line != null) {
                                sb.append(line);
                                sb.append("\n");
                                line = br.readLine();
                        }

                        br.close();
                        return sb.toString();
                }

                catch (Exception e) {
                        System.err.println("Could not read contents of orderform.txt");
                        return null;
                }
        }

        private void removeOrderData() {
                try {
                        String absolute = System.getProperty("user.dir");
                        File path = new File(absolute);
                        File orderToDelete = new File(path, fileName);
                        if (orderToDelete.exists()) {
                                orderToDelete.delete();
                        }
                } catch (Exception e) {
                        System.err.println("Could not delete existing orderform.txt");
                }
        }

        private void resetDatabase() {
                try {
                        String[] sqlQuery = { "USE INVENTORY;", "SET FOREIGN_KEY_CHECKS=0;",
                                        "DROP TABLE IF EXISTS MANUFACTURER;", "SET FOREIGN_KEY_CHECKS=1;",
                                        "CREATE TABLE MANUFACTURER (ManuID			char(3) not null,Name			varchar(25),Phone			char(12),Province		char(2),primary key (ManuID));",
                                        "INSERT INTO MANUFACTURER (ManuID, Name, Phone, Province)VALUES('001',	'Academic Desks',	'236-145-2542',	'BC'),('002',	'Office Furnishings',	'587-890-4387',	'AB'),('003',	'Chairs R Us',	'705-667-9481',	'ON'),('004',	'Furniture Goods',	'306-512-5508',	'SK'),('005',	'Fine Office Supplies',	'403-980-9876',	'AB');",
                                        "DROP TABLE IF EXISTS CHAIR;",
                                        "CREATE TABLE CHAIR (ID				char(5)	not null,Type			varchar(25),Legs			char(1),Arms			char(1),Seat			char(1),Cushion			char(1),Price			integer,ManuID			char(3),primary key (ID),foreign key (ManuID) references MANUFACTURER(ManuID) ON UPDATE CASCADE);",
                                        "INSERT INTO CHAIR (ID, Type, Legs, Arms, Seat, Cushion, Price, ManuID)VALUES('C1320',	'Kneeling',	'Y',	'N',	'N',	'N',	50,	'002'),('C3405',	'Task',	'Y',	'Y',	'N',	'N',	100,	'003'),('C9890',	'Mesh',	'N',	'Y',	'N',	'Y',	50,	'003'),('C7268',	'Executive',	'N',	'N',	'Y',	'N',	75,	'004'),('C0942',	'Mesh',	'Y',	'N',	'Y',	'Y',	175,	'005'),('C4839',	'Ergonomic',	'N',	'N',	'N',	'Y',	50,	'002'),('C2483',	'Executive',	'Y',	'Y',	'N',	'N',	175,	'002'),('C5789',	'Ergonomic',	'Y',	'N',	'N',	'Y',	125,	'003'),('C3819',	'Kneeling',	'N',	'N',	'Y',	'N',	75,	'005'),('C5784',	'Executive',	'Y',	'N',	'N',	'Y',	150,	'004'),('C6748',	'Mesh',	'Y',	'N',	'N',	'N',	75,	'003'),('C0914',	'Task',	'N',	'N',	'Y',	'Y',	50,	'002'),('C1148',	'Task',	'Y',	'N',	'Y',	'Y',	125,	'003'),('C5409',	'Ergonomic',	'Y',	'Y',	'Y',	'N',	200,	'003'),('C8138',	'Mesh',	'N',	'N',	'Y',	'N',	75,	'005');",
                                        "DROP TABLE IF EXISTS DESK;",
                                        "CREATE TABLE DESK (ID				char(5)	not null,Type			varchar(25),Legs			char(1),Top			char(1),Drawer			char(1),Price			integer,ManuID			char(3),primary key (ID),foreign key (ManuID) references MANUFACTURER(ManuID) ON UPDATE CASCADE);",
                                        "INSERT INTO DESK (ID, Type, Legs, Top, Drawer, Price, ManuID)VALUES('D3820',	'Standing',	'Y',	'N',	'N',	150,	'001'),('D4475',	'Adjustable',	'N',	'Y',	'Y',	200,	'002'),('D0890',	'Traditional',	'N',	'N',	'Y',	25,	'002'),('D2341',	'Standing',	'N',	'Y',	'N',	100,	'001'),('D9387',	'Standing',	'Y',	'Y',	'N',	250,	'004'),('D7373',	'Adjustable',	'Y',	'Y',	'N',	350,	'005'),('D2746',	'Adjustable',	'Y',	'N',	'Y',	250,	'004'),('D9352',	'Traditional',	'Y',	'N',	'Y',	75,	'002'),('D4231',	'Traditional',	'N',	'Y',	'Y',	50,	'005'),('D8675',	'Traditional',	'Y',	'Y',	'N',	75,	'001'),('D1927',	'Standing',	'Y',	'N',	'Y',	200,	'005'),('D1030',	'Adjustable',	'N',	'Y',	'N',	150,	'002'),('D4438',	'Standing',	'N',	'Y',	'Y',	150,	'004'),('D5437',	'Adjustable',	'Y',	'N',	'N',	200,	'001'),('D3682',	'Adjustable',	'N',	'N',	'Y',	50,	'005');",
                                        "DROP TABLE IF EXISTS LAMP;",
                                        "CREATE TABLE LAMP (ID				char(4)	not null,Type			varchar(25),Base			char(1),Bulb			char(1),Price			integer,ManuID			char(3),primary key (ID),foreign key (ManuID) references MANUFACTURER(ManuID) ON UPDATE CASCADE);",
                                        "INSERT INTO LAMP (ID, Type, Base, Bulb, Price, ManuID)VALUES('L132',	'Desk',	'Y',	'N',	18,	'005'),('L980',	'Study',	'N',	'Y',	2,	'004'),('L487',	'Swing Arm',	'Y',	'N',	27,	'002'),('L564',	'Desk',	'Y',	'Y',	20,	'004'),('L342',	'Desk',	'N',	'Y',	2,	'002'),('L982',	'Study',	'Y',	'N',	8,	'002'),('L879',	'Swing Arm',	'N',	'Y',	3,	'005'),('L208',	'Desk',	'N',	'Y',	2,	'005'),('L223',	'Study',	'N',	'Y',	2,	'005'),('L928',	'Study',	'Y',	'Y',	10,	'002'),('L013',	'Desk',	'Y',	'N',	18,	'004'),('L053',	'Swing Arm',	'Y',	'N',	27,	'002'),('L112',	'Desk',	'Y',	'N',	18,	'005'),('L649',	'Desk',	'Y',	'N',	18,	'004'),('L096',	'Swing Arm',	'N',	'Y',	3,	'002');",
                                        "DROP TABLE IF EXISTS FILING;",
                                        "CREATE TABLE FILING (ID				char(4)	not null,Type			varchar(25),Rails			char(1),Drawers			char(1),Cabinet			char(1),Price			integer,ManuID			char(3),primary key (ID),foreign key (ManuID) references MANUFACTURER(ManuID) ON UPDATE CASCADE);",
                                        "INSERT INTO FILING (ID, Type, Rails, Drawers, Cabinet, Price, ManuID)VALUES('F001',	'Small',	'Y',	'Y',	'N',	50,	'005'),('F002',	'Medium',	'N',	'N',	'Y',	100,	'004'),('F003',	'Large',	'N',	'N',	'Y',	150,	'002'),('F004',	'Small',	'N',	'Y',	'Y',	75,	'004'),('F005',	'Small',	'Y',	'N',	'Y',	75,	'005'),('F006',	'Small',	'Y',	'Y',	'N',	50,	'005'),('F007',	'Medium',	'N',	'Y',	'Y',	150,	'002'),('F008',	'Medium',	'Y',	'N',	'N',	50,	'005'),('F009',	'Medium',	'Y',	'Y',	'N',	100,	'004'),('F010',	'Large',	'Y',	'N',	'Y',	225,	'002'),('F011',	'Large',	'N',	'Y',	'Y',	225,	'005'),('F012',	'Large',	'N',	'Y',	'N',	75,	'005'),('F013',	'Small',	'N',	'N',	'Y',	50,	'002'),('F014',	'Medium',	'Y',	'Y',	'Y',	200,	'002'),('F015',	'Large',	'Y',	'N',	'N',	75,	'004');" };
                        DriverManager.registerDriver(new com.mysql.jdbc.Driver());
                        Connection dbConnect = DriverManager.getConnection(URL, USER, PASSWORD);
                        Statement myStmt = dbConnect.createStatement();
                        for (int i = 0; i < sqlQuery.length; i++) {
                                String tmp = sqlQuery[i].replace("\u0009", " ").trim();
                                myStmt.executeUpdate(tmp);
                        }

                } catch (Exception e) {
                        System.err.println("Could not reset database");
                }
        }
}
