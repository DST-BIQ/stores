package storesgroup;

import storesgroup.model.ChainAndMall;
import storesgroup.model.Employee;
import storesgroup.model.Store;

public class View {
    //
    ChainAndMall chainAndMall = new ChainAndMall();
    Employee employee = new Employee();
    Controller controller = new Controller();
    Store store;

    public View() {


    }


    public static void printMenu() {

        System.out.println("Please select one of the options:");
        System.out.println("1.Create new chain");
        System.out.println("2. Add store to chain");
        System.out.println("3. Add employee to chain");  // exist employee type name if exists and not chain update, if not exists insert
        System.out.println("4. Add employee to Store");  // exist employee type name if exists and not chain update, if not exists insert
        System.out.println("999. exit");


    }




    private static void printMessageToConsole(String message) {
        System.out.println(message);
    }
}



