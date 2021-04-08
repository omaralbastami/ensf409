package edu.ucalgary.ensf409;

import java.sql.SQLException;
import java.util.*;

import edu.ucalgary.ensf409.Exceptions.*;

/**
 * The console application receives user input, processes inputs, then outputs
 * results
 */
public class ConsoleApp {
    private Inventory inventory;

    /**
     * Constructor will initialize Inventory object
     */
    public ConsoleApp() {
        this.inventory = new Inventory();
        this.inventory.initializeConnection();
    }

    /**
     * Triggers console application to retrieve user input and process it before
     * displaying
     */
    public void run() {
        Scanner input = new Scanner(System.in);
        try {
            System.out.print("Enter a furniture category (Chair/Lamp/Desk/Filing): ");
            String category = input.nextLine();
            category = category.toUpperCase().trim();

            if (isInvalidCategory(category)) {
                throw new InvalidCategoryException();
            }

            System.out.print("Enter furniture type: ");
            String type = input.nextLine();
            type = type.toUpperCase().trim();

            if (isInvalidType(category, type)) {
                throw new InvalidTypeException();
            }

            System.out.print("Enter number of items: ");
            int quantity = input.nextInt();

            if (isInvalidQuantity(quantity)) {
                throw new InvalidQuantityException();
            }

            Furniture[] furniture = getRequestedFurniture(category, type, quantity);
            if (furniture == null || furniture.length == 0) {
                Manufacturer[] manufacturers = getSuggestedManufacturers(category);
                printFailedOutput(manufacturers);
            } else {
                printInvoice(furniture);
            }
        } catch (InvalidCategoryException e) {
            System.err.println("You must select one of the following: Chair, Lamp, Desk, or Filing");
        } catch (InvalidTypeException e) {
            System.err.println("This type is currently unavailable");
        } catch (InvalidQuantityException e) {
            System.err.println("Please enter a valid quantity");
        } catch (InputMismatchException e) {
            System.err.println("Please enter a valid number");
        } catch (SQLException e) {
            System.err.println("An error occurred while reading furniture information from database");
        } catch (OrderFormException e) {
            System.err.println("An error occurred while saving your order into a text file");
        } catch (Exception e) {
            System.err.println("Something went wrong, please try again ...");
        } finally {
            input.close();
        }
    }

    /**
     * Retrieve suggested manufacturers for specified category
     * 
     * @param category Chair, Lamp, Desk, or Filing
     * @return array of manufacturers
     */
    private Manufacturer[] getSuggestedManufacturers(String category) throws SQLException {
        switch (category) {
        case "CHAIR":
            return inventory.getChairManufacturers();

        case "DESK":
            return inventory.getDeskManufacturers();

        case "FILING":
            return inventory.getFilingManufacturers();

        case "LAMP":
            return inventory.getLampManufacturers();

        default:
            return new Manufacturer[0];
        }
    }

    /**
     * Retrieve the cheapest furniture combination given user requirements
     * 
     * @param category Chair, Lamp, Desk, or Filing
     * @param type     type of furniture
     * @param quantity number of items
     * @return array of cheapest furnitures
     */
    private Furniture[] getRequestedFurniture(String category, String type, int quantity) throws SQLException, OrderFormException {
        switch (category) {
        case "CHAIR":
            Chair[] chairs = inventory.getChairs(type);
            return inventory.getCheapestCombination(chairs, quantity);

        case "DESK":
            Desk[] desks = inventory.getDesks(type);
            return inventory.getCheapestCombination(desks, quantity);

        case "FILING":
            Filing[] filings = inventory.getFilings(type);
            return inventory.getCheapestCombination(filings, quantity);

        case "LAMP":
            Lamp[] lamps = inventory.getLamps(type);
            return inventory.getCheapestCombination(lamps, quantity);

        default:
            return new Furniture[0];
        }
    }

    /**
     * Check if the provided category is a valid category
     * 
     * @param category category to be checked
     * @return true if Chair, Lamp, Desk, or Filing; false otherwise
     */
    private boolean isInvalidCategory(String category) {
        return !category.equals("CHAIR") && !category.equals("DESK") && !category.equals("LAMP")
                && !category.equals("FILING");
    }

    /**
     * Check if the provided type is valid for the given category
     * 
     * @param category category of furniture
     * @param type     type to be checked
     * @return true if valid type; false otherwise
     */
    private boolean isInvalidType(String category, String type) {

        switch (category) {
        case "CHAIR":
            return !type.equals("TASK") && !type.equals("MESH") && !type.equals("KNEELING") && !type.equals("EXECUTIVE")
                    && !type.equals("ERGONOMIC");

        case "DESK":
            return !type.equals("TRADITIONAL") && !type.equals("ADJUSTABLE") && !type.equals("STANDING");

        case "FILING":
            return !type.equals("SMALL") && !type.equals("MEDIUM") && !type.equals("LARGE");

        case "LAMP":
            return !type.equals("STUDY") && !type.equals("SWING ARM") && !type.equals("DESK");

        default:
            return true;
        }
    }

    /**
     * Check if the provided quantity is valid
     * 
     * @param quantity quantity to be checked
     * @return true if valid quantity; false otherwise
     */
    private boolean isInvalidQuantity(int quantity) {
        return quantity <= 0;
    }

    /**
     * Print out message to console saying that order could not be completed. Print
     * manufacturer suggestions
     * 
     * @param manufacturers suggested manufacturers
     */
    private void printFailedOutput(Manufacturer[] manufacturers) {
        System.out.print("Order cannot be fulfilled based on current inventory. ");
        if (manufacturers == null || manufacturers.length == 0) {
            System.out.print("There are no suggested manufacturers.");
        } else if (manufacturers.length == 1) {
            System.out.print("Suggested manufacturer is " + manufacturers[0].getName() + ".");
        } else if (manufacturers.length == 2) {
            System.out.print("Suggested manufacturers are " + manufacturers[0].getName() + " and "
                    + manufacturers[1].getName() + ".");
        } else {
            System.out.print("Suggested manufacturers are ");
            for (int i = 0; i < manufacturers.length; i++) {
                if (i != 0) {
                    System.out.print(", ");
                }
                if (i == manufacturers.length - 1) {
                    System.out.print("and ");
                }
                System.out.print(manufacturers[i].getName());
            }
            System.out.print(".");
        }
        System.out.println();
    }

    /**
     * Print out invoice for the cheapest furniture combinations
     * 
     * @param furnitures cheapest furniture combinations
     */
    private void printInvoice(Furniture[] furnitures) {
        System.out.print("Purchase ");
        if (furnitures.length == 1) {
            System.out.print(furnitures[0].getId());
        } else if (furnitures.length == 2) {
            System.out.print(furnitures[0].getId() + " and " + furnitures[1].getId());
        } else {
            for (int i = 0; i < furnitures.length; i++) {
                if (i != 0) {
                    System.out.print(", ");
                }
                if (i == furnitures.length - 1) {
                    System.out.print("and ");
                }
                System.out.print(furnitures[i].getId());
            }
        }
        System.out.print(" for $" + getTotalPrice(furnitures));
    }

    /**
     * Get the total price of provided furniture
     * 
     * @param furnitures furniture to aggregate price
     * @return total price
     */
    private int getTotalPrice(Furniture[] furnitures) {
        int price = 0;

        for (Furniture furniture : furnitures) {
            price += furniture.getPrice();
        }

        return price;
    }
}
