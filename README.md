# ENSF 409 Final Project
This is the repository for ENSF 409 final project.

# Before Running
Before running the instructions, ensure you have an instance of MySQL running. Download the inventory.sql from D2L and run
the query to build the schema and tables. Also, make sure you have a valid MySQL java connector downloaded. mysql-connector-java-8.0.23.jar
can be downloaded from the course repository.

Create the following credentials for your database:
Username: scm
Password: ensf409

The repository also contains files for unit testing. To run these tests, you must download the required .jar files, which can be found
in the course repository (hamcrest-core-1.3, junit-4.13.2).

For any downloaded .jar files, add those into the project folder within a lib folder. Your folder structure should be as follows
~/edu/ucalgary/ensf409/<.java file>
~/lib/<.jar file>

# Instructions
1. Pull the code from this repository. If you do not have access, please request permission from the owner of this repository
2. In a command terminal, navigate to the project working directory (ex: cd C:/Users/john/finalProject/)
3. Once in the working directory, run the command: javac -cp .;lib/mysql-connector-java-8.0.23.jar edu/ucalgary/ensf409/SupplyChainManagement.java
4. Run the command: java -cp .;lib/mysql-connector-java-8.0.23.jar edu/ucalgary/ensf409/SupplyChainManagement
5. Follow instructions on terminal

# Unit Test Instructions
1. Pull the code from this repository. If you do not have access, please request permission from the owner of this repository
2. In a command terminal, navigate to the project working directory (ex: cd C:/Users/john/finalProject/)
3. Once in the working directory, run the command: javac -cp .;lib/junit-4.13.2.jar;lib/hamcrest-core-1.3.jar;lib/mysql-connector-java-8.0.23.jar edu/ucalgary/ensf409/SupplyChainManagementTest.java
4. Run the command: java -cp .;lib/junit-4.13.2.jar;lib/hamcrest-core-1.3.jar;lib/mysql-connector-java-8.0.23.jar org.junit.runner.JUnitCore edu.ucalgary.ensf409.SupplyChainManagementTest
