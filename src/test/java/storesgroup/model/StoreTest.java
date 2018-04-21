package storesgroup.model;

import org.junit.jupiter.api.Test;
import storesgroup.Controller;
import storesgroup.View;

import java.sql.SQLException;

public class StoreTest {
    View view = new View();
    Controller controller = new Controller();

    @Test
    public void presentStoreDetailsTests() throws SQLException {

        Store store = new Store(view,controller);
        store.presentStoreDetails(3);
    }
}
