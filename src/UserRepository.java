import java.sql.*;

public class UserRepository {
    private static final String URL = "jdbc:mysql://localhost:3306/sentinel_db?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "YOUR_ROOT_PASSWORD"; // put your actual MySQL password here

    public void saveUser(User user, ApiKey apiKey, RateLimitPolicy policy) {
        String sql = "INSERT INTO users (username, email, api_key, max_requests, window_seconds) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, user.getUsername());
            ps.setString(2, user.getEmail());
            ps.setString(3, apiKey.getKeyValue());
            ps.setInt(4, policy.getMaxRequests());
            ps.setInt(5, 30);
            ps.executeUpdate();
            System.out.println("Saved user: " + user.getUsername());

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

    public void printAllUsers() {
        String sql = "SELECT * FROM users";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                System.out.println(rs.getInt("id") + " | " + rs.getString("username") +
                        " | " + rs.getString("api_key") + " | max: " + rs.getInt("max_requests"));
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }
}