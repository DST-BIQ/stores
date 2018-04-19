package storesgroup;

import java.sql.SQLException;

public class StoresGroupConsoleApplication {
    //TODO ALL - unit tests, documentation
// TODO tal - upload DB scheme
    public static void main(String[] args) {
        View view = new View();


        try {
            view.consoleApplication();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}








