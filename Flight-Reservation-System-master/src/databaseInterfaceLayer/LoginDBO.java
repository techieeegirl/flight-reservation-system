ppackage databaseInterfaceLayer;

import java.sql.*;
import businessLogicLayer.ExceptionHandler;

public class LoginDBO {

    // Database connection details
    static final String databaseURL = "jdbc:mysql://localhost:3306/JavaJesusDB";
    static final String databaseUsername = "root";
    static final String databasePassword = "1234abcd";
    static Connection connection;

    /**
     * Retrieves the password for a given username.
     * @param username the username to search for.
     * @return the password associated with the username, or an empty string if not found.
     */
    public String loginConn(String username) {
        String result = "";

        try {
            // Load the JDBC driver
            Class.forName("java.sql.Driver");

            System.out.println("Database connected!");

            // Establish a connection to the database
            connection = DriverManager.getConnection(databaseURL, databaseUsername, databasePassword);

            // SQL query to retrieve the password for the given username
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM account WHERE username=?");
            preparedStatement.setString(1, username);

            ResultSet res = preparedStatement.executeQuery();

            // Retrieve the password if the user exists
            if (res.next()) {
                result = res.getString("password");
            }

            // Close the connection
            connection.close();
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println("Something went wrong with the database :(");
            ex.printStackTrace();
        }

        return result;
    }

    /**
     * Checks if an account with the given email exists in the database.
     * @param email the email to search for.
     * @return true if the account exists, false otherwise.
     */
    public Boolean searchFor(String email) {
        Boolean result = false;
        int check = 0;

        try {
            // Establish a connection to the database
            connection = DriverManager.getConnection(databaseURL, databaseUsername, databasePassword);

            // SQL query to check if an account with the given email exists
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM account WHERE username=?");
            preparedStatement.setString(1, email);

            ResultSet res = preparedStatement.executeQuery();

            // Check if the result set contains any records
            if (res.next()) {
                check = res.getInt("ssn");
            }

            // Close the connection
            connection.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        // Determine the result based on the query outcome
        result = check > 0;
        return result;
    }

    /**
     * Retrieves the password for an account based on email and security answer.
     * @param email the email associated with the account.
     * @param security_A the answer to the security question.
     * @return a message indicating the password or an error message.
     */
    public String returnPassword(String email, String security_A) {
        String result = "";
        String correctAns = "";
        String pword = "";

        try {
            // Establish a connection to the database
            connection = DriverManager.getConnection(databaseURL, databaseUsername, databasePassword);

            // SQL query to retrieve account details for the given email
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM account WHERE email=?");
            preparedStatement.setString(1, email);

            ResultSet res = preparedStatement.executeQuery();

            // Retrieve the password and security answer if the account exists
            if (res.next()) {
                pword = res.getString("password");
                correctAns = res.getString("security_a");
            }

            // Compare the provided answer with the correct answer
            if (!security_A.equals(correctAns)) {
                result = "Incorrect answer to security question";
            } else if (security_A.equals(correctAns)) {
                result = "Your password is: " + pword;
            } else {
                result = "I'm sorry, we don't have an account with that email address";
            }

            // Close the connection
            connection.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return result;
    }
}
