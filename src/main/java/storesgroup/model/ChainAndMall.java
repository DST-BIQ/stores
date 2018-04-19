package storesgroup.model;

import storesgroup.Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ChainAndMall {


    Controller controller = new Controller();


    public void createChain(String chainName) {
        try (Connection conn = controller.getConnectionToDB();
             PreparedStatement stmt = conn.prepareStatement("insert into `excercise_biq`.`chain` (Name) values (?);");
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


}



