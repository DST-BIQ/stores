package storesgroup.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import storesgroup.Controller;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class EmployeeTest {

    Employee employee = new Employee();
    Store store = new Store();
    Controller controller = new Controller();
    ChainAndMall chain = new ChainAndMall();


    @BeforeEach
    public void setUp() throws SQLException {

        chain.createChain("testChain");
        store.addStoreToChain("testPurpose", chain.getChainID("testChain"));
    }

    @AfterEach
    public void tearDown(){

controller.deleteFromDatabase("employees", "first_name=\"firstNameForTest\"");
controller.deleteFromDatabase("stores","store_name=\"testPurpose\"");
controller.deleteFromDatabase("chain","name = \"testChain\"");

    }
    @Test
    public void addEmployeToStorePositive() throws SQLException {


        employee.addEmployeeToStore("firstNameForTest", store.getStoreID("testPurpose"), "rieur", "nono");
        assertEquals( "rieur",controller.selectFromDatabase("employees", "first_name = \"firstNameForTest\"", "last_name"));


    }

    @Test
    public void addEmployeToStoreNegative() throws SQLException {


        employee.addEmployeeToStore("firstNameForTest", store.getStoreID("testPurpose"), "rieur", "nono");
        assertNotEquals( "riefur",controller.selectFromDatabase("employees", "first_name = \"firstNameForTest\"", "last_name"));


    }

    @Test
    public void addEmployeToChainPositive() throws SQLException {


        employee.addEmployeeToChain("firstNameForTest", chain.getChainID("testChain"), "rieur", "nono");
        assertEquals( "rieur",controller.selectFromDatabase("employees", "first_name = \"firstNameForTest\"", "last_name"));


    }

    @Test
    public void addEmployeToChainNegative() throws SQLException {


        employee.addEmployeeToChain("firstNameForTest", chain.getChainID("testChain"), "rieur", "nono");
        assertNotEquals( "riesssur",controller.selectFromDatabase("employees", "first_name = \"firstNameForTest\"", "last_name"));


    }

}