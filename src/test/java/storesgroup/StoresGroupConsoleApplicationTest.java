//package storesgroup;
//
//import org.junit.jupiter.api.Test;
//
//import java.sql.Connection;
//import java.sql.SQLException;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//
//public class StoresGroupConsoleApplicationTest {
//    @Test
//    public void testCreateChain() throws SQLException {
//        StoresGroupConsoleApplication app = new StoresGroupConsoleApplication();
//        Connection conn = app.getDataSource().getConnection();
//        assertEquals(1,app.createChain(conn, "Golf"));
//        conn.close();
//
//    }
//
//    @Test
//    public void testAddStoreToChain() throws SQLException {
//        ManageChain chainManager = new ManageChain();
//        Connection conn = chainManager.getDataSource().getConnection();
//        assertEquals(1,chainManager.addStoreToChain(conn,"Fox kids", 10001));
//        conn.close();
//    }
//
//    @Test
//    public void testAddStoreInvalidChainId() throws SQLException {
//        ManageChain chainManager = new ManageChain();
//        Connection conn = chainManager.getDataSource().getConnection();
//        assertThrows(SQLException.class,
//                ()-> {
//                    chainManager.addStoreToChain(conn, "Fox kids", 1001);
//
//                });
//        conn.close();
//    }
//
//    //TODO fix and implement INSERT employee tests
//    @Test
//    public void testAddEmployeeToChain() throws SQLException {
//        ManageChain chainManager = new ManageChain();
//        Connection conn = chainManager.getDataSource().getConnection();
//        chainManager.removeEmployee(conn,"039397708");
//        assertEquals(1,chainManager.addEmployeeToChain(conn,"039397708","Tal","Buch", "Buch", 10001));
//        chainManager.removeEmployee(conn,"039397708");
//
//        conn.close();
//    }
//
//    @Test
//    public void testAddEmployeeToStore() throws SQLException {
//        ManageChain chainManager = new ManageChain();
//        Connection conn = chainManager.getDataSource().getConnection();
//        chainManager.removeEmployee(conn,"039397708");
//        assertEquals(1,chainManager.addEmployeeToStore(conn,"039397708","Tal","Buch","Buch", 10));
//        chainManager.removeEmployee(conn,"039397708");
//        conn.close();
//    }
//
//    @Test
//    public void testAddEmployeeToChainInvalidChainId() throws SQLException {
//        ManageChain chainManager = new ManageChain();
//        Connection conn = chainManager.getDataSource().getConnection();
//        chainManager.removeEmployee(conn,"039397708");
//
//        assertThrows(SQLException.class,
//                ()-> {
//                    chainManager.addEmployeeToChain(conn, "039397708","Tal","Buch","Buch", 100);
//
//                });
//        chainManager.removeEmployee(conn,"039397708");
//        conn.close();
//    }
//
//    @Test
//    public void testAddEmployeeToChainExistingEmployeeId() throws SQLException {
//        ManageChain chainManager = new ManageChain();
//        Connection conn = chainManager.getDataSource().getConnection();
//        chainManager.removeEmployee(conn,"039397708");
//        chainManager.addEmployeeToChain(conn, "039397708","Tal","Buch","Buch", 10001);
//
//        assertThrows(SQLException.class,
//                ()-> {
//                    chainManager.addEmployeeToChain(conn, "039397708","Tal","Buch","Buch", 10001);
//
//                });
//        chainManager.removeEmployee(conn,"039397708");
//        conn.close();
//    }
//}
