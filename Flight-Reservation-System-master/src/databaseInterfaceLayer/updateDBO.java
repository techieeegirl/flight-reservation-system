package databaseInterfaceLayer;

import java.sql.*;
package databaseInterfaceLayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class updateDBO {

    // Database connection details
    static final String databaseURL = "jdbc:mysql://localhost:3306/JavaJesusDB";
    static final String databaseUsername = "root";
    static final String databasePassword = "1234abcd";
    static Connection connection;
    public static Boolean result;

    /**
     * Deletes a flight from the database based on the flight number.
     * @param flightNum the flight number of the flight to be deleted.
     */
    public static void deleteFlight(int flightNum) {
        result = false;
        try {
            // Load the JDBC driver
            Class.forName("java.sql.Driver");

            // Establish a connection to the database
            Connection connection = DriverManager.getConnection(databaseURL, databaseUsername, databasePassword);

            // SQL query to delete a flight
            String query = "DELETE FROM flights WHERE flight_number = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, flightNum);

            // Execute the update
            preparedStatement.executeUpdate();

            connection.close();
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
            result = false;
        }
    }

    /**
     * Deletes a booking from the database based on the ticket number.
     * @param ticketNumber the ticket number of the booking to be deleted.
     */
    public static void deleteBooking(int ticketNumber) {
        result = false;
        try {
            // Load the JDBC driver
            Class.forName("java.sql.Driver");

            // Establish a connection to the database
            Connection connection = DriverManager.getConnection(databaseURL, databaseUsername, databasePassword);

            // SQL query to delete a booking
            String query = "DELETE FROM bookedflights WHERE ticket_number = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, ticketNumber);

            // Execute the update
            preparedStatement.executeUpdate();

            connection.close();
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
            result = false;
        }
    }
}
