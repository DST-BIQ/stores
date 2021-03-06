package storesgroup.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import storesgroup.Controller;
import storesgroup.View;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class EmployeeTest {
    //TODO - add tests for 'employeePresent' methods

    View view = new View();
    Controller controller = new Controller(view);
    Employee employee = new Employee(view,controller);
    Store store = new Store(view,controller);
    ChainAndMall chain = new ChainAndMall(view,controller);
    int storeID;
    String storeName;
    String chainName;
    int CityID;


    EmployeeTest() throws SQLException {
    }


    @BeforeEach
    public void setUp() throws SQLException {
        chainName="testChain"+System.currentTimeMillis();
        chain.createChain(chainName);
        storeName="testPurpose"+System.currentTimeMillis();
        CityID = 1; // TODO select from cities
        int numberInMall=1;
        int mallID=1;//TODO select mall

        store.addStoreToChain(storeName, chain.getChainID(chainName),CityID,mallID,numberInMall);
        storeID = Integer.valueOf(controller.selectFromDatabase("stores","store_name=\""+storeName+"\"","id"));

    }

    @AfterEach
    public void tearDown() throws SQLException {

controller.deleteFromDatabase("employees", "first_name=\"firstNameForTest\"");
controller.deleteFromDatabase("stores","store_name=\""+storeName+"\"");
controller.deleteFromDatabase("chain","name = \""+chainName+"\"");

    }
    @Test
    public void addEmployeToStorePositive() throws SQLException {


        employee.addEmployeeToStore("firstNameForTest", storeID, "rieur", "nono",1);
        assertEquals( "rieur",controller.selectFromDatabase("employees", "first_name = \"firstNameForTest\"", "last_name"));


    }

    @Test
    public void addEmployeToStoreNegative() throws SQLException {


        employee.addEmployeeToStore("firstNameForTest", storeID, "rieur", "nono",1);
        assertNotEquals( "riefur",controller.selectFromDatabase("employees", "first_name = \"firstNameForTest\"", "last_name"));


    }

    @Test
    public void addEmployeToChainPositive() throws SQLException {


        employee.addEmployeeToChain("firstNameForTest", chain.getChainID(chainName),"rieur", "nono",1);
        assertEquals( "rieur",controller.selectFromDatabase("employees", "first_name = \"firstNameForTest\"", "last_name"));


    }

    @Test
    public void addEmployeToChainNegative() throws SQLException {


        employee.addEmployeeToChain("firstNameForTest", chain.getChainID(chainName), "rieur", "nono",1);
        assertNotEquals( "riesssur",controller.selectFromDatabase("employees", "first_name = \"firstNameForTest\"", "last_name"));


    }




}