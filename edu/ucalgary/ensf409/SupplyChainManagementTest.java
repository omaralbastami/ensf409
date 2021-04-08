package edu.ucalgary.ensf409;

import org.junit.*;
import static org.junit.Assert.*;

import java.io.File;
import java.sql.*;

public class SupplyChainManagementTest {
    private final String URL = "jdbc:mysql://localhost/inventory";
    private final String USER = "scm";
    private final String PASSWORD = "ensf409";
    private final String fileName = "orderform.txt";

    @Test
    public void inventory_getChairs() {
    }

    @Test
    public void inventory_getDesks() {
    }

    @Test
    public void inventory_getLamps() {
    }

    @Test
    public void inventory_getFilings() {
    }

    @Test
    public void inventory_getChairManufacturers() {
    }

    @Test
    public void inventory_getDeskManufacturers() {
    }

    @Test
    public void inventory_getLampManufacturers() {
    }

    @Test
    public void inventory_getFilingManufacturers() {
    }

    @Test
    public void inventory_getManufacturer() {
    }

    @Test
    public void inventory_deleteFurniture() {
    }

    @Test
    public void inventory_getCheapestChairCombination() {
    }

    @Test
    public void inventory_getCheapestLampCombination() {
    }

    @Test
    public void inventory_getCheapestDeskCombination() {
    }

    @Test
    public void inventory_getCheapestFilingCombination() {
    }

    @Test
    public void orderForm_createOrderForm() {
    }

    @Before
    public void start() {
        removeOrderData();
        // resetDatabase();
    }

    @After
    public void end() {
        removeOrderData();
        // resetDatabase();
    }

    private void removeOrderData() {
        try
        {
            String absolute = System.getProperty("user.dir");
            File path = new File(absolute);
            File orderToDelete = new File(path,fileName);
            if(orderToDelete.exists())
            {
                orderToDelete.delete();
            }
        }
        catch(Exception e)
        {
            System.err.println("Could not delete existing orderform.txt");
        }
    }

    private void resetDatabase() {
        try 
        {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            Connection dbConnect = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement myStmt = dbConnect.createStatement();
            myStmt.executeUpdate(
                "USE INVENTORY;"  + 
                "DROP TABLE IF EXISTS 'MANUFACTURER';" + //"\n" +
                "CREATE TABLE 'MANUFACTURER' (" + //"\n" +
                    "ManuID			char(3) not null," + //"\n" +
                    "Name			varchar(25)," + //"\n" +
                    "Phone			char(12)," + //"\n" +
                    "Province		char(2)," + //"\n" +
                    "primary key (ManuID)" + //"\n" +
                ");" + //"\n" +
                //"\n" +
                "INSERT INTO 'MANUFACTURER' (ManuID, Name, Phone, Province)" + //"\n" +
                "VALUES" + //"\n" +
                "('001',	'Academic Desks',	'236-145-2542',	'BC')," + //"\n" +
                "('002',	'Office Furnishings',	'587-890-4387',	'AB')," + //"\n" +
                "('003',	'Chairs R Us',	'705-667-9481',	'ON')," + //"\n" +
                "('004',	'Furniture Goods',	'306-512-5508',	'SK')," + //"\n" +
                "('005',	'Fine Office Supplies',	'403-980-9876',	'AB');"  //"\n" +
                //"\n" +
                // "DROP TABLE IF EXISTS CHAIR;" + //"\n" +
                // "CREATE TABLE CHAIR (" + //"\n" +
                //     "ID				char(5)	not null," + //"\n" +
                //     "Type			varchar(25)," + //"\n" +
                //     "Legs			char(1)," + //"\n" +
                //     "Arms			char(1)," + //"\n" +
                //     "Seat			char(1)," + //"\n" +
                //     "Cushion			char(1)," + //"\n" +
                //     "Price			integer," + //"\n" +
                //     "ManuID			char(3)," + //"\n" +
                //     "primary key (ID)," + //"\n" +
                //     "foreign key (ManuID) references MANUFACTURER(ManuID) ON UPDATE CASCADE" + //"\n" +
                // ");" +// "\n" +
                // //"\n" +
                // "INSERT INTO CHAIR (ID, Type, Legs, Arms, Seat, Cushion, Price, ManuID)" + //"\n" +
                // "VALUES" + //"\n" +
                // "('C1320',	'Kneeling',	'Y',	'N',	'N',	'N',	50,	'002')," + //"\n" +
                // "('C3405',	'Task',	'Y',	'Y',	'N',	'N',	100,	'003')," + //"\n" +
                // "('C9890',	'Mesh',	'N',	'Y',	'N',	'Y',	50,	'003')," + //"\n" +
                // "('C7268',	'Executive',	'N',	'N',	'Y',	'N',	75,	'004')," + //"\n" +
                // "('C0942',	'Mesh',	'Y',	'N',	'Y',	'Y',	175,	'005')," + //"\n" +
                // "('C4839',	'Ergonomic',	'N',	'N',	'N',	'Y',	50,	'002')," + //"\n" +
                // "('C2483',	'Executive',	'Y',	'Y',	'N',	'N',	175,	'002')," + //"\n" +
                // "('C5789',	'Ergonomic',	'Y',	'N',	'N',	'Y',	125,	'003')," + //"\n" +
                // "('C3819',	'Kneeling',	'N',	'N',	'Y',	'N',	75,	'005')," + //"\n" +
                // "('C5784',	'Executive',	'Y',	'N',	'N',	'Y',	150,	'004')," + //"\n" +
                // "('C6748',	'Mesh',	'Y',	'N',	'N',	'N',	75,	'003')," + //"\n" +
                // "('C0914',	'Task',	'N',	'N',	'Y',	'Y',	50,	'002')," + //"\n" +
                // "('C1148',	'Task',	'Y',	'N',	'Y',	'Y',	125,	'003')," + //"\n" +
                // "('C5409',	'Ergonomic',	'Y',	'Y',	'Y',	'N',	200,	'003')," + //"\n" +
                // "('C8138',	'Mesh',	'N',	'N',	'Y',	'N',	75,	'005');" + //"\n" +
                // //"\n" +
                // "DROP TABLE IF EXISTS DESK;" + //"\n" +
                // "CREATE TABLE DESK (" + //"\n" +
                //     "ID				char(5)	not null," + //"\n" +
                //     "Type			varchar(25)," + //"\n" +
                //     "Legs			char(1)," + //"\n" +
                //     "Top			char(1)," + //"\n" +
                //     "Drawer			char(1)," + //"\n" +
                //     "Price			integer," + //"\n" +
                //     "ManuID			char(3)," + //"\n" +
                //     "primary key (ID)," +// "\n" +
                //     "foreign key (ManuID) references MANUFACTURER(ManuID) ON UPDATE CASCADE" +// "\n" +
                // ");" +// "\n" +
                // //"\n" +
                // "INSERT INTO DESK (ID, Type, Legs, Top, Drawer, Price, ManuID)" + //"\n" +
                // "VALUES" + //"\n" +
                // "('D3820',	'Standing',	'Y',	'N',	'N',	150,	'001')," + //"\n" +
                // "('D4475',	'Adjustable',	'N',	'Y',	'Y',	200,	'002')," +// "\n" +
                // "('D0890',	'Traditional',	'N',	'N',	'Y',	25,	'002')," +// "\n" +
                // "('D2341',	'Standing',	'N',	'Y',	'N',	100,	'001')," +// "\n" +
                // "('D9387',	'Standing',	'Y',	'Y',	'N',	250,	'004')," +// "\n" +
                // "('D7373',	'Adjustable',	'Y',	'Y',	'N',	350,	'005')," +// "\n" +
                // "('D2746',	'Adjustable',	'Y',	'N',	'Y',	250,	'004')," + //"\n" +
                // "('D9352',	'Traditional',	'Y',	'N',	'Y',	75,	'002')," + //"\n" +
                // "('D4231',	'Traditional',	'N',	'Y',	'Y',	50,	'005')," + //"\n" +
                // "('D8675',	'Traditional',	'Y',	'Y',	'N',	75,	'001')," + //"\n" +
                // "('D1927',	'Standing',	'Y',	'N',	'Y',	200,	'005')," + //"\n" +
                // "('D1030',	'Adjustable',	'N',	'Y',	'N',	150,	'002')," +// "\n" +
                // "('D4438',	'Standing',	'N',	'Y',	'Y',	150,	'004')," + //"\n" +
                // "('D5437',	'Adjustable',	'Y',	'N',	'N',	200,	'001')," +// "\n" +
                // "('D3682',	'Adjustable',	'N',	'N',	'Y',	50,	'005');" + //"\n" +
                // //"\n" +
                // "DROP TABLE IF EXISTS LAMP;" + //"\n" +
                // "CREATE TABLE LAMP (" +// "\n" +
                //     "ID				char(4)	not null," +// "\n" +
                //     "Type			varchar(25)," +// "\n" +
                //     "Base			char(1)," +// "\n" +
                //     "Bulb			char(1)," +// "\n" +
                //     "Price			integer," + //"\n" +
                //     "ManuID			char(3)," +// "\n" +
                //     "primary key (ID)," +// "\n" +
                //     "foreign key (ManuID) references MANUFACTURER(ManuID) ON UPDATE CASCADE" +// "\n" +
                // ");" +// "\n" +
                // //"\n" +
                // "INSERT INTO LAMP (ID, Type, Base, Bulb, Price, ManuID)" +// "\n" +
                // "VALUES" +// "\n" +
                // "('L132',	'Desk',	'Y',	'N',	18,	'005')," +// "\n" +
                // "('L980',	'Study',	'N',	'Y',	2,	'004')," +// "\n" +
                // "('L487',	'Swing Arm',	'Y',	'N',	27,	'002')," +// "\n" +
                // "('L564',	'Desk',	'Y',	'Y',	20,	'004')," +// "\n" +
                // "('L342',	'Desk',	'N',	'Y',	2,	'002')," +// "\n" +
                // "('L982',	'Study',	'Y',	'N',	8,	'002')," +// "\n" +
                // "('L879',	'Swing Arm',	'N',	'Y',	3,	'005')," +// "\n" +
                // "('L208',	'Desk',	'N',	'Y',	2,	'005')," +// "\n" +
                // "('L223',	'Study',	'N',	'Y',	2,	'005')," +// "\n" +
                // "('L928',	'Study',	'Y',	'Y',	10,	'002')," +// "\n" +
                // "('L013',	'Desk',	'Y',	'N',	18,	'004')," +// "\n" +
                // "('L053',	'Swing Arm',	'Y',	'N',	27,	'002')," +// "\n" +
                // "('L112',	'Desk',	'Y',	'N',	18,	'005')," +// "\n" +
                // "('L649',	'Desk',	'Y',	'N',	18,	'004')," +// "\n" +
                // "('L096',	'Swing Arm',	'N',	'Y',	3,	'002');" +// "\n" +
                // //"\n" +
                // "DROP TABLE IF EXISTS FILING;" +// "\n" +
                // "CREATE TABLE FILING (" +// "\n" +
                //     "ID				char(4)	not null," +// "\n" +
                //     "Type			varchar(25)," +// "\n" +
                //     "Rails			char(1)," +// "\n" +
                //     "Drawers			char(1)," +// "\n" +
                //     "Cabinet			char(1)," +// "\n" +
                //     "Price			integer," + //"\n" +
                //     "ManuID			char(3)," +// "\n" +
                //     "primary key (ID)," + //"\n" +
                //     "foreign key (ManuID) references MANUFACTURER(ManuID) ON UPDATE CASCADE" + //"\n" +
                // ");" +// "\n" +
                // //"\n" +
                // "INSERT INTO FILING (ID, Type, Rails, Drawers, Cabinet, Price, ManuID)" +// "\n" +
                // "VALUES" + //"\n" +
                // "('F001',	'Small',	'Y',	'Y',	'N',	50,	'005')," +// "\n" +
                // "('F002',	'Medium',	'N',	'N',	'Y',	100,	'004')," +// "\n" +
                // "('F003',	'Large',	'N',	'N',	'Y',	150,	'002')," +// "\n" +
                // "('F004',	'Small',	'N',	'Y',	'Y',	75,	'004')," +// "\n" +
                // "('F005',	'Small',	'Y',	'N',	'Y',	75,	'005')," + //"\n" +
                // "('F006',	'Small',	'Y',	'Y',	'N',	50,	'005')," + //"\n" +
                // "('F007',	'Medium',	'N',	'Y',	'Y',	150,	'002')," +// "\n" +
                // "('F008',	'Medium',	'Y',	'N',	'N',	50,	'005')," + //"\n" +
                // "('F009',	'Medium',	'Y',	'Y',	'N',	100,	'004')," +// "\n" +
                // "('F010',	'Large',	'Y',	'N',	'Y',	225,	'002')," +// "\n" +
                // "('F011',	'Large',	'N',	'Y',	'Y',	225,	'005')," +// "\n" +
                // "('F012',	'Large',	'N',	'Y',	'N',	75,	'005')," +// "\n" +
                // "('F013',	'Small',	'N',	'N',	'Y',	50,	'002')," +// "\n" +
                // "('F014',	'Medium',	'Y',	'Y',	'Y',	200,	'002')," +// "\n" +
                // "('F015',	'Large',	'Y',	'N',	'N',	75,	'004');"
                );
        }
        catch(Exception e)
        {
            System.err.println("Could not reset database");
        }

    }
}
