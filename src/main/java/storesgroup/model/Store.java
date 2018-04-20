package storesgroup.model;

import storesgroup.Controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Store {


    Controller controller = new Controller();






    public String getChainIDfromStore(int selectedValue) {
        try (Connection conn = controller.getConnectionToDB(); Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT chainID from store where idStore=" + selectedValue + ";")) {

            while (rs.next()) {

                return rs.getString(1);
            }


        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
        }
        return null;
    }

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






    public void presentStoreDetails(String storeName) throws SQLException {

        try (Connection conn = controller.getConnectionToDB(); Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT name FROM excercise_biq.store WHERE ShoppingMallStore=1")) {

                while (rs.next()) {
                    System.out.print("ID : " + rs.getString(1)+",");
                    System.out.println("name : " + rs.getString(2));
                    System.out.println("chain ID : " + rs.getString(2));
                    System.out.println("Store name : " + rs.getString(2));
                    System.out.println("Store name : " + rs.getString(2));
                    System.out.println("Store name : " + rs.getString(2));
                    System.out.println("Store name : " + rs.getString(2));


                }
            } catch (SQLException e) {
                // TODO
            }
    }

}



