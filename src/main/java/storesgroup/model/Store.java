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
        this.controller = controller;
        this.view = view;
        chainAndMall = new ChainAndMall(view, controller);
        connection = controller.getConnectionToDB();
    }

    public void viewAllStores() {

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("Select id as ID,store_name as Name from stores;");

            view.printMessage("Displayed Stores  :  ");
            while (rs.next()) {
                System.out.print("Store ID : " + rs.getString(1));
                System.out.println("   Store Name : " + rs.getString(2));
            }
        } catch (SQLException e) {
            view.printMessage("Sorry could not view stores list, please contact your DB Administrator");
        }


    }

    /**
     * present all store details. input store ID
     *
     * @param storeId - store ID
     * @throws SQLException
     */
    public void presentStoreDetails(int storeId) {
        boolean booleanResult = false;
        ResultSet rs=null;
        int currentStoreID = storeId;
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT store_name AS 'Store Name', chain.name AS 'Chain', cities.name AS 'City', StreetAddress AS 'Street Address', shoppingmalls.name AS 'Mall', shoppingmalls.StreesAddress AS 'Mall Street Address' FROM stores\n" +
                    "LEFT JOIN chain ON stores.chain = chain.idchain\n" +
                    "LEFT JOIN cities ON stores.cityId = cities.id\n" +
                    "LEFT JOIN shoppingmalls ON stores.mallId = shoppingmalls.id\n" +
                    "WHERE stores.id=?");


            while(!booleanResult){
                stmt.setInt(1, currentStoreID);
                rs = stmt.executeQuery();
                if (!rs.next()) {
                    view.printMessage("No store with the requested ID was found, please try again");
                        viewAllStores();
                        currentStoreID = controller.getIntFromScanner();

                } else {
                    booleanResult=true;
                    view.printRecord(rs);
                    view.printMessage("");
                }

            }

        } catch (SQLException e) {
            view.printMessage("Sorry could not view store details, please contact your DB Administrator");
        }

    }


    /**
     * present all stores in a store group
     *
     * @param mallGroupId
     * @throws SQLException
     */
    public void presentStoreInMallGroup(int mallGroupId) throws SQLException {

        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT stores.id AS 'ID', store_name AS 'Store Name' FROM stores\n" +
                    "LEFT JOIN shoppingmalls ON stores.mallId = shoppingmalls.id\n" +
                    "WHERE shoppingmalls.mallGroupId=?");
            stmt.setInt(1, mallGroupId);
            ResultSet rs = stmt.executeQuery();
            if (!rs.next()) {
                view.printMessage("No store with the requested Mall GroupID was found");
                return;
            } else {
                view.printMessage("Found stores:");
                view.printRecord(rs);
                while (rs.next()) {
                    view.printRecord(rs);
                }
                view.printMessage("");
            }
        } catch (SQLException e) {
            view.printMessage("Sorry could not view stores in mall group , please contact your DB Administrator");
        }

    }

    public void presentStoresInMall(int mallId) throws SQLException {

        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT store_name AS 'Store', storeNumberInMall AS 'Store Number In Mall' FROM  stores WHERE mallId=? ");
            stmt.setInt(1, mallId);
            ResultSet rs = stmt.executeQuery();

            if (!rs.next()) {
                view.printMessage("No stores in selected mall");
                return;
            } else {
                view.printMessage("Stores in selected mall are:");
                view.printRecord(rs);
            }

            while (rs.next()) {
                view.printRecord(rs);
            }
            view.printMessage("");

        } catch (SQLException e) {
            view.printMessage("Sorry could not view stores in mall , please contact your DB Administrator");
        }

    }


    public boolean addStoreToChain(String storeName, int chainId, int cityId, int mallId, int storenumInMall) throws SQLException {


        PreparedStatement stmt = connection.prepareStatement("insert into `stores` (store_name,chain,cityId,mallId,storeNumberInMall) values (?,?,?,?,?);");
        stmt.setString(1, storeName);
        stmt.setInt(2, chainId);
        stmt.setInt(3, cityId);
        stmt.setInt(4, mallId);
        stmt.setInt(5, storenumInMall);


        int result = stmt.executeUpdate();
        if (result == 0) {
            view.printMessage("no updates were done");
            return false;
        } else {
            view.printMessage("Store was added successfully");
        }
        return true;
    }




    public boolean addStoreToChain(String storeName,int chainIdFromUser, int cityId, String stotrAddress) throws SQLException {

        PreparedStatement stmt = connection.prepareStatement("insert into `stores` (store_name,chain,cityId,streetAddress) values (?,?,?,?);");
        stmt.setString(1, storeName);
        stmt.setInt(2, chainIdFromUser);
        stmt.setInt(3, cityId);
        stmt.setString(4, stotrAddress);



        int result = stmt.executeUpdate();
        if (result == 0) {
            view.printMessage("no updates were done");
            return false;
        } else {
            view.printMessage("Store was added successfully");
        }
        return true;
    }


    public void viewAllCityIDs () throws SQLException {
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("Select id as ID,name as Name from cities;");
        view.printMessage("Displayed mall cities  :  ");

        while (rs.next()) {
            view.printMessage("" + rs.getString(1)+ "\t" + rs.getString(2));
        }
    }

}




