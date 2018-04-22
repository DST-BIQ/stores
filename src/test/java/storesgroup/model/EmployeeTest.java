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

    EmployeeTest() throws SQLException {
    }


    @BeforeEach
    public void setUp() throws SQLException {

        chain.createChain("testChain");
        store.addStoreToChain("testPurpose", chain.getChainID("testChain"));
    }

    @AfterEach
    public void tearDown() throws SQLException {

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