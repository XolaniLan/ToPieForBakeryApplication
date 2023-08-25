//package Ingredient_Test;
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
//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.TestInstance;
//import za.co.bakery.dao.IngredientDao;
//import za.co.bakery.dao.impl.IngredientDaoImpl;
//import za.co.bakery.model.Ingredient;
//
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//public class IngredientTest {
//
//    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
//    private static final String DB_URL = "jdbc:mysql://localhost:3306/thebakery";
//    private static final String USER = "root";
//    private static final String PASS = "root";
//    private Connection conn;
//    private IngredientDao ingredientDao;
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
//        ingredientDao = IngredientDaoImpl.getInstance(conn);
//    }
//
//    @AfterAll
//    public void tearDown() throws SQLException {
//        if (conn != null) {
//            conn.close();
//        }
//    }
//
//    @Test
//    public void testAddIngredient() throws SQLException {
//        Ingredient ingredient = new Ingredient();
//        ingredient.setUnitId(1);
//         ingredient.setQuantityInStock(35);
//        ingredient.setName("Test Ingred");
//        ingredient.setMinimumStockLevel(20);
//
//        boolean result = ingredientDao.addIngredient(ingredient);
//        assertTrue(result);
//
//        int ingredientId = 12;
//        assertNotEquals(0, ingredientId);
//
//        String querySql = "SELECT * FROM ingredient WHERE ingredientId = ?";
//        try (PreparedStatement queryStatement = conn.prepareStatement(querySql)) {
//            queryStatement.setInt(1, ingredientId);
//            try (ResultSet resultSet = queryStatement.executeQuery()) {
//                assertTrue(resultSet.next());
//                assertEquals(ingredient.getUnitId(), resultSet.getInt("unitId"));
//                assertEquals(ingredient.getQuantityInStock(), resultSet.getInt("quantityInStock"));
//                assertEquals(ingredient.getName(), resultSet.getString("name"));
//                assertEquals(ingredient.getMinimumStockLevel(), resultSet.getInt("minimumStockLevel"));
//                
//            }
//        }
//
//}
//@Test
//public void testGetIngredientByName() throws SQLException {
//    String name = "Eggs";
//    String expectedIngredientName = "Eggs";
//    String actualIngredientName = getIngredientByName(name);
//    assertEquals(expectedIngredientName, actualIngredientName, "Ingredient name should match");
//}
//
//private String getIngredientByName(String name) throws SQLException {
//    String query = "SELECT name FROM ingredient WHERE name = ?";
//    try (PreparedStatement statement = conn.prepareStatement(query)) {
//        statement.setString(1, name);
//        try (ResultSet resultSet = statement.executeQuery()) {
//            if (resultSet.next()) {
//                return resultSet.getString("name");
//            }
//        }
//    }
//    return null;
//
//}
//@Test
//public void testGetAllIngredients() throws SQLException {
//    List<String> expectedIngredients = Arrays.asList("Eggs", "carrots","Tshepang","flakes","flour","new","suagr","Test Ingred" );
//    List<String> actualIngredients = getAllIngridients();
//    assertEquals(expectedIngredients, actualIngredients, "Ingredients should match");
//}
//
//private List<String> getAllIngridients() throws SQLException {
//    List<String> ingredients = new ArrayList<>();
//    String query = "SELECT name FROM ingredient";
//    try (PreparedStatement statement = conn.prepareStatement(query);
//         ResultSet resultSet = statement.executeQuery()) {
//        while (resultSet.next()) {
//            String ingredientName = resultSet.getString("name");
//            ingredients.add(ingredientName);
//        }
//    }
//    return ingredients;
//}
//}