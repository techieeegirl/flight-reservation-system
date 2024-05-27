package businessLogicLayer;

public class ExceptionHandler extends Exception {

    // Constructor that accepts a message
    public ExceptionHandler(String message) {
        super(message);
    }

    /**
     * Validates the registration details of a new account.
     * Ensures that the SSN is exactly 9 digits long.
     * 
     * @param account The account object containing the registration details.
     * @throws ExceptionHandler if the SSN is not 9 digits long.
     */
    public static void checkRegistration(Account account) throws ExceptionHandler {
        String ssnString = String.valueOf(account.getSsn());
        int lengthOfSsn = ssnString.length();

        if (lengthOfSsn != 9) {
            throw new ExceptionHandler("Social Security No. must be 9 digits long.");
        }
    }
}
