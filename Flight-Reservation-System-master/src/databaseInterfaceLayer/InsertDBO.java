package databaseInterfaceLayer;

import java.sql.*;
import java.util.ArrayList;
import businessLogicLayer.Account;
import businessLogicLayer.Flight;
import graphicUserInterface.AlertBox;
import businessLogicLayer.Booking;

public class InsertDBO {
    // Database connection details
    static final String databaseURL = "jdbc:mysql://localhost:3306/JavaJesusDB";
    static final String databaseUsername = "root";
    static final String databasePassword = "1234abcd";

    public static Boolean success;
    public ArrayList<Object> returnList;

    /**
     * Inserts a new flight record into the database.
     * @param flight the flight object containing flight details.
     */
    public static void insertFlight(Flight flight) {
        success = false;
        try {
            // Load the JDBC driver
            Class.forName("java.sql.Driver");

            System.out.println("Database connected!");

            // Establish a connection to the database
            Connection connection = DriverManager.getConnection(databaseURL, databaseUsername, databasePassword);

            // SQL query to insert a new flight
            String sqlQuery = "INSERT INTO flights(flight_number, departure_city, destination_city, depart_time, " +
                              "arrive_time, flight_date, num_seats, return_day) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);

            // Set the parameters for the query
            preparedStatement.setInt(1, flight.getFlightNumber());
            preparedStatement.setString(2, flight.getDepartureCity());
            preparedStatement.setString(3, flight.getDestinationCity());
            preparedStatement.setString(4, flight.getDepartTime());
            preparedStatement.setString(5, flight.getArriveTime());
            preparedStatement.setString(6, flight.getFlightDate());
            preparedStatement.setInt(7, flight.getNumberOfSeats());
            preparedStatement.setString(8, flight.getReturnFlight());

            // Execute the update
            preparedStatement.executeUpdate();

            // Close the connection
            connection.close();
            success = true;
        } catch (Exception e) {
            System.out.println("An error occurred while connecting to the database!");
            e.printStackTrace();
            success = false;
        }
    }

    /**
     * Inserts a new booking into the database and updates the flight's available seats.
     * @param booking the booking object containing booking details.
     */
    public static void insertBooking(Booking booking) {
        try {
            // Load the JDBC driver
            Class.forName("java.sql.Driver");

            System.out.println("Database connected!");

            // Establish a connection to the database
            Connection connection = DriverManager.getConnection(databaseURL, databaseUsername, databasePassword);

            // SQL query to insert a new booking
            String sqlQuery = "INSERT INTO bookedflights(ticket_number, flight_number, account_id, flight_date, " +
                              "flight_time, departCity, destinationCity, passenger_username, return_flight_Date) " +
                              "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";

            // SQL query to update the number of available seats for the flight
            String updateSeatsSql = "UPDATE flights SET num_seats = num_seats - 1 WHERE flight_number = ?";

            PreparedStatement updateSeatsStmt = connection.prepareStatement(updateSeatsSql);
            updateSeatsStmt.setInt(1, booking.getFlight_number());
            updateSeatsStmt.executeUpdate();

            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);

            // Set the parameters for the query
            preparedStatement.setInt(1, booking.getTicketNumber());
            preparedStatement.setInt(2, booking.getFlight_number());
            preparedStatement.setInt(3, booking.getAccount_id());
            preparedStatement.setString(4, booking.getFlight_date());
            preparedStatement.setString(5, booking.getFlight_time());
            preparedStatement.setString(6, booking.getDeparteCity());
            preparedStatement.setString(7, booking.getDestinationCity());
            preparedStatement.setString(8, booking.getPassenger_userName());
            preparedStatement.setString(9, booking.getReturnFlight());

            // Execute the update
            preparedStatement.executeUpdate();

            // Close the connection
            connection.close();

            // Display confirmation message
            AlertBox.display("Thank you for flying with us", "Your reservation is for flight: " + booking.getFlight_number()
                             + "\nTicket number is: " + booking.getTicketNumber());

        } catch (SQLIntegrityConstraintViolationException e1) {
            // Handle duplicate booking error
            AlertBox.display("Duplicate Booking Alert!", "You already have a reservation for this flight. " +
                            "Please verify your flight number.");
            e1.printStackTrace();
        } catch (SQLException e2) {
            // Handle SQL syntax error
            System.out.println("SQL syntax error");
        } catch (ClassNotFoundException e) {
            // Handle class not found error
            e.printStackTrace();
        }
    }

    /**
     * Inserts a new account into the database.
     * @param account the account object containing account details.
     */
    public void insertAccount(Account account) {
        success = false;
        try {
            // Load the JDBC driver
            Class.forName("java.sql.Driver");

            System.out.println("Database connected!");

            // Establish a connection to the database
            Connection connection = DriverManager.getConnection(databaseURL, databaseUsername, databasePassword);

            // SQL query to insert a new account
            String sqlQuery = "INSERT INTO account(account_id, username, password, firstname, lastname, address, " +
                              "state, email, zipcode, ssn, security_q, security_a, is_Admin) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);

            // Set the parameters for the query
            preparedStatement.setInt(1, account.getAccountID());
            preparedStatement.setString(2, account.getUserName());
            preparedStatement.setString(3, account.getPassword());
            preparedStatement.setString(4, account.getFirstName());
            preparedStatement.setString(5, account.getLastName());
            preparedStatement.setString(6, account.getAddress());
            preparedStatement.setString(7, account.getState());
            preparedStatement.setString(8, account.getEmail());
            preparedStatement.setInt(9, account.getZipCode());
            preparedStatement.setInt(10, account.getSsn());
            preparedStatement.setString(11, account.getSecurityQuestion());
            preparedStatement.setString(12, account.getSecurityAnswer());
            preparedStatement.setBoolean(13, Account.is_Admin);

            // Execute the update
            preparedStatement.executeUpdate();

            // Close the connection
            connection.close();
            success = true;

        } catch (SQLIntegrityConstraintViolationException ex) {
            // Handle duplicate account error
            AlertBox.display("Duplicate Account Alert!", "An account already exists with your email address. " +
                            "Please try again or use the password recovery system.");
            success = false;
        } catch (SQLException e1) {
            // Handle SQL error
            e1.printStackTrace();
        } catch (ClassNotFoundException e2) {
            // Handle class not found error
            System.out.println("An error occurred while connecting to the database!");
            e2.printStackTrace();
        }
    }
}
