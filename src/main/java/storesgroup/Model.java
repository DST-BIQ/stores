package storesgroup;

import java.sql.*;

public class Model {


    Controller controller = new Controller();
    View view = new View();



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

                view.viewAllChains();
                System.out.println("Select a chain from the Available chains:  ");
                selectedValue = controller.selectFromScanner();
                stmt.setInt(5, selectedValue);

            } else {
                stmt.setBoolean(3, false);


                view.viewAllStores();
                System.out.println("Select a Store from the Available stores:  ");
                selectedValue = controller.selectFromScanner();
                stmt.setInt(4, selectedValue);

// set chain according to the store selected

                stmt.setInt(5, Integer.valueOf(getChainIDfromStore(selectedValue)));
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

    private String getChainIDfromStore(int selectedValue) {
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

    private String getGenerateEmployeeID(int count) {


        final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder builder = new StringBuilder();

        while (count-- != 0) {

            int character = (int) (Math.random() * ALPHA_NUMERIC_STRING.length());

            builder.append(ALPHA_NUMERIC_STRING.charAt(character));

        }

        return builder.toString();
    }





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



