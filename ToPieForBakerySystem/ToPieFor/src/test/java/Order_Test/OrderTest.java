//package Order_Test;
//
//import com.sun.javafx.scene.control.skin.VirtualFlow;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.time.LocalDate;
//import java.time.LocalTime;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.TestInstance;
//import za.co.bakery.dao.OrderDao;
//import za.co.bakery.dao.impl.OrderDaoImpl;
//import za.co.bakery.model.Order;
//import za.co.bakery.model.OrderLineItem;
//
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//public class OrderTest {
//
//    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
//    private static final String DB_URL = "jdbc:mysql://localhost:3306/thebakery";
//    private static final String USER = "root";
//    private static final String PASS = "root";
//    private Connection conn;
//    private OrderDao orderDao;
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
//        orderDao = OrderDaoImpl.getInstance(conn);
//    }
//
//    @AfterAll
//    public void tearDown() throws SQLException {
//        if (conn != null) {
//            conn.close();
//        }
//    }
//@Test
//public void testAddOrder() throws SQLException {
//    Order order = new Order();
//    order.setUsername("hello55@gmail.com");
//    order.setDeliveryAddress("15 Midrand Road");
//    order.setOrderDate(LocalDate.now());
//    order.setTotAmount(25);
//    order.setStatus(true);
//    List<OrderLineItem> lp = new ArrayList();
//
//        lp.add(new OrderLineItem(1, 2, 20));
//        lp.add(new OrderLineItem(1, 2, 20));
//        lp.add(new OrderLineItem(1, 2, 20));
//        
//    order.setOrderLineitems(lp);
//    order.setTime(LocalTime.now());
//
//    boolean result = orderDao.addAnOrder(order);
//    assertTrue(result);
//
//    int orderId = 33;
//    assertNotEquals(0, orderId);
//
//    String querySql = "SELECT * FROM orders WHERE orderId = ?";
//    try (PreparedStatement queryStatement = conn.prepareStatement(querySql)) {
//        queryStatement.setInt(1, orderId);
//        try (ResultSet resultSet = queryStatement.executeQuery()) {
//            assertTrue(resultSet.next());
//            assertEquals(order.getUsername(), resultSet.getString("username"));
//            assertEquals(order.getDeliveryAddress(), resultSet.getString("deliveryAddress"));
//            assertEquals(order.getOrderDate().toString(), resultSet.getDate("orderDate").toLocalDate().toString());
//            assertEquals(order.getTotAmount(), resultSet.getInt("totalAmount"));
//            assertEquals(order.getStatus(), resultSet.getBoolean("status"));
//            //assertEquals(order.getTime(), resultSet.getTime("time"));
//        }
//    }
//
//}
//    @Test
//    public void testGetOrderById() throws SQLException {
//        int orderId = 1;
//        String expectedUsername = "hello@gmail.com";
//        String actualUsername = getOrderById(orderId);
//        assertEquals(expectedUsername, actualUsername, "Order username should match");
//    }
//
//    private String getOrderById(int orderId) throws SQLException {
//        String query = "SELECT username FROM orders WHERE orderId = ?";
//        try (PreparedStatement statement = conn.prepareStatement(query)) {
//            statement.setInt(1, orderId);
//            try (ResultSet resultSet = statement.executeQuery()) {
//                if (resultSet.next()) {
//                    return resultSet.getString("username");
//                }
//            }
//        }
//        return null;
//    
//}
//    @Test
//public void testGetAllOrders() throws SQLException {
//    List<String> expectedOrders = Arrays.asList("hello@gmail.com");
//    List<String> actualOrders = getAllOrders();
//    assertEquals(expectedOrders, actualOrders, "Orders should match");
//}
//
//private List<String> getAllOrders() throws SQLException {
//    List<String> orders = new ArrayList<>();
//    String query = "SELECT username FROM orders";
//    try (PreparedStatement statement = conn.prepareStatement(query);
//         ResultSet resultSet = statement.executeQuery()) {
//        while (resultSet.next()) {
//            String username = resultSet.getString("username");
//            orders.add(username);
//        }
//    }
//    return orders;
//
//}
//        @Test
//public void testGetAllOrdersByStatus() throws SQLException {
//    List<String> expectedStatus = Arrays.asList("0");
//    List<Order> actualStatus = getAllOrdersByStatus();
//    assertEquals(expectedStatus, actualStatus, "Status should match");
//}
//
//private List<Order> getAllOrdersByStatus() throws SQLException {
//    List<Order> stat = new ArrayList<>();
//    String query = "SELECT status FROM orders";
//    try (PreparedStatement statement = conn.prepareStatement(query);
//         ResultSet resultSet = statement.executeQuery()) {
//        while (resultSet.next()) {
//            Boolean status = resultSet.getBoolean("status");
//        }
//    }
//    return stat;
//}
//}
//
