package databaseInterfaceLayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import businessLogicLayer.Flight;

public class searchDBO {
    // Database connection details
    static final String databaseURL = "jdbc:mysql://localhost:3306/JavaJesusDB";
    static final String databaseUsername = "root";
    static final String databasePassword = "1234abcd";

    /**
     * Searches for flights based on departure city, destination city, departure date, and return date.
     * @param departFrom the departure city.
     * @param arriveTo the destination city.
     * @param departDay the departure date.
     * @param returnDay the return date.
     * @return an ObservableList of flights matching the search criteria.
     */
    public static ObservableList<Flight> findFlights(String departFrom, String arriveTo, String departDay, String returnDay) {
        ObservableList<Flight> flights = FXCollections.observableArrayList();

        try {
            // Load the JDBC driver
            Class.forName("java.sql.Driver");

            // Establish a connection to the database
            Connection connection = DriverManager.getConnection(databaseURL, databaseUsername, databasePassword);

            // SQL query to search for flights based on the criteria
            String query = "SELECT * FROM flights WHERE departure_city = ? AND destination_city = ? AND flight_date = ? AND return_day = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, departFrom);
            preparedStatement.setString(2, arriveTo);
            preparedStatement.setString(3, departDay);
            preparedStatement.setString(4, returnDay);

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
        } catch (Exception ex) {
            System.out.println("Something went wrong with the database.");
            ex.printStackTrace();
        }
        return flights;
    }
}
