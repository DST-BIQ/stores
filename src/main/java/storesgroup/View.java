package storesgroup;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class View {

    Controller controller = new Controller();
//    private Statement stmt = null;

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
}
