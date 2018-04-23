package storesgroup;

import storesgroup.model.ChainAndMall;
import storesgroup.model.Employee;
import storesgroup.model.Store;

import java.sql.SQLException;

public class StoresGroupConsoleApplication {

    ChainAndMall chainAndMall;
    Employee employee;
    Store store;
    View view;
    Controller controller;
    public static void main(String[] args) {

        StoresGroupConsoleApplication app = new StoresGroupConsoleApplication();

        try {
            app.consoleApplication();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public  void consoleApplication() throws SQLException {
        view = new View();
        controller = new Controller(view);
        chainAndMall = new ChainAndMall(view,controller);
        employee = new Employee(view,controller);
        store = new Store(view,controller);
        int selection = 0;
        while (selection != 999) {
            view.printMenu();
            String valueForInput;
            selection = controller.getIntFromScanner();
            switch (selection) {
                case 1:
                    // create new chain
                    createNewChain();
                    break;
                case 2:
// add store to chain
                    addStore();
                    break;
                case 3:
// add employee to chain
                    String firstName = getStringInput("enter employee first name");
                    String lastName = getStringInput("enter employee last name");
                    String fName = getStringInput("enter employee middle name");
                    employee.addEmployeeToChain(firstName, getChainIdFromUser(), lastName, fName );
                    break;

                case 4:
//Add employee to store
                    firstName = getStringInput("enter employee first name");
                    lastName = getStringInput("enter employee last name");
                    fName = getStringInput("enter employee middle name");
                    employee.addEmployeeToStore(firstName, getStoreIdFromUser(), lastName, fName);
                    break;

                case 5:
// Present all shops of a mall
                    store.presentStoresInMall(getMallIdFromUser());
                    break;

                case 6:
//Present all shops in mall group
                    store.presentStoreInMallGroup(getMallGroupIdFromUser());
                    break;

                case 7:
//Present all Employees of a certain Chain
                    employee.presentAllEmployeesOfChain(getChainIdFromUser());
                    break;

                case 8:
//Present store details
                    store.presentStoreDetails(getStoreIdFromUser());
                    break;

                default:
                    System.out.println("Thank you and good bye..");
                    return;
            }
        }
        controller.tearDown();

    }

    private int getMallGroupIdFromUser() throws SQLException {
        view.printMessage("Please enter requested mall group id from the list bellow");
        chainAndMall.viewAllMallGroups();
        return controller.getIntFromScanner();
    }

    private int getMallIdFromUser() throws SQLException {
        view.printMessage("Please enter a mall id from the list bellow:");
        chainAndMall.viewAllMalls();
        return controller.getIntFromScanner();
    }

    private int getStoreIdFromUser() throws SQLException {
        view.printMessage("enter store ID from the list bellow");
        store.viewAllStores();
        return controller.getIntFromScanner();
    }

    private int getChainIdFromUser() throws SQLException {
        view.printMessage("enter chain ID from the list bellow");
        chainAndMall.viewAllChains();
        return controller.getIntFromScanner();
    }

    private String getStringInput(String s) {
        view.printMessage(s);
        return controller.getStringFromReader();

    }

    private void createNewChain() throws SQLException {
        view.printMessage("please enter a name for the new chain");
        chainAndMall.createChain(controller.getStringFromReader());
    }

    private void addStore() throws SQLException {
        String storeName;
        view.printMessage("enter store name");
        storeName = controller.getStringFromReader();
        int storeId = getChainIdFromUser();
        store.addStoreToChain(storeName,storeId);
    }
}








