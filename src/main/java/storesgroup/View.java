package storesgroup;

import storesgroup.model.Store;

import java.sql.ResultSet;
import java.sql.SQLException;

public class View {

    public static void printMenu() {

        System.out.println("Please select one of the options:");
        System.out.println("1. Create new chain");
        System.out.println("2. Add store to chain");
        System.out.println("3. Add employee to chain");  // exist employee type name if exists and not chain update, if not exists insert
        System.out.println("4. Add employee to Store");  // exist employee type name if exists and not chain update, if not exists insert
        System.out.println("5. Present all shops of a certain shopping mall");
        System.out.println("6. Present all shops of a certain shopping mall group");//TODO
        System.out.println("7. Present all Employees of a certain Chain");
        System.out.println("8. Present store details");
        System.out.println("Any other number to exit...");


    }




    public  void printMessage(String message) {
        System.out.println(message);
    }

    public void printRecord(ResultSet rs, Store store) throws SQLException {
        for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
            if (rs.getString(i) != null) {
                printMessage(rs.getMetaData().getColumnLabel(i) + ": " + rs.getString(i));
            }
        }
    }
}



