package storesgroup;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ControllerTest {
    View view = new View();
    Controller controller = new Controller(view);
    @Test

    public void deleteFromDatabase() throws SQLException {

        String tableName = "chain";
        String field = "name";
        String fieldValue = "testDelete";

                controller.insertIntoDatabase(tableName,field,fieldValue);
                assertTrue(controller.deleteFromDatabase(tableName,"idchain = "+controller.selectFromDatabase(tableName,"name=\""+fieldValue+"\"","idchain")));

    }

}