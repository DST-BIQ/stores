package storesgroup;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

import static stores.group.console.application.Model.DB_NAME;

public class Controller {
    private MysqlDataSource ds = new MysqlDataSource();
    private Connection conn = null;
    static final String USER = "root";
    static final String PASS = "root";
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
        while (!correctInput){
            try {

                selection = scanner.nextInt();
                correctInput = true;

            } catch (InputMismatchException e) {
                System.out.println("wrong format selection, please try again");
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


    public void tearDown(){
        scanner.close();
    }
}
