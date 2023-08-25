package DB_Test;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;



public class DatabaseConnectionTest {
    private String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private String DB_URL = "jdbc:mysql://localhost:3306/thebakery";
    private String USER = "root";
    private String PASS = "root";
    private Connection conn;

    @Test
    public void testDatabaseConnection() {
        Connection conn = null;
        try {
            // Register driver
            Class.forName(JDBC_DRIVER);

            // Open the connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // Check if the connection is valid
            assertTrue(conn.isValid(5)); // 5 seconds timeout
        } catch (ClassNotFoundException e) {
            fail("JDBC Driver not found");
        } catch (SQLException e) {
            fail("Failed to connect to the database");
        } finally {
            // Closing the connection
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    fail("Failed to close the database connection");
                }
            }
        }
    }
}
