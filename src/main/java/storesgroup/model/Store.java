package storesgroup.model;

import storesgroup.Controller;

import java.sql.*;

public class Store {


    Controller controller = new Controller();
    ChainAndMall chainAndMall = new ChainAndMall();





    public String getChainIDfromStore(int selectedValue) {
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

    public void viewAllStores() {

        try (Connection conn = controller.getConnectionToDB(); Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("Select idStore as ID,Name as Name from store;")
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






    public void presentStoreDetails(int storeId) throws SQLException {

        try (Connection conn = controller.getConnectionToDB(); Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT store_name,stores.chain, stores.cityId, stores.StreetAddress, stores.mallId, chain.name, cities.name, shoppingmalls.name  FROM stores, chain, cities, shoppingmalls WHERE stores.id=" + storeId + " AND " +
                         "stores.chain = chain.idchain AND stores.cityId = cities.id AND stores.mallId = shoppingmalls.id" )) {

                while (rs.next()) {
                    System.out.println("name : " + rs.getString(2));
                    System.out.println("chain : " + rs.getString(6));
                    System.out.println("City : " + rs.getString(7));
                    System.out.println("Street Address : " + rs.getString(4));
                    System.out.println("Mall : " + rs.getString(8));



                }
            } catch (SQLException e) {
            System.out.println("An error has occurred: "+e.getMessage());
            }
    }

    public void addStoreToChain(String storeName) throws SQLException {
        int selectedValue = 0;
        try (Connection conn = controller.getConnectionToDB();PreparedStatement stmt = conn.prepareStatement("insert into `stores`.`stores` (store_name,chain) values (?,?);");
        ) {
            stmt.setString(1, storeName);

                chainAndMall.viewAllChains();
                System.out.println("Select a chain from the Available chains:  ");
                selectedValue = controller.selectFromScanner();
                stmt.setInt(2, selectedValue);


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




