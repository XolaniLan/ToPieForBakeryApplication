//package Recipe_Test;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.TestInstance;
//import za.co.bakery.dao.RecipeDao;
//import za.co.bakery.dao.impl.RecipeDaoImpl;
//import za.co.bakery.model.Recipe;
//
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//public class RecipeTest {
//
//    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
//    private static final String DB_URL = "jdbc:mysql://localhost:3306/thebakery";
//    private static final String USER = "root";
//    private static final String PASS = "root";
//    private Connection conn;
//    private RecipeDao recipeDao;
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
//        recipeDao = RecipeDaoImpl.getInstance(conn);
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
//    public void testAddRecipe() throws SQLException {
//        Recipe recipe = new Recipe();
//        recipe.setRecipeName("TEST NAME");
//        recipe.setDescription("TEST DESC");
//
//        boolean result = recipeDao.addRecipe(recipe);
//        assertTrue(result);
//
//        int recipeId = 12;
//        assertNotEquals(0, recipeId);
//
//        String querySql = "SELECT recipeName, description FROM recipe WHERE recipeId = ?";
//        PreparedStatement queryStatement = conn.prepareStatement(querySql);
//        queryStatement.setInt(1, recipeId);
//        ResultSet resultSet = queryStatement.executeQuery();
//
//        assertTrue(resultSet.next());
//        assertEquals(recipe.getRecipeName(), resultSet.getString("recipeName"));
//        assertEquals(recipe.getDescription(), resultSet.getString("description"));
//    }
//
//    //editTest
//@Test
//public void testEditRecipe() throws SQLException {
//    int recipeId = 1;
//    String querySql = "SELECT * FROM recipe WHERE recipeId = ?";
//    PreparedStatement queryStatement = conn.prepareStatement(querySql);
//    queryStatement.setInt(1, recipeId);
//    ResultSet resultSet = queryStatement.executeQuery();
//    assertTrue(resultSet.next());
//
//    // Modify the item's properties using an SQL update query
//    String updateSql = "UPDATE recipe SET ingredientId = ?, recipeName = ?, description = ?, isActive = ? WHERE recipeId = ?";
//    PreparedStatement updateStatement = conn.prepareStatement(updateSql);
//    updateStatement.setInt(1, 2); // New ingredientId ID
//    updateStatement.setString(2, "Edited Recipe Name");
//    updateStatement.setString(3, "Edited Recipe Description");
//    updateStatement.setInt(4, 1); // New isActive value
//    updateStatement.setInt(5, recipeId);
//
//    int updateResult = updateStatement.executeUpdate();
//    assertEquals(1, updateResult);
//
//    // Retrieve the updated item from the database
//    PreparedStatement queryStatementAfterUpdate = conn.prepareStatement(querySql);
//    queryStatementAfterUpdate.setInt(1, recipeId);
//    ResultSet updatedResultSet = queryStatementAfterUpdate.executeQuery();
//    assertTrue(updatedResultSet.next());
//
//    // Assert that the item properties have been successfully updated
//    assertEquals(2, updatedResultSet.getInt("ingredientId"));
//    assertEquals("Edited Recipe Name", updatedResultSet.getString("recipeName"));
//    assertEquals("Edited Recipe Description", updatedResultSet.getString("description"));
//    assertEquals(1, updatedResultSet.getInt("isActive"));
//}
//
// @Test
//    public void getRecipeById() throws SQLException {
//        
//        int recipeId = 1;
//        
//        String expectedRecipeName = "Edited Recipe Name";
//
//        String actualRecipeName = getRecipeById(recipeId);
//
//        assertEquals(expectedRecipeName, actualRecipeName, "Recipe name should match");
//    }
//
//    private String getRecipeById(int recipeId) throws SQLException {
//        String query = "SELECT recipeName FROM recipe WHERE recipeId = ?";
//        try (PreparedStatement statement = conn.prepareStatement(query)) {
//            statement.setInt(1, recipeId);
//            try (ResultSet resultSet = statement.executeQuery()) {
//                if (resultSet.next()) {
//                    return resultSet.getString("recipeName");
//                }
//            }
//        }
//        return null;
//    }
//}