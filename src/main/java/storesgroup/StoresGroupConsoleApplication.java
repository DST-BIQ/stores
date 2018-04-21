package storesgroup;

import storesgroup.model.ChainAndMall;
import storesgroup.model.Employee;
import storesgroup.model.Store;

import java.sql.SQLException;

public class StoresGroupConsoleApplication {
    //TODO ALL - unit tests, documentation
    ChainAndMall chainAndMall;
    Employee employee;
    Store store;
    public static void main(String[] args) {

        StoresGroupConsoleApplication app = new StoresGroupConsoleApplication();

        try {
            app.consoleApplication();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public  void consoleApplication() throws SQLException {
        View view = new View();
        Controller controller = new Controller();
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

//TODO DORIT
                    break;
                case 2:
// add store to chain
                    view.printMessage("enter store name");
                    valueForInput = controller.getStringFromScanner();
                    store.addStoreToChain(valueForInput);
//DONE
                    break;
                case 3:
// add employee to chain
                    view.printMessage("enter employee first name");
                    String firstName = controller.getStringFromScanner();
                    view.printMessage("enter employee last name");
                    String lastName = controller.getStringFromScanner();
                    employee.addEmployee(firstName,lastName, true);
                    break;
//DONE
                case 4:
                    view.printMessage("enter employee first name");
                    firstName = controller.getStringFromScanner();
                    view.printMessage("enter employee last name");
                    lastName = controller.getStringFromScanner();
                    employee.addEmployee(firstName,lastName, false);
// add employee to store
                    break;
//DONE
                case 5:
// Present all shops of a mall
                    view.printMessage("Please enter a mall id from the list bellow:");
                    chainAndMall.viewAllMalls();
                    int mallId = controller.selectFromScanner();
                    store.presentStoresInMall(mallId);
//TODO DORIT
                    break;

                case 6:

//Present all Employees of a certain Chain
                    employee.presentAllEmployeesOfChain();
                    break;

                case 7:
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








