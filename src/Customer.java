import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

/**
 * This class stores customers and the activities associated with them, i.e. which tickets they have bought.
 * We use a hashmap to store the number of tickets bought for each customer, and this is by definition unordered.
 * We also count the number of different activities a customer has booked here.
 *
 * @author Tahir Khan
 */

public class Customer implements Comparable<Customer> {

    private String firstName;
    private String surname;
    private HashMap<String, Integer> activityMap;
    private int uniqueActivityCounter = 0;
    private static final int MAX_NUMBER_OF_ACTIVITIES = 3;


    public Customer(String firstName, String surname, HashMap<String, Integer> activityMap) {
        this.firstName = firstName;
        this.surname = surname;
        this.activityMap = activityMap;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return this.surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getFullName() {
        return this.firstName + " " + this.surname;
    }

    public HashMap<String, Integer> getActivityMap() {
        return this.activityMap;
    }

    public void setActivityMap(HashMap<String, Integer> activityMap) {
        this.activityMap = activityMap;
    }

    public int getUniqueActivityCounter() {
        return this.uniqueActivityCounter;
    }

    public void setUniqueActivityCounter(int uniqueActivityCounter) {
        this.uniqueActivityCounter = uniqueActivityCounter;
    }

    /**
     * Checks to see if a customer has more than MAX_NUMBER_OF_ACTIVITIES activities in their hashmap
     * Then updates the hashmap if it's a valid transaction
     * @param tickets the number of tickets requested by the clerk
     * @param activity the name of the activity requested by the clerk
     */
    public void boughtTickets(int tickets, String activity) {
        if (this.activityMap.get(activity) == 0) {
            uniqueActivityCounter++;
        }
        if(uniqueActivityCounter <= MAX_NUMBER_OF_ACTIVITIES) {
            int totalTickets = this.activityMap.get(activity) + tickets;
            this.activityMap.put(activity, totalTickets);
        }
    }

    /**
     * Updates the customer's hashmap when a ticket is sold
     * @param tickets the number of tickets requested by the clerk
     * @param activity the name of the activity requested by the clerk
     */
    public void soldTickets(int tickets, String activity) {
        int ticketsLeftover = this.activityMap.get(activity) - tickets;
        this.activityMap.put(activity, ticketsLeftover);
    }

    /**
     * Decrements the counter for when a customer sells all of their tickets for a single activity
     */
    public void clearedTicketsFromActivity() {
        uniqueActivityCounter--;
    }

    /**
     * prints a letter when we don't have enough tickets for an activity
     * @param activity the activity requested which we don't have enough tickets for
     */
    public void notEnoughTicketsLetter(Activity activity) {
        try {
            PrintWriter outFile = new PrintWriter(new FileWriter("letter.txt", true));
            outFile.write("Dear " + this.getFullName() + ",\n\nUnfortunately, there are not enough tickets left for the " +
                    "activity you've attempted to book (" + activity.getActivityName() +
                    ").\n\nYours sincerely,\n\nTicket Operator\n\n\n");
            outFile.flush();
            outFile.close();
        }
        catch (IOException e) {
            System.out.println("No file was printed.");
        }
    }

    /**
     * CompareTo method override to allow sorting by the SortedArrayList
     * @param customer the object to be compared.
     * @return positive, negative or 0
     */
    @Override
    public int compareTo(Customer customer) {
            int lastNameComparison = this.surname.compareTo(customer.surname);
            if (lastNameComparison !=0) {
                return lastNameComparison;
            }
            int firstNameComparison = this.firstName.compareTo(customer.firstName);
            if (firstNameComparison != 0) {
                return firstNameComparison;
            }
            else return 0;
    }

    @Override
    public String toString() {
        return this.firstName + " " + this.surname + " has " + this.uniqueActivityCounter + " activities booked ";
    }
}
