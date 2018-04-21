package storesgroup.model;

import storesgroup.Controller;

import java.sql.*;

public class ChainAndMall {


    Controller controller = new Controller();


    public void createChain(String chainName) {
        try (Connection conn = controller.getConnectionToDB();
             PreparedStatement stmt = conn.prepareStatement("insert into chain (Name) values (?)")
        ) {
            stmt.setString(1, chainName);
            int result = stmt.executeUpdate();
            if (result == 0) {
                System.out.println("no updates were done");
            } else {
                System.out.println("number of inserted records:  " + result);
            }


        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
        }
    }

    public void viewAllChains() {

        try (Connection conn = controller.getConnectionToDB(); Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("Select idchain as ID,name as Name from chain;")
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
}



