package storesgroup;

import java.sql.SQLException;

public class StoresGroupConsoleApplication {
//TODO ALL - unit tests, documentation
// TODO tal - upload DB scheme
    public static void main(String[] args) {

        try {
            menu();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }







    public static void menu() throws SQLException {
        View view = new View();
        Model model = new Model();
        Controller controller = new Controller();



        int selection = 0;
        while (selection != 999) {
            view.printMenu();
            String valueForInput = null;
            selection =  controller.selectFromScanner();
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
                    model.addEmployee(valueForInput, true);
                    break;
//DONE
                case 4:
                    printMessageToConsole("enter employee name");
                    valueForInput = controller.getStringFromScanner();
                    model.addEmployee(valueForInput, false);
// add employee to store
                    break;
//DONE
                case 5:
// Present all details of a Shop
                    valueForInput = controller.getStringFromScanner();
                    printMessageToConsole("Presenting all details of a shop:   "+valueForInput);
                    model.presentStoreDetails(valueForInput);
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
