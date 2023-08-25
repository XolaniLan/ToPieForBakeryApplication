//package Item_Test;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.junit.jupiter.api.Assertions.fail;
//import org.junit.jupiter.api.AfterAll;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestInstance;
//import za.co.bakery.dao.ItemDao;
//import za.co.bakery.dao.impl.ItemDaoImpl;
//import za.co.bakery.model.Item;
//import za.co.bakery.service.ItemService;
//
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//public class ItemTest {
//
//    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
//    private static final String DB_URL = "jdbc:mysql://localhost:3306/thebakery";
//    private static final String USER = "root";
//    private static final String PASS = "root";
//    private Connection conn;
//    private ItemDao itemDao;
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
//        itemDao = ItemDaoImpl.getInstance(conn);
//    }
//
//    @AfterAll
//    public void tearDown() throws SQLException {
//        if (conn != null) {
//            conn.close();
//        }
//    }
////addTest
//    @Test
//    public void testAddItem() throws SQLException {
//        Item item = new Item();
//        item.setCategoryId(1);
//        item.setRecipeId(1);
//        item.setItemName("Test Item Name");
//        item.setPrice(40);
//        item.setDescription("Test Item Description");
//        item.setNutritionalValue("Test Item Nutritional Value");
//        item.setWarnings("Warnings Test");
//        item.setImageUrl("Planet9_3840x2160.jpg");
//
//        boolean result = itemDao.addItem(item);
//        assertTrue(result);
//
//        int itemId = 16;
//        assertNotEquals(0, itemId);
//
//        String querySql = "SELECT * FROM item WHERE itemId = ?";
//        PreparedStatement queryStatement = conn.prepareStatement(querySql);
//        queryStatement.setInt(1, itemId);
//        ResultSet resultSet = queryStatement.executeQuery();
//
//        assertTrue(resultSet.next());
//        assertEquals(item.getCategoryId(), resultSet.getInt("categoryId"));
//        assertEquals(item.getRecipeId(), resultSet.getInt("recipeId"));
//        assertEquals(item.getItemName(), resultSet.getString("itemName"));
//        assertEquals(item.getPrice(), resultSet.getDouble("price"));
//        assertEquals(item.getDescription(), resultSet.getString("description"));
//        assertEquals(item.getNutritionalValue(), resultSet.getString("nutritionalValue"));
//        assertEquals(item.getWarnings(), resultSet.getString("warnings"));
//        assertEquals(item.getImageUrl(), resultSet.getString("image"));
//    
//}
//    ////editTest
//
//    @Test
//    public void testEditCategory() throws SQLException {
//        int itemId = 1;
//        String querySql = "SELECT * FROM item WHERE itemId = ?";
//        PreparedStatement queryStatement = conn.prepareStatement(querySql);
//        queryStatement.setInt(1, itemId);
//        ResultSet resultSet = queryStatement.executeQuery();
//        assertTrue(resultSet.next());
//
//        String updateSql = "UPDATE item SET categoryId = ?, recipeId = ?, itemName = ?, price = ?, description = ?, nutritionalValue = ?, warnings = ?, isActive = ?, image = ? WHERE itemId = ?";
//        PreparedStatement updateStatement = conn.prepareStatement(updateSql);
//        updateStatement.setInt(1, 2); 
//        updateStatement.setInt(2, 2);
//        updateStatement.setString(3, "Edited Item Name");
//        updateStatement.setDouble(4, 50);
//        updateStatement.setString(5, "Edited Item Description");
//        updateStatement.setString(6, "Edited Item Nutritional Value");
//        updateStatement.setString(7, "Edited Warnings Test");
//        updateStatement.setInt(8, 1);
//        updateStatement.setString(9, "Edited_Planet9_3840x2160.jpg");
//        updateStatement.setInt(10, itemId);
//
//        int updateResult = updateStatement.executeUpdate();
//        assertEquals(1, updateResult);
//
//        ResultSet updatedResultSet = queryStatement.executeQuery();
//        assertTrue(updatedResultSet.next());
//
//        // Assert that the item properties have been successfully updated
//        assertEquals(2, updatedResultSet.getInt("categoryId"));
//        assertEquals(2, updatedResultSet.getInt("recipeId"));
//        assertEquals("Edited Item Name", updatedResultSet.getString("itemName"));
//        assertEquals(50, updatedResultSet.getDouble("price"));
//        assertEquals("Edited Item Description", updatedResultSet.getString("description"));
//        assertEquals("Edited Item Nutritional Value", updatedResultSet.getString("nutritionalValue"));
//        assertEquals("Edited Warnings Test", updatedResultSet.getString("warnings"));
//        assertEquals(1, updatedResultSet.getInt("isActive"));
//        assertEquals("Edited_Planet9_3840x2160.jpg", updatedResultSet.getString("image"));
//    
//}
// @Test
//    public void testGetItemById() throws SQLException {
//        
//        int itemId = 1;
//        
//        String expectedItemName = "Chocolate";
//
//        String actualItemName = getItemById(itemId);
//
//        assertEquals(expectedItemName, actualItemName, "Item name should match");
//    }
//
//    private String getItemById(int itemId) throws SQLException {
//        String query = "SELECT itemName FROM item WHERE itemId = ?";
//        try (PreparedStatement statement = conn.prepareStatement(query)) {
//            statement.setInt(1, itemId);
//            try (ResultSet resultSet = statement.executeQuery()) {
//                if (resultSet.next()) {
//                    return resultSet.getString("itemName");
//                }
//            }
//        }
//        return null;
//    
//}
//    @Test
//    public void testGetAllItemsByCategory() throws SQLException {
//        int categoryId = 10;
//        String expectedItemNames = "new, Cake 1";
//
//        String actualItemNames = getAllItemsByCategory(categoryId);
//
//        assertEquals(expectedItemNames, actualItemNames, "Item names should match");
//    }
//
//private String getAllItemsByCategory(int categoryId) throws SQLException {
//    String query = "SELECT itemName FROM item WHERE categoryId = ?";
//    try (PreparedStatement statement = conn.prepareStatement(query)) {
//        statement.setInt(1, categoryId);
//        try (ResultSet resultSet = statement.executeQuery()) {
//            StringBuilder itemNames = new StringBuilder();
//            while (resultSet.next()) {
//                String itemName = resultSet.getString("itemName");
//                itemNames.append(itemName).append(", ");
//            }
//            if (itemNames.length() > 0) {
//                itemNames.setLength(itemNames.length() - 2);
//                return itemNames.toString();
//            }
//        }
//    }
//    return null;
//
//}
//@Test
//public void testGetAllItems() throws SQLException {
//    List<String> expectedItems = Arrays.asList("Edited Item Name", "Chocolate", "Chocolate", "cake", "cake", "Chocolatey", "Chocolate");
//    List<String> actualItems = getAllItems();
//    assertEquals(expectedItems, actualItems, "Categories should match");
//}
//
//private List<String> getAllItems() throws SQLException {
//    List<String> items = new ArrayList<>();
//    String query = "SELECT itemName FROM item";
//    try (PreparedStatement statement = conn.prepareStatement(query);
//         ResultSet resultSet = statement.executeQuery()) {
//        while (resultSet.next()) {
//            String itemName = resultSet.getString("itemName");
//            items.add(itemName);
//        }
//    }
//    return items;
//}
//}
//
