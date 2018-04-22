package storesgroup.model;

import org.junit.jupiter.api.Test;
import storesgroup.Controller;
import storesgroup.View;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;


class ChainAndMallTest {

    View view = new View();
    Controller controller = new Controller(view);
    ChainAndMall chainAndMall = new ChainAndMall(view,controller);

    ChainAndMallTest() throws SQLException {
        ChainAndMall chainAndMall = new ChainAndMall(view,controller);
    }

    @Test
    public void addChainPositive() throws SQLException {
        String chainName = "test" + System.currentTimeMillis();
        chainAndMall.createChain(chainName);
        assertEquals(chainName, controller.selectFromDatabase("chain", "name=\"" + chainName + "\"", "Name"));
    }
}