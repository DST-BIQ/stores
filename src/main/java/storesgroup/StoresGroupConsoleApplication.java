package storesgroup;

import storesgroup.model.ChainAndMall;
import storesgroup.model.Employee;
import storesgroup.model.Store;

import java.sql.SQLException;

public class StoresGroupConsoleApplication {
    //TODO ALL - unit tests, documentation


    public static void main(String[] args) {


        try {
            consoleApplication();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void consoleApplication() throws SQLException {
        View view = new View();
        Controller controller = new Controller();
        ChainAndMall chainAndMall = new ChainAndMall();
        Employee employee = new Employee();
        Store store = new Store();
        int selection = 0;
        while (selection != 999) {
            view.printMenu();
            String valueForInput;
            int intValueForInput;
            selection = controller.selectFromScanner();
            switch (selection) {

                case 1:
                    // create new chain
                    printMessageToConsole("please enter a name for the new chain");
                    chainAndMall.createChain(controller.getStringFromScanner());
                    break;
                case 2:
// add store to chain
                    printMessageToConsole("please select the chain desired (type in the id displayed below):");
                    chainAndMall.viewAllChains();
                    intValueForInput = controller.selectFromScanner();
                    printMessageToConsole("enter store name");
                    valueForInput = controller.getStringFromScanner();
                    store.addStoreToChain(valueForInput, intValueForInput);

                    break;
                case 3:


// add employee to chain
                    printMessageToConsole("please select the chain desired (type in the id displayed below):");
                    chainAndMall.viewAllChains();
                    intValueForInput = controller.selectFromScanner();
                    printMessageToConsole("enter employee name");
                    valueForInput = controller.getStringFromScanner();
                    employee.addEmployeeToChain(valueForInput, intValueForInput, valueForInput, valueForInput);
//                    employee.addEmployee(valueForInput, true);
                    break;

                case 4:// add employee to store
                    printMessageToConsole("please select the store desired (type in the id displayed below):");
                    store.viewAllStores();
                    intValueForInput = controller.selectFromScanner();
                    printMessageToConsole("enter employee name");
                    valueForInput = controller.getStringFromScanner();
                    employee.addEmployeeToStore(valueForInput, intValueForInput, valueForInput, valueForInput);


                    break;
//DONE
                case 5:
// Present all details of a Shop
                    printMessageToConsole("Select a store ID to presnt it's details:   ");
                    store.viewAllStores();
                    intValueForInput = controller.selectFromScanner();
                    printMessageToConsole("Presenting all details of a shop:   " + intValueForInput);

                    store.presentAllDetailsOfAStore(intValueForInput);
                    break;
//TODO whay print is not successfull dorit
                case 6:

//Present all Employees of a certain Chain
                    printMessageToConsole("Please select a chain (ID) to display all of it's employees");
                    chainAndMall.viewAllChains();
                    intValueForInput = controller.selectFromScanner();
                    employee.presentAllEmployeesOfChain(intValueForInput);

//DONE
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

    public static void printMessageToConsole(String message) {
        System.out.println(message);
    }
}








