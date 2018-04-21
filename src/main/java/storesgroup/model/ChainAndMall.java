package storesgroup.model;

import storesgroup.Controller;
import storesgroup.View;

import java.sql.*;

public class ChainAndMall {


    Controller controller = new Controller();

    /**
     * create new chain of stores to the database.
     * @param chainName - String icludes the chain nmae
     */
    public void createChain(String chainName) {
        View view = new View();
        try (Connection conn = controller.getConnectionToDB();
             PreparedStatement stmt = conn.prepareStatement("insert into chain (Name) values (?)")
        ) {
            stmt.setString(1, chainName);
            int result = stmt.executeUpdate();
            if (result == 0) {
                view.printMessageToConsole("no updates were done");
                } else {
                view.printMessageToConsole("New chain was inserted to DB  " + chainName);
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



