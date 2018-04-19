package storesgroup;

import storesgroup.model.ChainAndMall;
import storesgroup.model.Employee;
import storesgroup.model.Store;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class View {

    View view;
    ChainAndMall model;
    Employee employee;
    Controller controller;
    Store store;

    public View() {

        view = new View();
        model = new ChainAndMall();
        employee = new Employee();
        controller = new Controller();
        store = new Store();
    }

    public void viewAllStores() {

        try (Connection conn = controller.getConnectionToDB(); Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("Select idStore as ID,Name as Name from store;")
        ) {
            System.out.println("Displayed Stores  :  ");

            while (rs.next()) {
                System.out.print("Store ID : " + rs.getString(1));
                System.out.println("   Store Name : " + rs.getString(2));

            }


        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
        }


    }


    public void viewAllChains() {

        try (Connection conn = controller.getConnectionToDB(); Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("Select idChain as ID,Name as Name from chain;")
        ) {
            System.out.println("Displayed chains  :  ");

            while (rs.next()) {
                System.out.print("Chain ID : " + rs.getString(1));
                System.out.println("   Chain Name : " + rs.getString(2));

            }


        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
        }
    }


    public static void printMenu() {

        System.out.println("Please select one of the options:");
        System.out.println("1.Create new chain");
        System.out.println("2. Add store to chain");
        System.out.println("3. Add employee to chain");  // exist employee type name if exists and not chain update, if not exists insert
        System.out.println("4. Add employee to Store");  // exist employee type name if exists and not chain update, if not exists insert
        System.out.println("999. exit");


    }


    public void consoleApplication() throws SQLException {


        int selection = 0;
        while (selection != 999) {
            view.printMenu();
            String valueForInput = null;
            selection = controller.selectFromScanner();
            switch (selection) {

                case 1:
                    // create new chain
                    printMessageToConsole("please enter a name for the new chain");
                    model.createChain(controller.getStringFromScanner());
//TODO DORIT
                    break;
                case 2:
// add store to chain
                    printMessageToConsole("enter store name");
                    valueForInput = controller.getStringFromScanner();
//TODO STAV
                    break;
                case 3:
// add employee to chain
                    printMessageToConsole("enter employee name");
                    valueForInput = controller.getStringFromScanner();
                    employee.addEmployee(valueForInput, true);
                    break;
//DONE
                case 4:
                    printMessageToConsole("enter employee name");
                    valueForInput = controller.getStringFromScanner();
                    employee.addEmployee(valueForInput, false);
// add employee to store
                    break;
//DONE
                case 5:
// Present all details of a Shop
                    valueForInput = controller.getStringFromScanner();
                    printMessageToConsole("Presenting all details of a shop:   " + valueForInput);

                    store.presentStoreDetails(valueForInput);
//TODO DORIT
                    break;

                case 6:
                    //TODO STAV
                    break;

                case 7:
                    //TODO TAL
                    break;

                case 999:
                    //TODO TAL

            }

        }
        controller.tearDown();

    }

    private static void printMessageToConsole(String message) {
        System.out.println(message);
    }
}

