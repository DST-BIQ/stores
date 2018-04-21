package storesgroup.model;

import org.junit.jupiter.api.Test;
import storesgroup.Controller;
import storesgroup.View;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;


class ChainAndMallTest {

    View view = new View();
    Controller controller = new Controller();
    ChainAndMall chainAndMall = new ChainAndMall(view,controller);

    ChainAndMallTest() throws SQLException {
    }
    Controller controller = new Controller();
    ChainAndMall chainAndMall = new ChainAndMall();

    @Test
    public void addChainPositive() throws SQLException {
        String chainName = "test";
    }
    @Test
    public void addChainPositive() {
        String chainName = "test";

        if (controller.selectFromDatabase("chain", "name=\"" + chainName + "\"", "idchain") != null) {
            System.out.println("deleting existing chain from DB");
            controller.deleteFromDatabase("stores.chain", "test");
        }

        chainAndMall.createChain(chainName);

        assertEquals(chainName, controller.selectFromDatabase("chain", "Name=\"" + chainName + "\"", "Name"));


    }


}