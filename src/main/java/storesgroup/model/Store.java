package storesgroup.model;

import storesgroup.Controller;

import java.sql.*;

public class Store {


    Controller controller = new Controller();
    ChainAndMall chainAndMall = new ChainAndMall();





    public int getChainIDfromStore(int selectedValue) {
        try (Connection conn = controller.getConnectionToDB(); Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT chain from stores where id=" + selectedValue + ";")) {

            while (rs.next()) {

//                return rs.getString(1);
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






    public void presentStoreDetails(String storeName) throws SQLException {

        try (Connection conn = controller.getConnectionToDB(); Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT name FROM store.stores WHERE ShoppingMallStore=1")) {

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

    public void addStoreToChain(String storeName,int chainID) throws SQLException {
        try (Connection conn = controller.getConnectionToDB();PreparedStatement stmt = conn.prepareStatement("insert into `stores`.`stores` (store_name,chain) values (?,?);");
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
    }




