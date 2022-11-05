import java.util.HashMap;

public class Customer implements Comparable<Customer> {

    private String firstName;
    private String surname;
    private int numberOfActivities;
    /*
     do I even need to split this by first name and last name?
     - Yes in order to sort lexicographically by surname first
     */
    private HashMap<String, Integer> chosenActivities;
    /* TODO: do I need a counter field to increment/decrement the number of activities booked?
        TODO: Would this be better than counting the number of values in the Hashmap?
     */

    public Customer(String firstName, String surname, HashMap<String, Integer> chosenActivities) {
        this.firstName = firstName;
        this.surname = surname;
        this.chosenActivities = chosenActivities;
        numberOfActivities = 0;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public HashMap<String, Integer> getChosenActivities() {
        return chosenActivities;
    }

    public void setChosenActivities(HashMap<String, Integer> chosenActivities) {
        this.chosenActivities = chosenActivities;
    }

    @Override
    //TODO: Add the logic in here
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
