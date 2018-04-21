package storesgroup.model;

import org.junit.jupiter.api.Test;
import storesgroup.Controller;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StoreTest {
    Controller controller = new Controller();
    Store store = new Store();

    @Test
    public void addStorePositive() throws SQLException {
        String storeName = "testStore";

        if (controller.selectFromDatabase("stores", "name=\"" + storeName + "\"", "id") != null) {
            System.out.println("deleting existing store from DB");
            controller.deleteFromDatabase("stores.stores", storeName);
        }
        int value = Integer.valueOf((controller.selectFromDatabase("chain","name=\"fox\"", "idchain")));
        store.addStoreToChain(storeName,value);


        assertEquals(storeName, controller.selectFromDatabase("stores", "store_name=\"" + storeName + "\"", "store_name"));

    }

}