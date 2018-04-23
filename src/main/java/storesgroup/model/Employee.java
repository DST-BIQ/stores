package storesgroup.model;

import storesgroup.Controller;
import storesgroup.View;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Employee {

    Controller controller;
    View view;
    ChainAndMall chainAndMall;
    Store store;
    Connection connection;

    public Employee(View view, Controller controller) throws SQLException {
        this.view = view;
        this.controller = controller;
        this.store = new Store(view, controller);
        this.chainAndMall = new ChainAndMall(view, controller);
        connection = controller.getConnectionToDB();
    }

    /**
     * add employee to chain
     *
     * @param firstName - employee name
     * @param chainID   - selected chain ID
     */
    public void addEmployeeToChain(String firstName,
                                   int chainID, String lastName, String fname, int isManager) throws SQLException {
        int internalChainID = chainID;

        PreparedStatement stmt = connection.prepareStatement("insert into `employees` (first_name,isManager,ChainID,last_name,fname) values (?,?,?,?,?);");

        stmt.setString(1, firstName);
        stmt.setInt(2, isManager);
        stmt.setString(4, lastName);
        stmt.setString(5, fname);
        int result = 0;
        while (result == 0) {
            try {
                stmt.setInt(3, internalChainID);
                result = stmt.executeUpdate();
            } catch (SQLException e) {
                view.printMessage("Please type in chain ID again. you have probably inserted wrong value");
                chainAndMall.viewAllChains();
                internalChainID = controller.getIntFromScanner();


            }


        }

        if (result == 0) {
            System.out.println("no updates were done");
        } else {
            System.out.println("number of inserted records:  " + result);
        }
    }

    /**
     * add employee to store
     *
     * @param firstName - name
     * @param storeID   - id of the selected store
     */
    public void addEmployeeToStore(String firstName, int storeID, String lastName, String fname, int isManager) throws SQLException {

        int internalStoreID = storeID;
        PreparedStatement stmt = connection.prepareStatement("insert into `employees` (first_name,isManager,storeID,last_name,fname) values (?,?,?,?,?);");
        stmt.setString(1, firstName);
        stmt.setInt(2, isManager);

        stmt.setString(4, lastName);
        stmt.setString(5, fname);


        int result = 0;
        while (result == 0) {
            try {
                stmt.setInt(3, internalStoreID);
                result = stmt.executeUpdate();
            } catch (SQLException e) {
                view.printMessage("Please type in Store ID again. you have probably inserted wrong value");
                store.viewAllStores();
                internalStoreID = controller.getIntFromScanner();


            }


        }


        if (result == 0) {
            System.out.println("no updates were done");
        } else {
            System.out.println("number of inserted records:  " + result);
        }
    }

    public void presentAllEmployeesOfChain(int chainID) throws SQLException {

        int currentIDChain = chainID;
        ResultSet rs = null;
        boolean booleanResult = false;

        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT id,first_name,last_name,fname,dateofbirth,isManager,storeID FROM employees WHERE chainID=?;");

            while (!booleanResult) {
                stmt.setInt(1, currentIDChain);
                rs = stmt.executeQuery();
                if (!rs.next()) {
                    view.printMessage("Sorry no employees to the selected chain, please try a different one");
                    chainAndMall.viewAllChains();
                    currentIDChain = controller.getIntFromScanner();
                } else {
                    booleanResult = true;
                }
            }
            view.printMessage("Displayed Employees:");
            view.printMessage("\tID \t First Name \t LastName \t Fname \t BirthDate t\tIsManager ");

            while (rs.next()) {

                view.printMessage("\t" + rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t" + rs.getString(4) + "\t" + rs.getString(5) + "\t" + rs.getString(6) + "");
            }


        } catch (SQLException e) {
            view.printMessage("Sorry could not display list of malls, please contact DBA");

        }


    }
}



