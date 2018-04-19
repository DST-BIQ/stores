package storesgroup.model;

import storesgroup.Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Employee {

    Controller controller = new Controller();

    ChainAndMall chainAndMall = new ChainAndMall();
    Store store = new Store();

    /**
     * add employee to chain or to store
     *
     * @param employeeName
     * @param toChain      - true - to chain, false = to store
     */
    public void addEmployee(String employeeName, boolean toChain) {

        int selectedValue = 0;
        try (Connection conn = controller.getConnectionToDB();
             PreparedStatement stmt = conn.prepareStatement("insert into `excercise_biq`.`employee` (ID,Name,isManagement,storeID,chainID) values (?,?,?,?,?);");
        ) {
            stmt.setString(2, employeeName);
            stmt.setString(1, getGenerateEmployeeID(5));


            if (toChain) {
                stmt.setBoolean(3, true);
                stmt.setInt(4, 99);

                chainAndMall.viewAllChains();
                System.out.println("Select a chain from the Available chains:  ");
                selectedValue = controller.selectFromScanner();
                stmt.setInt(5, selectedValue);

            } else {
                stmt.setBoolean(3, false);


                store.viewAllStores();
                System.out.println("Select a Store from the Available stores:  ");
                selectedValue = controller.selectFromScanner();
                stmt.setInt(4, selectedValue);

// set chain according to the store selected

                stmt.setInt(5, Integer.valueOf(store.getChainIDfromStore(selectedValue)));
            }


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


    private String getGenerateEmployeeID(int count) {


        final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder builder = new StringBuilder();

        while (count-- != 0) {

            int character = (int) (Math.random() * ALPHA_NUMERIC_STRING.length());

            builder.append(ALPHA_NUMERIC_STRING.charAt(character));

        }

        return builder.toString();
    }


}



