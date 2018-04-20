package storesgroup.model;

import storesgroup.Controller;

import java.sql.*;

import static storesgroup.StoresGroupConsoleApplication.printMessageToConsole;

public class ChainAndMall {


    Controller controller = new Controller();

    /**
     * create new chain of stores to the database.
     * @param chainName - String icludes the chain nmae
     */
    public void createChain(String chainName) {
        try (Connection conn = controller.getConnectionToDB();
             PreparedStatement stmt = conn.prepareStatement("insert into `excercise_biq`.`chain` (Name) values (?);");
        ) {
            stmt.setString(1, chainName);
            int result = stmt.executeUpdate();
            if (result == 0) {
                printMessageToConsole("no updates were done");
                } else {
                printMessageToConsole("New chain was inserted to DB  " + chainName);
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
}



