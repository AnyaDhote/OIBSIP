import java.sql.*;
import java.util.*;
public class Login {
	Connection connection;
    Scanner scanner;
    
	//    Scanner scanner = new Scanner(System.in);
    
		public Login(Connection connection, String username, String password) {
		// TODO Auto-generated constructor stub
			this.getUserInput(username);
			this.getUserInput(password);
	}


		private void llogin() {
			try {
	    	System.out.println("Enter UserName and Password : ");
	    	System.out.println();
	        String username = getUserInput("Enter UserName: ");
	        String password = getUserInput("Enter Password: ");
	        u_login(connection, username, password);
			} 
			catch (SQLException e) {
				e.printStackTrace();
	    }
		}
	
	
    private String getUserInput(String string) {
			// TODO Auto-generated method stub
			return null;
		}
	private static void u_login(Connection connection, String username, String password)throws SQLException {
    	//String InsertQuery="INSERT INTO users(username,password)"+"values(?,?)";
    	String loginQuery = "SELECT * FROM users WHERE username=? AND password=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(loginQuery)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

        // Call your login method here with username and password
        // login(connection, username, password);
        int rowsAffected = preparedStatement.executeUpdate();
	    if (rowsAffected > 0) {
	        System.out.println("Reservation Successful. " + rowsAffected + " row(s) affected.");
	    } else {
	        System.out.println("Insertion Failed!!!");
	    }

        ResultSet resultSet = preparedStatement.executeQuery();
        
        if (resultSet.next()) {
            System.out.println("Login successful");
        } else {
            System.out.println("Invalid credentials");
        }
    }catch (SQLException e) {
        e.printStackTrace();
    }
    }

}	    
