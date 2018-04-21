package storesgroup;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;

import static storesgroup.StoresGroupConsoleApplication.printMessageToConsole;

public class Controller {
    private MysqlDataSource ds = new MysqlDataSource();
    private Connection conn = null;
    static final String USER = "root";
    static final String PASS = "root";
    static final String DB_NAME = "stores_group";

    Scanner scanner;


    public Controller() {
        try {
            getConnectionToDB();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        scanner = new Scanner(System.in);


    }


    public int selectFromScanner() {
        boolean correctInput = false;
        int selection = -1;
        while (!correctInput) {
            try {

                selection = scanner.nextInt();
                correctInput = true;

            } catch (InputMismatchException e) {
                System.out.println("wrong format selection, please try again");
                return 999;
            }
        }


        return selection;
    }


    public String getStringFromScanner() {

        String selection = scanner.next();
        System.out.println(selection);
        return selection;

    }


    public DataSource getDataSource() {

        ds.setServerName("127.0.0.1");
        ds.setPortNumber(3306);
        ds.setDatabaseName(DB_NAME);
        ds.setUser(USER);
        ds.setPassword(PASS);
        return ds;
    }


    public Connection getConnectionToDB() throws SQLException {
        return conn = getDataSource().getConnection();
    }


    public void tearDown() {
        scanner.close();
    }


    /**
     * Select column from database. return result set result
     *
     * @param tableName       = FROM what table we wish to retrieve the data
     * @param selectCondition = WHERE part of select
     * @param selection       - SELECT part of query
     */
    public String selectFromDatabase(String tableName, String selectCondition, String selection) {
        Controller controller = new Controller();

        try (Connection conn = controller.getConnectionToDB(); Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("Select " + selection + " from " + tableName + " where " + selectCondition + ";")
        ) {
            if (rs.next()) {
                System.out.println("this is the resource name answer   " + rs.getString(1));
                return rs.getString(1);

            }


        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
        }

        return null;
    }

    /**
     * delete row from database
     *      * @param tableName       = FROM what table we wish to retrieve the data
     * @param deleteCondition = WHERE part of select
     * @return true/false
     */
    public boolean deleteFromDatabase(String tableName, String deleteCondition) {
        Controller controller = new Controller();
String sql = "delete from "+tableName+" where "+deleteCondition+";";
        try (Connection conn = controller.getConnectionToDB(); PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            int result = stmt.executeUpdate();
            if (result == 0) {
                printMessageToConsole("no updates were done");
                return false;
            } else {
                printMessageToConsole("deleted successfully from:  "+tableName+ ":  " + deleteCondition );
                return true;
            }

        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
        }

        return false;
    }


    /**
     * insert value into the database
     * @param tableName = FROM what table we wish to retrieve the data
     * @param field = which field(s) to update
     * @param fieldValue = list of value(s) matching the fields
     ** @return true/false
     */
    public boolean insertIntoDatabase(String tableName, String field,String fieldValue) {
        Controller controller = new Controller();


        String sql = "insert into "+tableName+" ("+field+")";
        try (Connection conn = controller.getConnectionToDB(); PreparedStatement stmt = conn.prepareStatement(sql+" values (?);");
        ) {
            stmt.setString(1, fieldValue);

            int result = stmt.executeUpdate();
            if (result == 0) {
                printMessageToConsole("no updates were done");
                return false;
            } else {
                printMessageToConsole("inserted successfully to:  "+tableName+ ":  " + field );
                return true;
            }

        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
        }

        return false;
    }
}