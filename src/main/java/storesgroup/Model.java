package storesgroup;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import java.sql.*;

public class Model {

    //    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
//    static final String DB_URL = "jdbc:mysql://localhost/";
    static final String DB_NAME = "excercise_biq";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "root";
    private Connection conn = null;
    private Statement stmt = null;
    private MysqlDataSource ds = new MysqlDataSource();
    Controller controller = new Controller();
    View view = new View();


//        /**
//         * creates new chain of stores
//         *
//         * @param chainName - string of the chain name
//         */
//        public void createChain(String chainName) {
//            try (Connection conn = controller.getConnectionToDB(); PreparedStatement stmt = conn.prepareStatement("INSERT INTO `excercise_biq`.`chain`   (Name) VALUES (?);")
//            ) {
//
//                stmt.setString(1, chainName);
//                int result = stmt.executeUpdate();
//                if (result == 0) {
//                    System.out.println("no updates were done");
//                } else {
//                    System.out.println("number of inserted records:  " + result);
//                }
//            } catch (SQLException e) {
//                // TODO
//            }
//
//        }

//
//        //TODO like employee
//        public void addStore(String name, int chainID, int shoppingMallID, int cityID, String streetAddress) {
//
////        INSERT INTO `excercise_biq`.`store` (`idStore`, `Name`, `groupID`, `ShoppingMallStore`, `shoppingMallID`) VALUES (?,?,?,?,?);
//
//            try (Connection conn = getConnectionToDB(); PreparedStatement stmt = conn.prepareStatement("INSERT INTO `excercise_biq`.`store` (`Name`, `chainID`, `shoppingMallID`, `cityID`,`StreetAddress`) VALUES (?,?,?,?,?);")
//            ) {
//
//                stmt.setString(1, name);
//                stmt.setInt(2, chainID);
//                stmt.setInt(3, shoppingMallID);
//                stmt.setInt(4, cityID);
//                stmt.setString(5, streetAddress);
//
//                int result = stmt.executeUpdate();
//                if (result == 0) {
//                    System.out.println("no updates were done");
//                } else {
//                    System.out.println("number of inserted records:  " + result);
//                }
//
//
//            } catch (SQLException e) {
//                System.out.println(e.getErrorCode());
//            }
//
//
//        }

//        public void updateStore(int idStore, int groupID) {
//// TODO update store in chain
////        UPDATE `excercise_biq`.`store` SET `groupID`='2' WHERE `idStore`='3' and`groupID`='1';
//
//            try (Connection conn = controller.getConnectionToDB(); PreparedStatement stmt = conn.prepareStatement("UPDATE `excercise_biq`.`store` `groupID`=? WHERE `idStore`=?;")
//            ) {
//
//                stmt.setInt(2, idStore);
//                stmt.setInt(1, groupID);
//
//
//                int result = stmt.executeUpdate();
//                if (result == 0) {
//                    System.out.println("no updates were done");
//                } else {
//                    System.out.println("number of inserted records:  " + result);
//                }
//
//
//            } catch (SQLException e) {
//                // TODO
//            }
//
//
//        }

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

//    public void updateEmployee(int ID, boolean isManagement) {
//        try (Connection conn = getConnectionToDB(); PreparedStatement stmt = conn.prepareStatement("UPDATE `excercise_biq`.`eployee` `isManagement`=? WHERE `ID`=?;")
//        ) {
//
//            stmt.setBoolean(1, isManagement);
//            stmt.setInt(1, ID);
//
//
//            int result = stmt.executeUpdate();
//            if (result == 0) {
//                System.out.println("no updates were done");
//            } else {
//                System.out.println("number of inserted records:  " + result);
//            }
//
//
//        } catch (SQLException e) {
//            // TODO
//        }
//
//    }

//        public void presentShopsInMall() {
//
////        SELECT name FROM excercise_biq.store WHERE ShoppingMallStore=1 ;
//            try (Connection conn = getConnectionToDB(); Statement stmt = conn.createStatement();
//                 ResultSet rs = stmt.executeQuery("SELECT name FROM excercise_biq.store WHERE ShoppingMallStore=1")) {
//
////           rs = stmt.executeQuery("SELECT version()");
//                while (rs.next()) {
//                    System.out.println("Store name : " + rs.getString(1));
//
//                }
//            } catch (SQLException e) {
//                // TODO
//            }
//
//        }
//
//        public void presentShopsInMallGroup() {
//
//        }
//
//        public void presentEmployeesInChain() {
//
//        }
//
//        public void presentShopDetails() {
//
//        }
//
//
//    }


