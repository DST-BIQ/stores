package storesgroup.model;

import storesgroup.Controller;
import storesgroup.View;

import java.sql.*;

public class Store {


    Controller controller = new Controller();
    View view = new View();





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



