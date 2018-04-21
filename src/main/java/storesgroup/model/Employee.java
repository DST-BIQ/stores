package storesgroup.model;

import storesgroup.Controller;
import storesgroup.View;

import java.sql.*;

public class Employee {

    private Controller controller;
    private View view;
    Connection connection;

    private ChainAndMall chainAndMall;
    private Store store;

    public Employee(View view, Controller controller) throws SQLException {
        this.view = view;
        this.controller=controller;
        chainAndMall = new ChainAndMall(view, controller);
        store = new Store(view,controller);
        connection = controller.getConnectionToDB();
    }

    /**
     * add employee to chain or to store
     *
     * @param employeeFirstName
     * @param toChain      - true - to chain, false = to store
     */
    public void addEmployee(String employeeFirstName, String employeeLastName, boolean toChain) throws SQLException {

        int selectedValue = 0;

             PreparedStatement stmt = connection.prepareStatement("insert into employees (first_name,last_name,isManager,storeID,chainID) values (?,?,?,?,?)");

            stmt.setString(1, employeeFirstName);
            stmt.setString(2,employeeLastName);
//            stmt.setString(1, getGenerateEmployeeID(5));


            if (toChain) {
                stmt.setBoolean(3, true);
                stmt.setNull(4, Types.INTEGER);

                chainAndMall.viewAllChains();
                System.out.println("Select a chain from the Available chains:  ");
                selectedValue = controller.selectFromScanner();
                stmt.setInt(5, selectedValue);

            } else {
                stmt.setBoolean(3, false);


                store.viewAllStores();
                System.out.println("Select a Store from the Available stores:  ");
                selectedValue = controller.selectFromScanner();
                stmt.setInt(4, selectedValue);

// set chain according to the store selected

                stmt.setNull(5, Types.INTEGER);
            }


            int result = stmt.executeUpdate();
            if (result == 0) {
                System.out.println("no updates were done");
            } else {
                System.out.println("number of inserted records:  " + result);
            }
    }


//    private String getGenerateEmployeeID(int count) {
//
//
//        final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
//        StringBuilder builder = new StringBuilder();
//
//        while (count-- != 0) {
//
//            int character = (int) (Math.random() * ALPHA_NUMERIC_STRING.length());
//
//            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
//
//        }
//
//        return builder.toString();
//    }


    public void presentAllEmployeesOfChain() throws SQLException {
        int selectedValue = 0;
            PreparedStatement stmt = connection.prepareStatement("SELECT first_name,last_name FROM employees WHERE chainID=?");
            chainAndMall.viewAllChains();
            System.out.println("Select a chain from the Available chains:  ");
            selectedValue = controller.selectFromScanner();
            stmt.setInt(1, selectedValue);
                ResultSet rs = stmt.executeQuery();
                if (!rs.next()){
                    view.printMessage("No employees were found");
                    return;
                }else{
                    view.printMessage("Displayed Employees  :  ");
                    view.printMessage(rs.getString(1)+" " + rs.getString(2));
                }

                    while (rs.next()) {
                        view.printMessage(rs.getString(1)+" " + rs.getString(2));
                    }
    }
}



