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
            selection = controller.selectFromScanner();
            switch (selection) {

                case 1:
                    // create new chain
                    view.printMessage("please enter a name for the new chain");
                    chainAndMall.createChain(controller.getStringFromScanner());
                    break;
                case 2:
// add store to chain
                    view.printMessage("enter store name");
                    valueForInput = controller.getStringFromScanner();
                    view.printMessage("Select chain id from list bellow");
                    chainAndMall.viewAllChains();
                    int storeId = controller.selectFromScanner();
                    store.addStoreToChain(valueForInput,storeId);
                    break;
                case 3:
// add employee to chain
                    view.printMessage("enter employee first name");
                    String firstName = controller.getStringFromScanner();
                    view.printMessage("enter employee last name");
                    String lastName = controller.getStringFromScanner();
                    view.printMessage("enter employee middle name");
                    String fName = controller.getStringFromScanner();
                    view.printMessage("enter chain ID from the list bellow");
                    chainAndMall.viewAllChains();
                    int chainId = controller.selectFromScanner();
                    employee.addEmployeeToChain(firstName, chainId, lastName, fName );
                    break;

                case 4:
//Add employee to store
                    view.printMessage("enter employee first name");
                    firstName = controller.getStringFromScanner();
                    view.printMessage("enter employee last name");
                    lastName = controller.getStringFromScanner();
                    view.printMessage("enter employee middle name");
                    fName = controller.getStringFromScanner();
                    view.printMessage("enter store ID from the list bellow");
                    store.viewAllStores();
                    storeId = controller.selectFromScanner();
                    employee.addEmployeeToStore(firstName, storeId, lastName, fName);
                    break;

                case 5:
// Present all shops of a mall
                    view.printMessage("Please enter a mall id from the list bellow:");
                    chainAndMall.viewAllMalls();
                    int mallId = controller.selectFromScanner();
                    store.presentStoresInMall(mallId);
                    break;

                case 6:
//Present all shops in mall group
                    view.printMessage("Please enter requested mall group id from the list bellow");
                    chainAndMall.viewAllMallGroups();
                    int mallGroupId = controller.selectFromScanner();
                    store.presentStoreInMallGroup(mallGroupId);
                    break;

                case 7:
//Present all Employees of a certain Chain
                    view.printMessage("Please enter a chain id from the list bellow:");
                    chainAndMall.viewAllChains();
                    chainId = controller.selectFromScanner();
                    employee.presentAllEmployeesOfChain(chainId);
                    break;

                case 8:
//Present store details
                    view.printMessage("Please enter store ID");
                    store.presentStoreDetails(controller.selectFromScanner());
                    break;

                default:
                    System.out.println("Thank you and good bye..");
                    return;

            }

        }
        controller.tearDown();

    }
}








