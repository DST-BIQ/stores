package storesgroup.model;

import org.junit.jupiter.api.Test;
import storesgroup.Controller;

import java.sql.SQLException;

public class StoreTest {

    @Test
    public void presentStoreDetailsTests() throws SQLException {
        Controller controller = new Controller();
        Store store = new Store();
        store.presentStoreDetails(3);
    }
}
