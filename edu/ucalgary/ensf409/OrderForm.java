package edu.ucalgary.ensf409;

import java.io.*;

public class OrderForm {
    private Furniture[] furnitures;
    private String category;
    private int quantity;
    private int totalPrice;
    private String fileName = "orderform.txt";
    private String orderIntro = "Furniture Order Form\n\nFaculty Name:\nContact:\nDate:\n\n";
    private String originalRequest = "Original Request: ";
    private String orderClosing = "\nTotal Price: $";
    private String[] itemsOrdered;

    public OrderForm(Furniture[] furnitures, String category, int quantity) {
        this.furnitures = furnitures;
        this.category = category;
        this.quantity = quantity;
    }

    /**
     * creates the order form file
     */
    public void createOrderForm() {
        String temp = this.originalRequest + this.furnitures[0].getType() + " " + category + ", " + quantity
                + "\n\nItems Ordered\n";
        this.originalRequest = temp; // completes the original request Line with all the needed components
        temp = this.orderClosing + getTotalPrice();
        this.orderClosing = temp; // completes the final line in the order form with the correct price
        writeFile();
    }

    /**
     * Attempts to write to the file in the orderform.txt format as shown in the
     * project document
     */
    private void writeFile() {
        BufferedWriter file = null;
        try {
            file = new BufferedWriter(new FileWriter(this.fileName));

            file.write(orderIntro, 0, orderIntro.length()); // writes the intro to the order form

            file.write(originalRequest, 0, originalRequest.length()); // writes the original request line as well as the
                                                                      // items ordered line to the order form

            /**
             * loops through the array of item IDs and writes them in the correct format
             */
            getItemIds();
            for (int i = 0; i < itemsOrdered.length; i++) {
                file.write("ID: " + itemsOrdered[i] + "\n", 0, 4 + itemsOrdered[i].length());
                file.newLine();
            }

            file.write(orderClosing, 0, orderClosing.length()); // writes the last line in the order form
        }

        catch (Exception e) {
            System.err.println("I/O error opening/writing file.");
            System.err.println(e.getMessage());
            closeOrderForm(file);
            System.exit(1);
        }

        closeOrderForm(file); // closes the order form
    }

    /**
     * closes the order form file and throws an error if it fails
     * 
     * @param file
     */
    private void closeOrderForm(BufferedWriter file) {
        if (file != null) {
            try {
                file.close();
            }

            catch (Exception e) {
                System.err.println("I/O error closing output file " + this.fileName + ".");
                System.err.println(e.toString());
                System.exit(1);
            }

        }
    }

    /**
     * get's the total price of all the furniture items in the list, and returns it
     * as a String value
     * 
     * @return
     */
    private String getTotalPrice() {
        for (int i = 0; i < this.furnitures.length; i++) {
            this.totalPrice += this.furnitures[i].getPrice();
        }
        return "" + this.totalPrice;
    }

    /**
     * fills our String array called itemsOrdered with their corresponding item IDs
     */
    private void getItemIds() {
        this.itemsOrdered = new String[furnitures.length];
        for (int i = 0; i < this.furnitures.length; i++) {
            this.itemsOrdered[i] = furnitures[i].getId();
        }
    }
}
