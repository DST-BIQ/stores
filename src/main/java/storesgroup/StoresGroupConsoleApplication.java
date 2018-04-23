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

    public void consoleApplication() throws SQLException {
        view = new View();
        controller = new Controller(view);
        chainAndMall = new ChainAndMall(view, controller);
        employee = new Employee(view, controller);
        store = new Store(view, controller);
        int selection = 0;
        while (selection != 999) {
            view.printMenu();
//            String valueForInput;
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
                    employee.addEmployeeToChain(firstName, getChainIdFromUser(), lastName, fName);
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

    private int getMallGroupIdFromUser(){
        view.printMessage("Please enter requested mall group id from the list bellow");
        chainAndMall.viewAllMallGroups();
        return controller.getIntFromScanner();
    }

    private int getMallIdFromUser()  {
         view.printMessage("Please enter a mall id from the list bellow:");
        chainAndMall.viewAllMalls();
        return controller.getIntFromScanner();
    }

    private int getStoreIdFromUser() {
        view.printMessage("enter store ID from the list bellow");
        store.viewAllStores();
        return controller.getIntFromScanner();
    }

    public int getChainIdFromUser() {

        try {
            chainAndMall.viewAllChains();
        } catch (SQLException e) {
            view.printMessage("Sorry could not view chain list. Please contact your DB Administrator.");
        }
        return controller.getIntFromScanner();
    }

    private String getStringInput(String s) {
        view.printMessage(s);
        return controller.getStringFromReader();

    }

    private void createNewChain() {
        view.printMessage("please enter a name for the new chain");
        try {
            chainAndMall.createChain(controller.getStringFromReader());
        } catch (SQLException e) {
            view.printMessage("Sorry could not enter new chain. Please contact your DB Administrator.");
        }
    }

    private void addStore() {
        String storeName;
        view.printMessage("enter store name");
        storeName = controller.getStringFromReader();
        view.printMessage("enter chain ID from the list bellow");

        boolean addedStore = false;
        while (!addedStore) {
            try {
                int chainId = getChainIdFromUser();
                addedStore = store.addStoreToChain(storeName, chainId);


            } catch (SQLException e) {
                view.printMessage("You have chosen wrong chain ID, please try a valid ID from the list displayed above");
            }
        }

    }

}









