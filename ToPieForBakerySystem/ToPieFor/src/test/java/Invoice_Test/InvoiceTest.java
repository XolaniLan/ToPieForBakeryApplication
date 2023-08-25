//package Invoice_Test;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.time.LocalDate;
//import java.time.Month;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.TestInstance;
//import za.co.bakery.dao.InvoiceDao;
//import za.co.bakery.dao.impl.InvoiceDaoImpl;
//import za.co.bakery.model.Invoice;
//
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//public class InvoiceTest {
//
//    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
//    private static final String DB_URL = "jdbc:mysql://localhost:3306/thebakery";
//    private static final String USER = "root";
//    private static final String PASS = "root";
//    private Connection conn;
//    private InvoiceDao invoiceDao;
//
//    @BeforeAll
//    public void setUp() throws SQLException {
//        try {
//            Class.forName(JDBC_DRIVER);
//        } catch (ClassNotFoundException e) {
//            fail("JDBC Driver not found");
//        }
//
//        conn = DriverManager.getConnection(DB_URL, USER, PASS);
//        invoiceDao = InvoiceDaoImpl.getInstance(conn);
//    }
//
//    @AfterAll
//    public void tearDown() throws SQLException {
//        if (conn != null) {
//            conn.close();
//        }
//    }
//
//@Test
//public void testAddInvoice() throws SQLException {
//    Invoice invoice = new Invoice();
//    invoice.setOrderId(1);
//    invoice.setDatePaid(LocalDate.of(2023, 3, 3));
//    invoice.setStatus(true);
//
//    boolean result = invoiceDao.addInvoice(invoice);
//    assertTrue(result);
//
//    int invoiceId = 3;
//    assertNotEquals(0, invoiceId);
//
//    String querySql = "SELECT * FROM invoice WHERE invoiceId = ?";
//    try (PreparedStatement queryStatement = conn.prepareStatement(querySql)) {
//        queryStatement.setInt(1, invoiceId);
//        try (ResultSet resultSet = queryStatement.executeQuery()) {
//            assertTrue(resultSet.next());
//            assertEquals(invoice.getOrderId(), resultSet.getInt("orderId"));
//            assertEquals(invoice.getDatePaid().toString(), resultSet.getDate("datePaid").toLocalDate().toString());
//            assertEquals(invoice.getStatus(), resultSet.getBoolean("status"));
//        }
//    }
//}
//@Test
//public void testGetAllInvoices() throws SQLException {
//    List<String> expectedInvoices = Arrays.asList("1" , "0", "1", "1" ,"1" ,"1" ,"1" ,"1" ,"1" ,"1", "1", "1");
//    List<String> actualInvoices = getAllInvoices();
//    assertEquals(expectedInvoices, actualInvoices, "Invoices should match");
//}
//
//private List<String> getAllInvoices() throws SQLException {
//    List<String> invoices = new ArrayList<>();
//    String query = "SELECT status FROM invoice";
//    try (PreparedStatement statement = conn.prepareStatement(query);
//         ResultSet resultSet = statement.executeQuery()) {
//        while (resultSet.next()) {
//            String status = resultSet.getString("status");
//            invoices.add(status);
//        }
//    }
//    return invoices;
//
//}
//    @Test
//    public void testGetInvoiceByStatus() throws SQLException {
//        int status = 0;
//        String expectedInvoice = "2";
//
//        String actualInvoice = getInvoiceByStatus(status);
//
//        assertEquals(expectedInvoice, actualInvoice, "Invoice status should match");
//    }
//
//    private String getInvoiceByStatus(int status) throws SQLException {
//        String query = "SELECT invoiceId FROM invoice WHERE status = ?";
//        try (PreparedStatement statement = conn.prepareStatement(query)) {
//            statement.setInt(1, status);
//            try (ResultSet resultSet = statement.executeQuery()) {
//                if (resultSet.next()) {
//                    return String.valueOf(resultSet.getInt("invoiceId"));
//                }
//            }
//        }
//        return null;
//    
//}
//    @Test
//public void testGetInvoiceById() throws SQLException {
//    int invoiceId = 1;
//    String expectedInvoiceStatus = "1";
//
//    String actualInvoiceStatus = getInvoiceStatusById(invoiceId);
//
//    assertEquals(expectedInvoiceStatus, actualInvoiceStatus, "Invoice status should match");
//}
//
//private String getInvoiceStatusById(int invoiceId) throws SQLException {
//    String query = "SELECT invoiceId FROM invoice WHERE invoiceId = ?";
//    try (PreparedStatement statement = conn.prepareStatement(query)) {
//        statement.setInt(1, invoiceId);
//        try (ResultSet resultSet = statement.executeQuery()) {
//            if (resultSet.next()) {
//                return String.valueOf(resultSet.getInt("invoiceId"));
//            }
//        }
//    }
//    return null;
//}
//}
