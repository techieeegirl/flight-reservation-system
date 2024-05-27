package databaseInterfaceLayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import businessLogicLayer.Account;
import businessLogicLayer.Booking;
import businessLogicLayer.Flight;

public class RetrieveDBO {

    // Database connection details
    static final String databaseURL = "jdbc:mysql://localhost:3306/JavaJesusDB";
    static final String databaseUsername = "root";
    static final String databasePassword = "1234abcd";

    /**
     * Retrieves an account from the database based on the username.
     * @param username the username to search for.
     * @return the account associated with the given username.
     */
    public static Account retrieveAccount(String username) {
        Account account = new Account();

        try {
            // Load the JDBC driver
            Class.forName("java.sql.Driver");

            // Establish a connection to the database
            Connection connection = DriverManager.getConnection(databaseURL, databaseUsername, databasePassword);

            // SQL query to retrieve account details
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM account WHERE username=?");
            preparedStatement.setString(1, username);

            ResultSet res = preparedStatement.executeQuery();

            // Populate account details
            while (res.next()) {
                account.setAccountID(res.getInt("account_id"));
                account.setUserName(res.getString("username"));
                account.setPassword(res.getString("password"));
                account.setFirstName(res.getString("firstname"));
                account.setLastName(res.getString("lastname"));
                account.setAddress(res.getString("address"));
                account.setState(res.getString("state"));
                account.setEmail(res.getString("email"));
                account.setZipCode(res.getInt("zipcode"));
                account.setSsn(res.getInt("ssn"));
                account.setSecurityQuestion(res.getString("security_q"));
                account.setSecurityAnswer(res.getString("security_a"));
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return account;
    }

    /**
     * Retrieves all flights from the database.
     * @return an ObservableList of all flights.
     */
    public static ObservableList<Flight> getFlights() {
        ObservableList<Flight> flights = FXCollections.observableArrayList();

        try {
            // Load the JDBC driver
            Class.forName("java.sql.Driver");

            // Establish a connection to the database
            Connection connection = DriverManager.getConnection(databaseURL, databaseUsername, databasePassword);

            // SQL query to retrieve all flights
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM flights");

            ResultSet rs = preparedStatement.executeQuery();

            // Populate flight details
            while (rs.next()) {
                Flight flight = new Flight();
                flight.setFlightNum(rs.getInt("flight_number"));
                flight.setDepartureCity(rs.getString("departure_city"));
                flight.setDestinationCity(rs.getString("destination_city"));
                flight.setDepartTime(rs.getString("depart_time"));
                flight.setArriveTime(rs.getString("arrive_time"));
                flight.setFlightDate(rs.getString("flight_date"));
                flight.setReturnFlight(rs.getString("return_day"));
                flight.setNumberOfSeats(rs.getInt("num_seats"));
                flights.add(flight);
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flights;
    }

    /**
     * Retrieves bookings from the database for a specific account ID.
     * @param accountID the account ID to search for.
     * @return an ObservableList of bookings associated with the account ID.
     */
    public static ObservableList<Booking> retrieveBookings(int accountID) {
        ObservableList<Booking> bookings = FXCollections.observableArrayList();

        try {
            // Load the JDBC driver
            Class.forName("java.sql.Driver");

            // Establish a connection to the database
            Connection connection = DriverManager.getConnection(databaseURL, databaseUsername, databasePassword);

            // SQL query to retrieve bookings for the given account ID
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM bookedflights WHERE account_id=?");
            preparedStatement.setInt(1, accountID);

            ResultSet res = preparedStatement.executeQuery();

            // Populate booking details
            while (res.next()) {
                Booking booking = new Booking(
                    res.getInt("ticket_number"),
                    res.getInt("flight_number"),
                    res.getString("flight_date"),
                    res.getString("flight_time"),
                    res.getString("departCity"),
                    res.getString("destinationCity"),
                    res.getString("return_flight_date")
                );
                bookings.add(booking);
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bookings;
    }

    /**
     * Retrieves a flight from the database based on the flight number.
     * @param flightNum the flight number to search for.
     * @return the flight associated with the given flight number.
     */
    public static Flight retrieveFlight(int flightNum) {
        Flight flight = new Flight();

        try {
            // Load the JDBC driver
            Class.forName("java.sql.Driver");

            // Establish a connection to the database
            Connection connection = DriverManager.getConnection(databaseURL, databaseUsername, databasePassword);

            // SQL query to retrieve flight details for the given flight number
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM flights WHERE flight_number=?");
            preparedStatement.setInt(1, flightNum);

            ResultSet rs = preparedStatement.executeQuery();

            // Populate flight details
            while (rs.next()) {
                flight.setFlightNum(rs.getInt("flight_number"));
                flight.setDepartureCity(rs.getString("departure_city"));
                flight.setDestinationCity(rs.getString("destination_city"));
                flight.setDepartTime(rs.getString("depart_time"));
                flight.setArriveTime(rs.getString("arrive_time"));
                flight.setFlightDate(rs.getString("flight_date"));
                flight.setReturnFlight(rs.getString("return_day"));
                flight.setNumberOfSeats(rs.getInt("num_seats"));
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flight;
    }
}
