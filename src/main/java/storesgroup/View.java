package storesgroup;

public class View {

    public View() {


    }


    public static void printMenu() {

        System.out.println("Please select one of the options:");
        System.out.println("1.Create new chain");
        System.out.println("2. Add store to chain");
        System.out.println("3. Add employee to chain");  // exist employee type name if exists and not chain update, if not exists insert
        System.out.println("4. Add employee to Store");  // exist employee type name if exists and not chain update, if not exists insert
        System.out.println("5. Present all details of a store");
        System.out.println("6. Present all Employees of a certain Chain");

        System.out.println("999. exit");


    }




    private static void printMessageToConsole(String message) {
        System.out.println(message);
    }
}



