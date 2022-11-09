import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;


public class Customer implements Comparable<Customer> {

    private String firstName;
    private String surname;
    private HashMap<String, Integer> activityMap;
    private int uniqueActivityCounter = 0;
    private static final int MAXNUMBEROFACTIVITIES = 3;


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

    public void boughtTickets(int tickets, String activity) {
        if (this.activityMap.get(activity) == 0) {
            uniqueActivityCounter++;
        }
        if(uniqueActivityCounter <= MAXNUMBEROFACTIVITIES) {
            int totalTickets = this.activityMap.get(activity) + tickets;
            this.activityMap.put(activity, totalTickets);
        }
    }

    public void soldTickets(int tickets, String activity) {
        int ticketsLeftover = this.activityMap.get(activity) - tickets;
        this.activityMap.put(activity, ticketsLeftover);
    }

    public void clearedTicketsFromActivity() {
        uniqueActivityCounter--;
    }

    public void notEnoughTicketsLetter(Activity activity) {
        try {
            PrintWriter outFile = new PrintWriter("letter.txt");
            outFile.write("Dear " + this.getFullName() + ",\n\nUnfortunately, there are not enough tickets left for the " +
                    "activity you've attempted to book (" + activity.getActivityName() +
                    ").\n\nYours sincerely,\n\nTicket Operator");
            outFile.flush();
            outFile.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("No file was printed.");
        }
    }

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
