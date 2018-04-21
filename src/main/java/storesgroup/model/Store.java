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

    public String getChainIDfromStore(int selectedValue) throws SQLException {
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT chainID from store where idStore=" + selectedValue);

            while (rs.next()) {

                return rs.getString(1);
            }
        return null;
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

                 view.printRecord(rs, this);
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
                view.printRecord(rs, this);
            }

                while (rs.next()) {
                    view.printRecord(rs, this);
                }
                view.printMessage("");

    }

    public void addStoreToChain(String storeName)  {
        int selectedValue = 0;
        try (Connection conn = controller.getConnectionToDB();
             PreparedStatement stmt = conn.prepareStatement("insert into `stores` (store_name,chain) values (?,?);")
        ) {
            stmt.setString(1, storeName);

                chainAndMall.viewAllChains();
                view.printMessage("Select a chain from the Available chains:  ");
                selectedValue = controller.selectFromScanner();
                stmt.setInt(2, selectedValue);
            int result = stmt.executeUpdate();
            if (result == 0) {
                view.printMessage("no updates were done");
            } else {
                view.printMessage("Store was added successfully");
            }


        } catch (SQLException e) {
            view.printMessage(""+e.getErrorCode());
        }

     }
    }




