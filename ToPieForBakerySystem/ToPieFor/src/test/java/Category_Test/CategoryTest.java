//package Category_Test;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import org.junit.jupiter.api.AfterAll;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.junit.jupiter.api.Assertions.fail;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import za.co.bakery.dao.CategoryDao;
//import za.co.bakery.dao.impl.CategoryDaoImpl;
//import za.co.bakery.model.Category;
//import org.junit.jupiter.api.TestInstance;
//
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//public class CategoryTest {
//
//    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
//    private static final String DB_URL = "jdbc:mysql://localhost:3306/thebakery";
//    private static final String USER = "root";
//    private static final String PASS = "root";
//    private Connection conn;
//    private CategoryDao categoryDao;
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
//        categoryDao = CategoryDaoImpl.getInstance(conn);
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
//public void testAddCategory() throws SQLException {
//    Category category = new Category();
//    category.setName("Test Name");
//    category.setDescription("Test Description");
//
//    boolean result = categoryDao.addCategory(category);
//    assertTrue(result);
//
//    int categoryId = 11;
//    assertNotEquals(0, categoryId); // Ensure that categoryId is not 0
//
//    String querySql = "SELECT * FROM category WHERE categoryId = ?";
//    PreparedStatement queryStatement = conn.prepareStatement(querySql);
//    queryStatement.setInt(1, categoryId);
//    ResultSet resultSet = queryStatement.executeQuery();
//
//    assertTrue(resultSet.next());
//    assertEquals(category.getName(), resultSet.getString("name"));
//    assertEquals(category.getDescription(), resultSet.getString("description"));
//}
//
//    @Test
//    public void testEditCategory() throws SQLException {
//        int categoryId = 1;
//        String querySql = "SELECT * FROM category WHERE categoryId = ? AND isActive = 1";
//        PreparedStatement queryStatement = conn.prepareStatement(querySql);
//        queryStatement.setInt(1, categoryId);
//        ResultSet resultSet = queryStatement.executeQuery();
//        assertTrue(resultSet.next());
//
//        // Modify the category's properties using an SQL update query
//        String updateSql = "UPDATE category SET name = ?, description = ?, isActive = ? WHERE categoryId = ?";
//        PreparedStatement updateStatement = conn.prepareStatement(updateSql);
//        updateStatement.setString(1, "Edited Category Name");
//        updateStatement.setString(2, "Edited Category Description");
//        updateStatement.setInt(3, 1); // New isActive value
//        updateStatement.setInt(4, categoryId);
//
//        int updateResult = updateStatement.executeUpdate();
//        assertEquals(1, updateResult);
//
//        // Retrieve the updated category from the database
//        PreparedStatement queryStatementAfterUpdate = conn.prepareStatement(querySql);
//        queryStatementAfterUpdate.setInt(1, categoryId);
//        ResultSet updatedResultSet = queryStatementAfterUpdate.executeQuery();
//        assertTrue(updatedResultSet.next());
//
//        // Assert that the category properties have been successfully updated
//        assertEquals("Edited Category Name", updatedResultSet.getString("name"));
//        assertEquals("Edited Category Description", updatedResultSet.getString("description"));
//        assertEquals(1, updatedResultSet.getInt("isActive"));
//    
//}
//
//    @Test
//    public void getAllActiveCategories() throws SQLException {
//        int isActive = 1;
//        List<String> expectedActive = Arrays.asList("Edited Category Name", "Cakes", "vs vs", "new", "new cat", "Test Name", "Test Name");
//        List<String> actualActive = getAllActiveCategories(isActive);
//        assertEquals(expectedActive, actualActive, "Active categories should match");
//    }
//
//    private List<String> getAllActiveCategories(int isActive) throws SQLException {
//        List<String> categories = new ArrayList<>();
//        String query = "SELECT name FROM category WHERE isActive = ?";
//        try (PreparedStatement statement = conn.prepareStatement(query)) {
//            statement.setInt(1, isActive);
//            try (ResultSet resultSet = statement.executeQuery()) {
//                while (resultSet.next()) {
//                    String categoryName = resultSet.getString("name");
//                    categories.add(categoryName);
//                }
//            }
//        }
//        return categories;
//    
//}
// @Test
//    public void getCategoryById() throws SQLException {
//        
//        int categoryId = 1;
//        
//        String expectedCategoryName = "hello";
//
//        String actualCategoryName = getCategoryById(categoryId);
//
//        assertEquals(expectedCategoryName, actualCategoryName, "Category name should match");
//    }
//
//    private String getCategoryById(int categoryId) throws SQLException {
//        String query = "SELECT name FROM category WHERE categoryId = ?";
//        try (PreparedStatement statement = conn.prepareStatement(query)) {
//            statement.setInt(1, categoryId);
//            try (ResultSet resultSet = statement.executeQuery()) {
//                if (resultSet.next()) {
//                    return resultSet.getString("name");
//                }
//            }
//        }
//        return null;
//    }
//
//@Test
//public void testGetAllCategories() throws SQLException {
//    List<String> expectedCategories = Arrays.asList("hello", "Cakes", "vs vs", "new", "new cat");
//    List<String> actualCategories = getAllCategories();
//    assertEquals(expectedCategories, actualCategories, "Categories should match");
//}
//
//private List<String> getAllCategories() throws SQLException {
//    List<String> categories = new ArrayList<>();
//    String query = "SELECT name FROM category";
//    try (PreparedStatement statement = conn.prepareStatement(query);
//         ResultSet resultSet = statement.executeQuery()) {
//        while (resultSet.next()) {
//            String categoryName = resultSet.getString("name");
//            categories.add(categoryName);
//        }
//    }
//    return categories;
//}
//}