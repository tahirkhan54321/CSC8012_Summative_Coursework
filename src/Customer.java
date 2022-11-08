import java.util.HashMap;

public class Customer implements Comparable<Customer> {

    private String firstName;
    private String surname;
    private HashMap<String, Integer> chosenActivities;
    private int uniqueActivityCounter = 0;
    private static final int MAXNUMBEROFACTIVITIES = 3;

    public Customer(String firstName, String surname, HashMap<String, Integer> chosenActivities) {
        this.firstName = firstName;
        this.surname = surname;
        this.chosenActivities = chosenActivities;
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

    public HashMap<String, Integer> getChosenActivities() {
        return this.chosenActivities;
    }

    public void setChosenActivities(HashMap<String, Integer> chosenActivities) {
        this.chosenActivities = chosenActivities;
    }

    public int getUniqueActivityCounter() {
        return this.uniqueActivityCounter;
    }

    public void setUniqueActivityCounter(int uniqueActivityCounter) {
        this.uniqueActivityCounter = uniqueActivityCounter;
    }

    public void boughtTickets(int tickets, String activity) {
        if (this.chosenActivities.get(activity) == 0) {
            uniqueActivityCounter++;
        }
        if(uniqueActivityCounter <= MAXNUMBEROFACTIVITIES) {
            int totalTickets = tickets + this.chosenActivities.get(activity);
            this.chosenActivities.put(activity, totalTickets);
        }
    }

    //TODO: add soldTickets method

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
    //TODO: Make this an appropriate format for output
    public String toString() {
        return "Customer{" +
                "firstName='" + firstName + '\'' +
                ", surname='" + surname + '\'' +
                ", chosenActivities=" + chosenActivities +
                '}';
    }


}
