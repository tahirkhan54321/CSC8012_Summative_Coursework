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
    private int uniqueActivityCounter = 0;

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

    public void boughtTickets(int tickets, String activity) {
        if(uniqueActivityCounter < 3) {
            int totalTickets = tickets + this.chosenActivities.get(activity);
            this.chosenActivities.put(activity, totalTickets);
        } else {
            System.out.println("You can't buy tickets for more activities, this request has not been processed.");
        }
        if (chosenActivities.get(activity) == 0) {
            uniqueActivityCounter++;
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
    //TODO: Make this an appropriate format for output
    public String toString() {
        return "Customer{" +
                "firstName='" + firstName + '\'' +
                ", surname='" + surname + '\'' +
                ", chosenActivities=" + chosenActivities +
                '}';
    }


}
