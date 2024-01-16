import java.sql.*;
import java.util.*;
class DatabaseConnection {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/railway_reservation2";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Aniket@333";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);
    }
}