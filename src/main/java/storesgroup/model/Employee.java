package storesgroup.model;

import storesgroup.Controller;

import java.sql.*;
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


    int getGenerateEmployeeID(int count) {


        Random generator = new Random();
        int i = generator.nextInt(50000);

        return i;
    }


    public void presentAllEmployeesOfChain(int chainID) {
        Store store = new Store();
        try (Connection conn = controller.getConnectionToDB(); PreparedStatement stmt = conn.prepareStatement("SELECT id,first_name,last_name,fname,dateofbirth,isManager,storeID FROM stores.employees WHERE chainID=?;")
        ) {


            stmt.setInt(1, chainID);
            try (ResultSet rs = stmt.executeQuery()) {
                System.out.println("Displayed Employees:");

                System.out.println("ID | First Name | LastName | Fname | BirthDate | IsManager | Store");

                while (rs.next()) {
                    //select id,first_name,last_name,fname,dateofbirth,isManager,storeID from employees where chainID=10025;
                    System.out.print(rs.getInt(1)+"|");
                    System.out.print(rs.getString(2)+"|");
                    System.out.print(rs.getString(3)+"|");
                    System.out.print(rs.getString(4)+"|");
                    System.out.print(rs.getString(5)+"|");

                    System.out.print(rs.getBoolean(6)+"|");
                    int storeID = rs.getInt(7);
                    System.out.println(controller.selectFromDatabase("stores","idStore ="+storeID+"\"","store_name")+"|");



                }
            }


        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
        }

    }


}



