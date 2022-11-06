import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        ArrayList<Activity> activities = new ArrayList<Activity>();
        SortedArrayList<Activity> sortedActivities = new SortedArrayList<Activity>(activities);
        HashMap<String, Integer> eachCustomerActivities = new HashMap<>();
        ArrayList<Customer> sortedCustomers = new ArrayList<>();

        try (
                Scanner fileReader = new Scanner(Paths.get("input.txt"))) {

            int numberOfActivities = Integer.parseInt(fileReader.nextLine()); //taking the first line to use in a for loop
            System.out.println("Number of activities registered: " + numberOfActivities);
            /*
            for loop to add activities to the arraylist and builds a hashmap of activities which can be used in the customer object
             */
            for (int i = 0; i < numberOfActivities; i++) {
                String activityName = fileReader.nextLine();
                int activityTickets = Integer.parseInt(fileReader.nextLine());
                Activity activityElement = new Activity(activityName, activityTickets);

                sortedActivities.addElement(sortedActivities, activityElement);
                eachCustomerActivities.put(activityName,0);
            }

            // start populating the customers arraylist
            int numberOfCustomers = Integer.parseInt(fileReader.nextLine());
            /*
            for loop to add customers to the arraylist
             */
            for (int i = 0; i < numberOfCustomers; i++) {
                String line = fileReader.nextLine();
                String fullName[] = line.split(" ");
                String firstName = fullName[0];
                String surname = fullName[1];
                Customer customer = new Customer(firstName, surname, eachCustomerActivities);
                //customers.add(customer);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace(); //TODO: change this to something more meaningful
        }

        /*
        TODO: Should I sort the lists at this point?
         Yes, I'll need the sorted lists in order to confirm that activity/customer exist efficiently
         */
        //test to see whether all activities/customers are registered to arraylists - pass x2
        System.out.println(sortedActivities);
        //System.out.println(customers);

        /*
        while (true) {
            //customer choices
            System.out.println("Select an option:");
            System.out.println("f - to finish running the program.");
            System.out.println("a - to display on the screen information about all the activities.");
            System.out.println("c - to display on the screen information about all the customers.");
            System.out.println("t - to update the stored data when tickets are bought by one of the registered customers.");
            System.out.println("r - to update the stored data when a registered customer cancels tickets for a booking.");
            String userChoice = scanner.nextLine();

            if (userChoice.equals("f")) {
                break;
            } else if (userChoice.equals("a")) {
                activities.toString();
            } else if (userChoice.equals("c")) {
                customers.toString();
            } else if (userChoice.equals("t")) {
                // TODO: finish logic in here
                String foundName;
                System.out.println("What is your name?");
                String nameLine = scanner.nextLine();
                String fullName[] = nameLine.split(" ");
                String firstName = fullName[0];
                String surname = fullName[1];
                for (Customer customer : customers) {  //TODO: can this be replaced with a binary search once the list is sorted?
                    if (customer.getFirstName().equals(fullName) && customer.getSurname().equals(surname)) {
                        foundName = fullName + " " + surname;
                    } else {
                        //TODO: print some kind of letter or return something.
                    }
                }
                System.out.println("Which activity would you like to purchase tickets for?");
                String activityName = scanner.nextLine();
                //TODO: add a searching algorithm here to find the activity stored in activityName

            } else if (userChoice.equals("r")) {
                //TODO: add logic in here
            } else {
                System.out.println("You have selected an invalid option, try again");
            }
        }

         */
    }

}