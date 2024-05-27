package businessLogicLayer;

import databaseInterfaceLayer.InsertDBO;
import java.util.Random;

/**
 * The Account class represents a user account in the system.
 * It implements the Comparable interface to allow comparison based on SSN.
 */
public class Account implements Comparable<Account> {

    // Fields for account information
    private int accountID;
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private String address;
    private String state;
    private String email;
    private int zipCode;
    private int ssn;
    private String securityQuestion;
    private String securityAnswer;
    public static boolean is_Admin = false;

    /**
     * Default constructor.
     */
    public Account() {}

    /**
     * Constructor with username and password.
     * @param username the username of the account.
     * @param password the password of the account.
     */
    public Account(String username, String password) {
        this.userName = username;
        this.password = password;
    }

    /**
     * Full constructor to initialize all fields of the account.
     * @param acct_id the account ID.
     * @param userName the username of the account.
     * @param password the password of the account.
     * @param firstname the first name of the account holder.
     * @param lastName the last name of the account holder.
     * @param address the address of the account holder.
     * @param State the state of the account holder.
     * @param email the email of the account holder.
     * @param zipCode the zip code of the account holder.
     * @param ssn the social security number of the account holder.
     * @param sq the security question.
     * @param sa the answer to the security question.
     */
    public Account(int acct_id, String userName, String password, String firstname, String lastName, String address,
                   String State, String email, int zipCode, int ssn, String sq, String sa) {
        this.accountID = acct_id;
        this.userName = userName;
        this.password = password;
        this.firstName = firstname;
        this.lastName = lastName;
        this.address = address;
        this.state = State;
        this.email = email;
        this.zipCode = zipCode;
        this.ssn = ssn;
        this.securityQuestion = sq;
        this.securityAnswer = sa;
    }

    /**
     * Generates a new account and inserts it into the database.
     * @param fname the first name of the account holder.
     * @param lname the last name of the account holder.
     * @param address the address of the account holder.
     * @param email the email of the account holder.
     * @param state the state of the account holder.
     * @param zip the zip code of the account holder.
     * @param ssn the social security number of the account holder.
     * @param un the username.
     * @param pword the password.
     * @param secQuestion the security question.
     * @param sa the answer to the security question.
     */
    public static void generateAccount(String fname, String lname, String address, String email, String state, int zip,
                                       int ssn, String un, String pword, String secQuestion, String sa) {
        int id = makeAccountID();
        Account acct = new Account(id, un, pword, fname, lname, address, state, email, zip, ssn, secQuestion, sa);
        InsertDBO input = new InsertDBO();
        input.insertAccount(acct);
    }

    // Getters and Setters
    public int getAccountID() { return accountID; }
    public void setaccount_id(int account_id) { this.accountID = account_id; }
    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getState() { return state; }
    public void setState(String state) { this.state = state; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public int getZipCode() { return zipCode; }
    public void setZipCode(int zipCode) { this.zipCode = zipCode; }
    public int getSsn() { return ssn; }
    public void setSsn(int ssn) { this.ssn = ssn; }
    public String getSecurityQuestion() { return securityQuestion; }
    public void setSecurityQuestion(String securityQuestion) { this.securityQuestion = securityQuestion; }
    public String getSecurityAnswer() { return securityAnswer; }
    public void setSecurityAnswer(String securityAnswer) { this.securityAnswer = securityAnswer; }

    /**
     * Generates a random account ID.
     * @return a random account ID.
     */
    public static int makeAccountID() {
        Random rand = new Random();
        return rand.nextInt(99999);
    }

    @Override
    public String toString() {
        return "\nAccount ID: " + getAccountID() + "\nUsername: " + this.getUserName() + "\nFirst name: " + this.getFirstName() +
               "\nLast name: " + this.getLastName();
    }

    @Override
    public int compareTo(Account a) {
        return Integer.compare(this.ssn, a.getSsn());
    }
}
