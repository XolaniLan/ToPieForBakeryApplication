//package Login_Test;
//
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//
//public class LoginTest {
//
//    @Test
//    void testLoginAsAdmin() {
//        // Arrange
//        String username = "admin";
//        String password = "admin123";
//
//        // Act
//        boolean loginSuccessful = performLogin(username, password);
//
//        // Assert
//        Assertions.assertTrue(loginSuccessful);
//        Assertions.assertEquals("admin", getCurrentUserRole());
//    }
//
//    @Test
//    void testLoginAsUser() {
//        // Arrange
//        String username = "user";
//        String password = "user123";
//
//        // Act
//        boolean loginSuccessful = performLogin(username, password);
//
//        // Assert
//        Assertions.assertTrue(loginSuccessful);
//        Assertions.assertEquals("user", getCurrentUserRole());
//    }
//
//    @Test
//    void testInvalidLogin() {
//        // Arrange
//        String username = "invalid";
//        String password = "password";
//
//        // Act
//        boolean loginSuccessful = performLogin(username, password);
//
//        // Assert
//        Assertions.assertFalse(loginSuccessful);
//    }
//
//    // Simulates the login process and returns true if successful
//    private boolean performLogin(String username, String password) {
//        // Your login implementation goes here
//        // This method should validate the credentials and return true if successful, false otherwise
//        // You can use a mock or stub implementation for testing purposes
//        return true; // Replace with your implementation
//    }
//
//    // Returns the current role of the logged-in user
//    private String getCurrentUserRole() {
//        // Your implementation to retrieve the user's role goes here
//        // You can use a mock or stub implementation for testing purposes
//        return "admin"; // Replace with your implementation
//    }
//}
