package storesgroup;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ControllerTest {

    Controller controller = new Controller();
    @Test

    public void deleteFromDatabase(){

        String tableName = "chain";
        String field = "name";
        String fieldValue = "testDelete";

                controller.insertIntoDatabase(tableName,field,fieldValue);
//                controller.selectFromDatabase(tableName,"name="+fieldValue,"idchain");


                assertTrue(controller.deleteFromDatabase(tableName,"idchain = "+controller.selectFromDatabase(tableName,"name=\""+fieldValue+"\"","idchain")));


    }

}