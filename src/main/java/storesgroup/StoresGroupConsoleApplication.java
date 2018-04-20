package storesgroup;

import storesgroup.model.ChainAndMall;
import storesgroup.model.Employee;
import storesgroup.model.Store;

import java.sql.SQLException;

public class StoresGroupConsoleApplication {
    //TODO ALL - unit tests, documentation
// TODO tal - upload DB scheme

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
            String valueForInput = null;
            selection = controller.selectFromScanner();
            switch (selection) {

                case 1:
                    // create new chain
                    printMessageToConsole("please enter a name for the new chain");
                    chainAndMall.createChain(controller.getStringFromScanner());

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

    public static void printMessageToConsole(String message) {
        System.out.println(message);
    }
}








