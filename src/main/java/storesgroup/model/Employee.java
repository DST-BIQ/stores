package storesgroup.model;

import storesgroup.Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

public class Employee {

    Controller controller = new Controller();

    ChainAndMall chainAndMall = new ChainAndMall();
    Store store = new Store();

    /**
     * add employee to chain
     *
     * @param firstName - employee name
     * @param chainID   - selected chain ID
     */
    public void addEmployeeToChain(String firstName,
                                   int chainID, String lastName, String fname) {

        try (Connection conn = controller.getConnectionToDB();
             PreparedStatement stmt = conn.prepareStatement("insert into `stores`.`employees` (id,first_name,isManager,chainID,last_name,fname) values (?,?,?,?,?,?);")
        ) {
            stmt.setInt(1, getGenerateEmployeeID(5));
            stmt.setString(2, firstName);
            stmt.setBoolean(3, true);
            stmt.setString(5, lastName);
            stmt.setString(6, fname);



            stmt.setInt(4, chainID);
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

    /**
     * add employee to store
     *
     * @param employeeName - name
     * @param storeID      - id of the selected store
     */
    public void addEmployeeToStore(String employeeName, int storeID, String lastName, String fname) {


        try (Connection conn = controller.getConnectionToDB();
             PreparedStatement stmt = conn.prepareStatement("insert into `stores`.`employees` (id,first_name,isManager,storeID,chainID,last_name,fname) values (?,?,?,?,?,?,?);")
        ) {
            stmt.setString(2, employeeName);
            stmt.setInt(1, getGenerateEmployeeID(5));
            stmt.setBoolean(3, false);
            stmt.setInt(4, storeID);
            stmt.setInt(5, store.getChainIDfromStore(storeID));
            stmt.setString(6, lastName);
            stmt.setString(7, fname);
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

//    /**
//     * add employee to chain or to store
//     *
//     * @param employeeName
//     * @param toChain      - true - to chain, false = to store
//     */
//    public void addEmployee(String employeeName, boolean toChain) {
//
//        int selectedValue;
//        try (Connection conn = controller.getConnectionToDB();
//             PreparedStatement stmt = conn.prepareStatement("insert into `stores`.`employees` (id,first_name,isManager,storeID,chainID) values (?,?,?,?,?);");
//        ) {
//            stmt.setString(2, employeeName);
//            stmt.setString(1, getGenerateEmployeeID(5));
//
//
//            if (toChain) {
//                stmt.setBoolean(3, true);
//                stmt.setInt(4, 99);
//
//                chainAndMall.viewAllChains();
//                System.out.println("Select a chain from the Available chains:  ");
//                selectedValue = controller.selectFromScanner();
//                stmt.setInt(5, selectedValue);
//
//            } else {
//                stmt.setBoolean(3, false);
//
//
//                store.viewAllStores();
//                System.out.println("Select a Store from the Available stores:  ");
//                selectedValue = controller.selectFromScanner();
//                stmt.setInt(4, selectedValue);
//
//// set chain according to the store selected
//
//                stmt.setInt(5, Integer.valueOf(store.getChainIDfromStore(selectedValue)));
//            }
//
//
//            int result = stmt.executeUpdate();
//            if (result == 0) {
//                System.out.println("no updates were done");
//            } else {
//                System.out.println("number of inserted records:  " + result);
//            }
//
//
//        } catch (SQLException e) {
//            System.out.println(e.getErrorCode());
//        }
//
//    }


    int getGenerateEmployeeID(int count) {


        Random generator = new Random();
        int i = generator.nextInt(50000);

        return i;
    }


    public void presentAllEmployeesOfChain() {
        int selectedValue;
        try (Connection conn = controller.getConnectionToDB(); PreparedStatement stmt = conn.prepareStatement("SELECT first_name,last_name FROM stores.employees WHERE chainID=?;");
        ) {


            chainAndMall.viewAllChains();
            System.out.println("Select a chain from the Available chains:  ");
            selectedValue = controller.selectFromScanner();
            stmt.setInt(1, selectedValue);
            try (ResultSet rs = stmt.executeQuery()) {
                System.out.println("Displayed Employees  :  ");

                while (rs.next()) {
                    System.out.print("employee first name : " + rs.getString(1));
                    System.out.println("  employee last name : " + rs.getString(2));

                }
            }


        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
        }

    }
}



