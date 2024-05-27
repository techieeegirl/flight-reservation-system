package businessLogicLayer;

/**
 * The Admin class represents an administrative account in the system.
 * It extends the Account class and sets the is_Admin field to true.
 */
public class Admin extends Account {

    // Admin status is always true for Admin accounts
    public final static Boolean is_Admin = true;

    /**
     * Constructor to create an Admin account with a username and password.
     * @param username the username of the admin.
     * @param password the password of the admin.
     */
    public Admin(String username, String password) {
        super(username, password);
    }

    /**
     * Full constructor to initialize all fields of the Admin account.
     * @param acct_id the account ID.
     * @param userName the username of the admin.
     * @param password the password of the admin.
     * @param firstname the first name of the admin.
     * @param lastName the last name of the admin.
     * @param address the address of the admin.
     * @param State the state of the admin.
     * @param email the email of the admin.
     * @param zipCode the zip code of the admin.
     * @param ssn the social security number of the admin.
     * @param sq the security question.
     * @param sa the answer to the security question.
     */
    public Admin(int acct_id, String userName, String password, String firstname, String lastName, String address,
                 String State, String email, int zipCode, int ssn, String sq, String sa) {
        super(acct_id, userName, password, firstname, lastName, address, State, email, zipCode, ssn, sq, sa);
    }

    /**
     * Returns a string representation of the Admin account.
     * @return a string containing the admin's username, password, and admin status.
     */
    @Override
    public String toString() {
        return "\nUsername: " + this.getUserName() + "\nPassword: " + this.getPassword() 
               + "\nAdmin Status: " + is_Admin;
    }
}
