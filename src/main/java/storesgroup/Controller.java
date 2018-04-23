package storesgroup;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Controller {
    private MysqlDataSource ds = new MysqlDataSource();
    private Connection conn = null;
    static final String USER = "root";
    static final String PASS = "root";
    static final String DB_NAME = "stores_group"; //TODO properties per user for all configurable issues.

//    Scanner scanner;
    View view;
    BufferedReader bufferedReader;


    public Controller(View view) {
        this.view = view;
        try {
            getConnectionToDB();
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        scanner = new Scanner(System.in);
        bufferedReader = new BufferedReader(new InputStreamReader(System.in));


    }

    public int getIntFromReader() {
        String selection;
        int value=-1;
        boolean validateIntValue = false;

        while (!validateIntValue){
            try {
                selection = bufferedReader.readLine();

            } catch (IOException exception) {
                // Handle exceptions here.
                return -1;
            }
            try {

                 value =  Integer.valueOf(selection);
                validateIntValue=true;

            }
            catch (NumberFormatException e ){
                view.printMessage("wrong number format, please enter new value");
            }
        }


return value;
    }

    /**
     * get string using bufferred reader. supports spaces on read.
     *
     * @return string value of new entered parameter
     */
    public String getStringFromReader() {
        String selection = "";
        String[] words = {};


        try {
            words = bufferedReader.readLine().split(" ");

        } catch (IOException exception) {
            // Handle exceptions here.
            return null;
        }
        for ( String str : words ) {
            selection = selection + " " + str;
        }

        return selection.trim();

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
//        scanner.close();
        try {
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Select column from database. return result set result
     *
     * @param tableName       = FROM what table we wish to retrieve the data
     * @param selectCondition = WHERE part of select
     * @param selection       - SELECT part of query
     */
    public String selectFromDatabase(String tableName, String selectCondition, String selection) throws SQLException {


        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("Select " + selection + " from " + tableName + " where " + selectCondition + ";");

        if (rs.next()) {
            System.out.println("this is the resource name answer   " + rs.getString(1));
            return rs.getString(1);

        }

        return null;
    }

    /**
     * delete row from database
     * * @param tableName       = FROM what table we wish to retrieve the data
     *
     * @param deleteCondition = WHERE part of select
     * @return true/false
     */
    public boolean deleteFromDatabase(String tableName, String deleteCondition) throws SQLException {

        String sql = "delete from " + tableName + " where " + deleteCondition;
        PreparedStatement stmt = conn.prepareStatement(sql);
        int result = stmt.executeUpdate();
        if (result == 0) {
            view.printMessage("no updates were done");
            return false;
        } else {
            view.printMessage("deleted successfully from:  " + tableName + ":  " + deleteCondition);
            return true;
        }

    }


    /**
     * insert value into the database
     *
     * @param tableName  = FROM what table we wish to retrieve the data
     * @param field      = which field(s) to update
     * @param fieldValue = list of value(s) matching the fields
     *                   * @return true/false
     */
    public boolean insertIntoDatabase(String tableName, String field, String fieldValue) throws SQLException {
        String sql = "insert into " + tableName + " (" + field + ")";
        PreparedStatement stmt = conn.prepareStatement(sql + " values (?);");

        stmt.setString(1, fieldValue);

        int result = stmt.executeUpdate();
        if (result == 0) {
            view.printMessage("no updates were done");
            return false;
        } else {
            view.printMessage("inserted successfully to:  " + tableName + ":  " + field);
            return true;
        }

    }
}