package storesgroup.model;

import storesgroup.Controller;
import storesgroup.View;

import java.sql.*;

public class Store {


    Controller controller;
    ChainAndMall chainAndMall;
    View view;
    Connection connection;


    public Store(View view, Controller controller) throws SQLException {
        this.controller=controller;
        this.view=view;
        chainAndMall = new ChainAndMall(view,controller);
        connection = controller.getConnectionToDB();
    }

    public int getChainIDfromStore(int selectedValue) throws SQLException {
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT chainID from store where idStore=" + selectedValue);

            while (rs.next()) {

                return rs.getInt(1);
            }
        return 0;
    }

    public int getStoreID(String selectedValue) throws SQLException {
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT id from stores where store_name='" + selectedValue+"'");

        while (rs.next()) {

            return rs.getInt(1);
        }
        return -1;
    }

    public void viewAllStores() throws SQLException {

        Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("Select id as ID,store_name as Name from stores;");

            view.printMessage("Displayed Stores  :  ");

            while (rs.next()) {
                System.out.print("Store ID : " + rs.getString(1));
                System.out.println("   Store Name : " + rs.getString(2));
            }

    }

    /**
     * present all store details. input store ID
     * @param storeId - store ID
     * @throws SQLException
     */
    public void presentStoreDetails(int storeId) throws SQLException {


             PreparedStatement stmt = connection.prepareStatement("SELECT store_name AS 'Store Name', chain.name AS 'Chain', cities.name AS 'City', StreetAddress AS 'Street Address', shoppingmalls.name AS 'Mall', shoppingmalls.StreesAddress AS 'Mall Street Address' FROM stores\n" +
                     "LEFT JOIN chain ON stores.chain = chain.idchain\n" +
                     "LEFT JOIN cities ON stores.cityId = cities.id\n" +
                     "LEFT JOIN shoppingmalls ON stores.mallId = shoppingmalls.id\n" +
                     "WHERE stores.id=?" );
             stmt.setInt(1,storeId);
             ResultSet rs = stmt.executeQuery();
             if (!rs.next()){
                    view.printMessage("No store with the requested ID was found");
                    return;
                }else {

                 view.printRecord(rs);
                 view.printMessage("");
                }

    }


    /**
     * present all stores in a store group
     * @param mallGroupId
     * @throws SQLException
     */
    public void presentStoreInMallGroup(int mallGroupId) throws SQLException {


        PreparedStatement stmt = connection.prepareStatement("SELECT stores.id AS 'ID', store_name AS 'Store Name' FROM stores\n" +
                "LEFT JOIN shoppingmalls ON stores.mallId = shoppingmalls.id\n" +
                "WHERE shoppingmalls.mallGroupId=?" );
        stmt.setInt(1,mallGroupId);
        ResultSet rs = stmt.executeQuery();
        if (!rs.next()){
            view.printMessage("No store with the requested Mall GroupID was found");
            return;
        }else {
            view.printMessage("Found stores:");
            view.printRecord(rs);
            while (rs.next()) {
                view.printRecord(rs);
            }
            view.printMessage("");
        }

    }
    public void presentStoresInMall(int mallId) throws SQLException {


            PreparedStatement stmt = connection.prepareStatement("SELECT store_name AS 'Store', storeNumberInMall AS 'Store Number In Mall' FROM  stores WHERE mallId=? ");
            stmt.setInt(1,mallId);
            ResultSet rs = stmt.executeQuery();
            if (!rs.next()){
                view.printMessage("No stores in selected mall");
                return;
            }else{
                view.printMessage("Stores in selected mall are:");
                view.printRecord(rs);
            }

                while (rs.next()) {
                    view.printRecord(rs);
                }
                view.printMessage("");

    }



    public void addStoreToChain(String storeName, int storeId) throws SQLException {

        PreparedStatement stmt = connection.prepareStatement("insert into `stores` (store_name,chain) values (?,?);");
        stmt.setString(1, storeName);
        view.printMessage("Select a chain from the Available chains:  ");
        stmt.setInt(2, storeId);
        int result = stmt.executeUpdate();
        if (result == 0) {
          view.printMessage("no updates were done");
        } else {
          view.printMessage("Store was added successfully");
        }
     }
    }




