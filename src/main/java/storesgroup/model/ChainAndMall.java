package storesgroup.model;

import storesgroup.Controller;
import storesgroup.View;

import java.sql.*;

public class ChainAndMall {


    Controller controller;
    View view;
    Connection conn;

    public ChainAndMall(View view, Controller controller) throws SQLException {
        this.view = view;
        this.controller = controller;
        conn = controller.getConnectionToDB();
    }

    /**
     * create new chain of stores to the database.
     * @param chainName - String icludes the chain nmae
     */
    public void createChain(String chainName) throws SQLException {
        View view = new View();

            PreparedStatement stmt = conn.prepareStatement("insert into chain (Name) values (?)");
            stmt.setString(1, chainName);
            int result = stmt.executeUpdate();
            if (result == 0) {
                view.printMessage("no updates were done");
                } else {
                view.printMessage("New chain was inserted to DB  " + chainName);
            }
    }

    public void viewAllChains() {

        try (Connection conn = controller.getConnectionToDB(); Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("Select idchain as ID,name as Name from chain;")
        ) {
            System.out.println("Displayed chains  :  ");

            while (rs.next()) {
                view.printMessage("" + rs.getString(1)+ "\t" + rs.getString(2));
            }


        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
        }
    }

    public void viewAllMalls() throws SQLException {

         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery("Select id as ID,name as Name from shoppingmalls;");
         view.printMessage("Displayed malls  :  ");

            while (rs.next()) {
                view.printMessage("" + rs.getString(1)+ "\t" + rs.getString(2));
            }
    }
}



