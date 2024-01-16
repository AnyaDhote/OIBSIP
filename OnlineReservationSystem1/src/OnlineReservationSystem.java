import java.sql.*;
import java.util.*;
public class OnlineReservationSystem {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in);
             Connection connection = DatabaseConnection.getConnection()) {

            ReservationSystem reservationSystem = new ReservationSystem(connection);
            reservationSystem.run();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
} 