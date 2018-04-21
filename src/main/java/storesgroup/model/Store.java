package storesgroup.model;

import storesgroup.Controller;

import java.sql.*;

public class Store {


    Controller controller = new Controller();
//    ChainAndMall chainAndMall = new ChainAndMall();


    public int getChainIDfromStore(int selectedValue) {
        try (Connection conn = controller.getConnectionToDB(); Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT chain from stores where id=" + selectedValue + ";")) {

            while (rs.next()) {


                return rs.getInt(1);
            }


        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
        }
        return -1;
    }

    public void viewAllStores() {

        try (Connection conn = controller.getConnectionToDB(); Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("Select id as ID,store_name as Name from stores;")
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


//    public void presentStoreDetails(int storeID) throws SQLException {
//
//        System.out.println("ID |  Name | chain | Fname | BirthDate | IsManager | Store");
//        try (Connection conn = controller.getConnectionToDB(); Statement stmt = conn.createStatement();
//             ResultSet rs = stmt.executeQuery("SELECT * FROM stores.stores WHERE id="+storeID+";")) {
//
//            while (rs.next()) {
//                System.out.print("ID : " + rs.getString(1) + ",");
//                System.out.println("name : " + rs.getString(2));
//                System.out.println("chain ID : " + rs.getString(2));
//                System.out.println("Store name : " + rs.getString(2));
//                System.out.println("Store name : " + rs.getString(2));
//                System.out.println("Store name : " + rs.getString(2));
//                System.out.println("Store name : " + rs.getString(2));
//
//
//            }
//        } catch (SQLException e) {
//            // TODO
//        }
//    }

    public void addStoreToChain(String storeName, int chainID) throws SQLException {
        try (Connection conn = controller.getConnectionToDB(); PreparedStatement stmt = conn.prepareStatement("insert into `stores`.`stores` (store_name,chain) values (?,?);");
        ) {
            stmt.setString(1, storeName);
            stmt.setInt(2, chainID);


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


    public int getStoreID(String storeName) {

        String sql = "Select id as ID,store_name as Name from stores where store_name = \"" + storeName + "\"";


        try (Connection conn = controller.getConnectionToDB(); Statement stmt = conn.createStatement();

             ResultSet rs = stmt.executeQuery(sql)
        ) {

            if (rs.next()) {
                return rs.getInt(1);

            }


        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
        }
        return -1;
    }



    public void presentAllDetailsOfAStore(int storeID) {

//        SELECT id,store_name,chain,cityID,StreetAddress,mallID FROM stores.stores;
        try (Connection conn = controller.getConnectionToDB(); PreparedStatement stmt = conn.prepareStatement("SELECT store_name,chain,cityID,StreetAddress,mallID FROM stores.stores WHERE id=?;")
        ) {


            stmt.setInt(1, storeID);

            try (ResultSet rs = stmt.executeQuery()) {
                System.out.println("details of :"+rs.getString(1));

                System.out.println(" Name | chain | city | street | mall ");

                while (rs.next()) {

                    System.out.print(rs.getString(1) + "|");
                    int chainID = rs.getInt(2);
                    System.out.println(controller.selectFromDatabase("chain", "idchain =" + chainID + "\"", "name") + "|");
                    int cityID = rs.getInt(3);
                    System.out.println(controller.selectFromDatabase("cities", "id =" + cityID + "\"", "name") + "|");
                    System.out.print(rs.getString(4) + "|");
                    int mallID = rs.getInt(5);
                    System.out.println(controller.selectFromDatabase("shoppingmalls", "id =" + mallID + "\"", "name") + "|");

                }

            }


        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
        }

    }
}




