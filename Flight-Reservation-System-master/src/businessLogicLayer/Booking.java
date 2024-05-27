package businessLogicLayer;

import java.util.Random;
import graphicUserInterface.Login;
import databaseInterfaceLayer.InsertDBO;
import databaseInterfaceLayer.RetrieveDBO;

/**
 * The Booking class represents a booking made by a passenger.
 * It includes details about the flight, the account making the booking, and the ticket information.
 */
public class Booking {

    private int ticketNumber;
    private int account_id;
    private int flight_number;
    private String flight_date;
    private String flight_time;
    private String departeCity;
    private String destinationCity;
    private String passenger_userName;
    private String returnFlight;

    /**
     * Default constructor.
     */
    public Booking() {
    }

    /**
     * Full constructor for creating a Booking instance.
     * @param ticket_num the ticket number.
     * @param acctID the account ID.
     * @param flightNum the flight number.
     * @param flyDate the flight date.
     * @param flyTime the flight time.
     * @param departCity the departure city.
     * @param destinCity the destination city.
     * @param pass_username the username of the passenger.
     * @param returnFly the return flight information.
     */
    public Booking(int ticket_num, int acctID, int flightNum, String flyDate,
                   String flyTime, String departCity, String destinCity, String pass_username, String returnFly) {
        this.ticketNumber = ticket_num;
        this.account_id = acctID;
        this.flight_number = flightNum;
        this.flight_date = flyDate;
        this.flight_time = flyTime;
        this.passenger_userName = pass_username;
        this.returnFlight = returnFly;
        this.departeCity = departCity;
        this.destinationCity = destinCity;
    }

    /**
     * Constructor for creating a Booking instance without account information.
     * @param ticket the ticket number.
     * @param flight the flight number.
     * @param fdate the flight date.
     * @param ftime the flight time.
     * @param depCity the departure city.
     * @param desCity the destination city.
     * @param rFlight the return flight information.
     */
    public Booking(int ticket, int flight, String fdate, String ftime, String depCity, String desCity, String rFlight) {
        this.ticketNumber = ticket;
        this.flight_number = flight;
        this.flight_date = fdate;
        this.flight_time = ftime;
        this.departeCity = depCity;
        this.destinationCity = desCity;
        this.returnFlight = rFlight;
    }

    /**
     * Combines flight and account information to create a booking and inserts it into the database.
     * @param flightNumber the flight number.
     */
    public static void bookFlight(int flightNumber) {
        int ticketNum = makeTicketNumber();

        Flight toBook = RetrieveDBO.retrieveFlight(flightNumber);

        Booking booking = new Booking(ticketNum, Login.currentAccount.getAccountID(),
                flightNumber, toBook.getFlightDate(), toBook.getDepartTime(),
                toBook.getDepartureCity(), toBook.getDestinationCity(),
                Login.currentAccount.getUserName(),
                toBook.getReturnFlight());

        InsertDBO.insertBooking(booking);
    }

    public int getAccount_id() {
        return account_id;
    }

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }

    public int getFlight_number() {
        return flight_number;
    }

    public void setFlight_number(int flight_number) {
        this.flight_number = flight_number;
    }

    public int getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNum(int ticketNum) {
        this.ticketNumber = ticketNum;
    }

    public String getFlight_date() {
        return flight_date;
    }

    public void setFlight_date(String flightdate) {
        this.flight_date = flightdate;
    }

    public String getFlight_time() {
        return flight_time;
    }

    public void setFlight_time(String flight_time) {
        this.flight_time = flight_time;
    }

    public String getPassenger_userName() {
        return passenger_userName;
    }

    public void setPassenger_userName(String passenger_userName) {
        this.passenger_userName = passenger_userName;
    }

    public String getReturnFlight() {
        return returnFlight;
    }

    public void setReturnFlight(String returnFlight) {
        this.returnFlight = returnFlight;
    }

    public String getDeparteCity() {
        return departeCity;
    }

    public void setDeparteCity(String departeCity) {
        this.departeCity = departeCity;
    }

    public String getDestinationCity() {
        return destinationCity;
    }

    public void setDestinationCity(String destinationCity) {
        this.destinationCity = destinationCity;
    }

    /**
     * Generates a random ticket number.
     * @return a random ticket number.
     */
    public static int makeTicketNumber() {
        Random rand = new Random();
        return rand.nextInt(999);
    }
}
