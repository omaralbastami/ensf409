package edu.ucalgary.ensf409;

import java.io.*;
import edu.ucalgary.ensf409.Exceptions.*;

public class OrderForm {
    private Furniture[] furnitures;
    private String category;
    private int quantity;
    private StringBuilder orderFormString;
    private final String fileName = "orderform.txt";

    public OrderForm(Furniture[] furnitures, String category, int quantity) {
        this.furnitures = furnitures;
        this.category = category;
        this.quantity = quantity;
        this.orderFormString = new StringBuilder();
    }

    /**
     * Creates the order form file
     */
    public void createOrderForm() throws OrderFormException {
        if(this.furnitures == null || this.furnitures.length == 0)
            return;

        orderFormString.append("Furniture Order Form\n\nFaculty Name:\nContact:\nDate:\n\n")
                .append("Original Request: " + this.furnitures[0].getType() + " " + category + ", " + quantity + "\n\n")
                .append("Items Ordered\n");

        for (Furniture furniture : this.furnitures) {
            orderFormString.append("ID: " + furniture.getId() + "\n");
        }

        orderFormString.append("\n");
        orderFormString.append("Total Price: $" + getTotalPrice());
        writeFile();
    }

    /**
     * Attempts to write to the file in the orderform.txt format as shown in the
     * project document
     */
    private void writeFile() throws OrderFormException {
        BufferedWriter file = null;
        try {
            file = new BufferedWriter(new FileWriter(this.fileName));
            file.write(orderFormString.toString(), 0, orderFormString.length()); // writes the intro to the order form
        }

        catch (Exception e) {
            throw new OrderFormException();
        }

        finally {
            closeOrderForm(file); // closes the order form
        }
    }

    /**
     * closes the order form file and throws an error if it fails
     * 
     * @param file A BufferedWriter type to close
     */
    private void closeOrderForm(BufferedWriter file) throws OrderFormException {
        if (file != null) {
            try {
                file.close();
            }

            catch (Exception e) {
                throw new OrderFormException();
            }

        }
    }

    /**
     * Gets the total price of all the furniture items in the list
     * 
     * @return returns the total price
     */
    private int getTotalPrice() {
        int price = 0;
        for (int i = 0; i < this.furnitures.length; i++) {
            price += this.furnitures[i].getPrice();
        }
        return price;
    }
}
