import java.sql.*;
import java.util.*;


class ReservationSystem {
    Scanner sc=new Scanner(System.in);
	Connection connection;
    //Scanner scanner;
	private String u_prn;
    
//    String username;
//    String password;
//    
//    String u_Name;
//    String u_Contact;
//    String u_Class_type;
//    String u_Date_of_journey;
//    String u_From_Location;
//    String u_To_location;
//    String u_prn;


    public ReservationSystem(Connection connection) {
        this.connection = connection;
        
    }

    public void run() {
        try {
            while (true) {
                System.out.println("1. Login");
                System.out.println("2. Make Reservation");
                System.out.println("3. Cancel Reservation");
                System.out.println("4. Confirm Cancel Reservation");
                System.out.println("5. View Reservations");
                System.out.println("6. Exit");

                System.out.print("Enter your choice: ");
                int choice = sc.nextInt();
                sc.nextLine(); // Consume the newline character

                switch (choice) {
                    case 1:
                    	login ();
                        break;
                    case 2:
                    	makeReservation();
                        break;
                    case 3:
                    	System.out.println("Enter Prn :");
                    	u_prn=sc.next();
                    	cancelReservation(connection, u_prn);
                        break;
                    case 4:
                    	System.out.println("plz Confirm Cancelletion Enter PRN : ");
                    	u_prn=sc.next();
                        confirmCancellation(connection, u_prn);
                        break;
                    case 5:
                    	viewReservation(connection);
                    	break;
                    case 6:
                        System.out.println("Exiting the program. Goodbye!");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a valid option.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void login() {
    	try {
    	System.out.println("Enter UserName and Password : ");
    	System.out.println();
        String username = sc.next();
        String password = sc.next();
        System.out.println("-------------------------------------------------------------------------------");
        insert_setLogin(connection , username, password);
        System.out.println("---------------------------------------------------------------------------------");
        u_login(connection, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    public void insert_setLogin(Connection connection,String username,String password) {
    	try (PreparedStatement preparedStatement = connection.prepareStatement(
    	        "INSERT INTO users(username,password)" +
                "VALUES (?,?)")){
    		preparedStatement.setString(1, username);
    	    preparedStatement.setString(2, password);
    	    
    	    int rowsAffected = preparedStatement.executeUpdate();
    	    if (rowsAffected > 0) {
    	        System.out.println("Username and Password Set Successful. " + rowsAffected + " row(s) affected.");
    	    } else {
    	        System.out.println("Insertion Failed!!!");
    	    }
    	}catch (SQLException e) {
    	    e.printStackTrace();
        }
    	
    }
    public static void u_login(Connection connection, String username, String password)throws SQLException {
    	//String InsertQuery="INSERT INTO users(username,password)"+"values(?,?)";
    	String loginQuery = "SELECT * FROM users WHERE username=? AND password=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(loginQuery)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

        // Call your login method here with username and password
        // login(connection, username, password);
//        int rowsAffected = preparedStatement.executeUpdate();
//	    if (rowsAffected > 0) {
//	        System.out.println("login Successful. " + rowsAffected + " row(s) affected.");
//	    } else {
//	        System.out.println("Insertion Failed!!!");
//	    }

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
    public void makeReservation(){
    	System.out.println(" Enter User Details and Reserved your seat... : ");
    	System.out.println("Enter Username : ");
        String u_Name = sc.next();
        System.out.println("Enter Contact Number : ");
        String u_Contact = sc.next();
        System.out.println("Enter Class Type : ");
        String u_Class_type = sc.next();
        System.out.println("Enter Date : ");
        String u_Date_of_journey = sc.next();
        System.out.println("Enter From : ");
        String u_From_Location = sc.next();
        System.out.println("Enter To :");
        String u_To_location = sc.next();
        System.out.println("Enter Prn :");
        String u_prn = sc.next();
        System.out.println("-------------------------------------------------------------------------------");
        
        make_setReservation(connection, u_Name, u_Contact, u_Class_type, u_Date_of_journey, u_From_Location, u_To_location,u_prn);

        // Call your makeReservation method here with the provided details
        // makeReservation(connection, u_Name, u_Contact, u_Class_type, u_Date_of_journey, u_From_Location, u_To_location, u_prn);
    }
    public static void make_setReservation(Connection connection, String username, String contact, String class_type, String date_of_journey, String from_Location, String to_location,String u_prn){
    	try (PreparedStatement preparedStatement = connection.prepareStatement(
    	        "INSERT INTO reservations(passenger_name, contact, class_type, date_of_journey, from_Location, to_location, u_prn)" +
    	                "VALUES (?,?,?,?,?,?,?)")) {
    	    preparedStatement.setString(1, username);
    	    preparedStatement.setString(2, contact);
    	    preparedStatement.setString(3, class_type);
    	    preparedStatement.setString(4, date_of_journey);
    	    preparedStatement.setString(5, from_Location);
    	    preparedStatement.setString(6, to_location);
    	    preparedStatement.setString(7, u_prn);

    	    int rowsAffected = preparedStatement.executeUpdate();
    	    if (rowsAffected > 0) {
    	        System.out.println("Reservation Successful. " + rowsAffected + " row(s) affected.");
    	    } else {
    	        System.out.println("Insertion Failed!!!");
    	    }
    	} catch (SQLException e) {
    	    e.printStackTrace();
      }
    }

    public static void cancelReservation(Connection connection, String u_prn) throws SQLException {
        // Retrieve reservation details for cancellation
        String selectQuery = "SELECT * FROM reservations WHERE u_prn=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
            preparedStatement.setString(1, u_prn);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                // Display reservation details for confirmation
                System.out.println("Details for PRN " + u_prn + ": " +
                        "Passenger: " + resultSet.getString("passenger_name") +
                        ", From: " + resultSet.getString("from_location") +
                        ", To: " + resultSet.getString("to_location") +
                        ", Date: " + resultSet.getString("date_of_journey"));

                // Example: Confirm cancellation
                confirmCancellation(connection, u_prn);
            } else {
                System.out.println("Invalid PRN");
            }
        }catch (SQLException e) {
    	    e.printStackTrace();
      }
    }

    public static void confirmCancellation(Connection connection, String u_prn) throws SQLException {
        // Perform cancellation
        String deleteQuery = "DELETE FROM reservations WHERE u_prn=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
            preparedStatement.setString(1, u_prn);

            preparedStatement.executeUpdate();
            System.out.println("Cancellation  Confirm");
        }catch(Exception e) {
        	e.printStackTrace();
        }
    }
    
    private static void viewReservation(Connection connection)throws SQLException{
		String query="SELECT passenger_name,contact,class_type,date_of_journey,from_Location,to_location,u_prn FROM reservations; ";
		
		try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
			ResultSet resultSet = preparedStatement.executeQuery();
			System.out.println("Current Reservations : ");
			System.out.println("+----------------+-----------------+----------------+----------------+----------------+----------------+----------------+");
			System.out.println("| Passanger Name |      Contact    |   Class Type   |Reservation Date|      From      |       TO       |       PRN      |");
			System.out.println("+----------------+-----------------+----------------+----------------+----------------+----------------+----------------+");
			
			while(resultSet.next()) {
				String username = resultSet.getString("passenger_name");
				String contact = resultSet.getString("contact");
				String class_type = resultSet.getString("class_type");
				String date_of_journey = resultSet.getString("date_of_journey");
				String from_Location = resultSet.getString("from_Location");
				String to_location = resultSet.getString("to_location");
				String u_prn = resultSet.getString("u_prn").toString();
				
				System.out.printf("| %14s | %15s | %14s | %14s | %14s | %14s | %14s |\n",
						username,contact,class_type,date_of_journey,from_Location,to_location,u_prn);
			}
			
			System.out.println("+----------------+-----------------+----------------+----------------+----------------+----------------+----------------+");
			
		}catch(Exception e) {
        	e.printStackTrace();
        }
	}
	

    
}
