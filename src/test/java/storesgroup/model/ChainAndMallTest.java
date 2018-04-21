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

    @Test
    public void addChainPositive() throws SQLException {
        String chainName="test";

        if(controller.selectFromDatabase("chain","Name=\""+chainName+"\"","idChain")!=null){
            //TODO delete chain from DB
            System.out.println("deleting existing chain from DB");
        }

        chainAndMall.createChain(chainName);

            assertEquals(chainName,controller.selectFromDatabase("chain","Name=\""+chainName+"\"","Name"));


    }



}