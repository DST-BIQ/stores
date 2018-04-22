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
        this.view =view;
        this.controller = controller;
        this.store = new Store(view,controller);
        this.chainAndMall = new ChainAndMall(view, controller);
        connection =  controller.getConnectionToDB();
    }

    /**
     * add employee to chain
     *
     * @param firstName - employee name
     * @param chainID   - selected chain ID
     */
    public void addEmployeeToChain(String firstName,
                                   int chainID, String lastName, String fname) throws SQLException {


             PreparedStatement stmt = connection.prepareStatement("insert into `employees` (first_name,isManager,chainID,last_name,fname) values (?,?,?,?,?);");

            stmt.setString(1, firstName);
            stmt.setBoolean(2, true);
            stmt.setInt(3, chainID);
            stmt.setString(4, lastName);
            stmt.setString(5, fname);
            int result = stmt.executeUpdate();
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
     * @param storeID      - id of the selected store
     */
    public void addEmployeeToStore(String firstName, int storeID, String lastName, String fname) throws SQLException {
            PreparedStatement stmt = connection.prepareStatement("insert into `employees` (first_name,isManager,storeID,last_name,fname) values (?,?,?,?,?);");
            stmt.setString(1, firstName);
            stmt.setBoolean(2, false);
            stmt.setInt(3, storeID);
            stmt.setString(4, lastName);
            stmt.setString(5, fname);
            int result = stmt.executeUpdate();
            if (result == 0) {
                System.out.println("no updates were done");
            } else {
                System.out.println("number of inserted records:  " + result);
            }
    }

    public void presentAllEmployeesOfChain(int chainID) throws SQLException {

        PreparedStatement stmt = connection.prepareStatement("SELECT id,first_name,last_name,fname,dateofbirth,isManager,storeID FROM employees WHERE chainID=?;");
        stmt.setInt(1, chainID);
        try (ResultSet rs = stmt.executeQuery()) {
              view.printMessage("Displayed Employees:");
              view.printMessage("\tID \t First Name \t LastName \t Fname \t BirthDate t\tIsManager ");

              while (rs.next()) {
                    //select id,first_name,last_name,fname,dateofbirth,isManager,storeID from employees where chainID=10025;
                    view.printMessage("\t"+rs.getInt(1)+"\t" + rs.getString(2)+"\t" + rs.getString(3)+"\t"+rs.getString(4)+"\t"+ rs.getString(5)+"\t"+rs.getString(6)+"");
                }
            }
    }
}



