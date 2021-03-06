package storesgroup.model;

import storesgroup.Controller;
import storesgroup.View;

import java.sql.*;

public class ChainAndMall {


    Controller controller;
    View view;
    Connection connection;

    public ChainAndMall(View view, Controller controller) throws SQLException {
        this.view = view;
        this.controller = controller;
        connection = controller.getConnectionToDB();
    }

    /**
     * create new chain of stores to the database.
     *
     * @param chainName - String icludes the chain nmae
     */
    public void createChain(String chainName) throws SQLException {
            PreparedStatement stmt = connection.prepareStatement("insert into chain (Name) values (?)");
            stmt.setString(1, chainName);
            int result = stmt.executeUpdate();
            if (result == 0) {
                view.printMessage("no updates were done");
                } else {
                view.printMessage("New chain was inserted to DB  " + chainName);
            }
    }

    public void viewAllChains() throws SQLException {

         Statement stmt = connection.createStatement();
         ResultSet rs = stmt.executeQuery("Select idchain as ID,name as Name from chain;");
         view.printMessage("Displayed chains  :  ");

            while (rs.next()) {
                view.printMessage("" + rs.getString(1)+ "\t" + rs.getString(2));
            }
    }

    public void viewAllMalls() {

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("Select id as ID,name as Name from shoppingmalls;");
            view.printMessage("Displayed malls  :  ");

            while (rs.next()) {
                view.printMessage("" + rs.getString(1) + "\t" + rs.getString(2));
            }
        }
            catch(SQLException e){
                view.printMessage("Sorry could not display list of malls, please contact DBA");
            }
    }
    public void viewAllMallGroups()  {

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("Select id as ID,name as Name from mallgroups;");
            view.printMessage("Displayed mall groups  :  ");

            while (rs.next()) {
                view.printMessage("" + rs.getString(1) + "\t" + rs.getString(2));
            }
        }catch (SQLException e){
            view.printMessage("Sorry could not display list of mall groups, please contact DBA");
        }
    }

    public int getChainID(String chainName)  {

        String sql = "Select idchain as ID,name as Name from chain where name = \"" + chainName + "\"";
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            view.printMessage("Displayed chains  :  ");
            if (rs.next()) {
                return rs.getInt(1);
            }

        }catch (SQLException e){
            view.printMessage("Wrong chain Name... please select different chain");
        }
            return -1;
    }

}



