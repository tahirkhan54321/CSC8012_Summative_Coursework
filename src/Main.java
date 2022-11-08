import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        SortedArrayList<Activity> sortedActivities = new SortedArrayList<Activity>();
        SortedArrayList<Customer> sortedCustomers = new SortedArrayList<Customer>();
        HashMap<String, Integer> eachCustomerActivities = new HashMap<>();
        ArrayList<Activity> activities = new ArrayList<Activity>();
        ArrayList<Customer> customers = new ArrayList<Customer>();

        try (
                Scanner fileReader = new Scanner(Paths.get("input.txt"))) {

            int numberOfActivities = Integer.parseInt(fileReader.nextLine()); //taking the first line to use in a for loop
            System.out.println("Number of activities registered: " + numberOfActivities); //test
            /*
            for loop to add activities to the arraylist and builds a hashmap of activities which can be used in the customer object
             */
            for (int i = 0; i < numberOfActivities; i++) {
                String activityName = fileReader.nextLine();
                int activityTickets = Integer.parseInt(fileReader.nextLine());
                Activity activityElement = new Activity(activityName, activityTickets);

                sortedActivities.addElement(sortedActivities, activityElement);
                eachCustomerActivities.put(activityName, 0);
            }
            activities = sortedActivities;
            System.out.println(activities); //Test

            // start populating the customers arraylist
            int numberOfCustomers = Integer.parseInt(fileReader.nextLine());
            System.out.println("Number of customers registered: " + numberOfCustomers); //test
            /*
            for loop to add customers to the arraylist
             */
            for (int i = 0; i < numberOfCustomers; i++) {
                String line = fileReader.nextLine();
                String fullName[] = line.split(" ");
                String firstName = fullName[0];
                String surname = fullName[1];
                Customer customer = new Customer(firstName, surname, eachCustomerActivities);
                sortedCustomers.addElement(sortedCustomers, customer);
            }
            customers = sortedCustomers;
            System.out.println(customers); //Test

        } catch (FileNotFoundException e) {
            e.printStackTrace(); //TODO: change this to something more meaningful
        }
        //test to see whether all activities/customers are registered to arraylists - pass x2
        System.out.println(sortedActivities);
        //System.out.println(customers);


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
                //t - to update the stored data when tickets are bought by one of the registered customers.

                //checking name exists
                System.out.println("What is your full name?");
                String confirmedName = null;
                Customer confirmedCustomer = new Customer(null, null, null);
                String nameLine = scanner.nextLine();
                String fullName[] = nameLine.split(" ");
                String firstName = fullName[0];
                String surname = fullName[1];
                for (Customer customer : customers) {  //TODO: can this be replaced with a binary search once the list is sorted?
                    if (customer.getFirstName().equals(firstName) && customer.getSurname().equals(surname)) {
                        //customer is valid
                        confirmedName = fullName + " " + surname;
                        confirmedCustomer = customer;
                    }
                }
                if (confirmedName == null) {
                    System.out.println("Your customer name was invalid");
                    continue;
                }

                //checking activity exists
                System.out.println("What is the name of the activity you'd like to buy tickets for?");
                String activityRequested = scanner.nextLine();
                Activity confirmedActivity = new Activity(null, 0);
                String confirmedActivityName = null;
                for (Activity activity : activities) {
                    if (activity.getActivityName().equals(activityRequested)) {
                        //activity is valid
                        confirmedActivityName = activityRequested;
                        confirmedActivity = activity;
                        break;
                    } else {
                        //activity invalid - probably shouldn't do anything for each element of the list
                    }
                }
                if (confirmedActivityName == null) {
                    System.out.println("Your activity name was invalid");
                    continue;
                }

                //checking if there are enough tickets left for the activity
                System.out.println("How many tickets would you like to buy?");
                int numberOfTickets = Integer.valueOf(scanner.nextLine());
                boolean ticketsBoughtValidity = true;
                if (numberOfTickets <= 0) {
                    System.out.println("You have input an invalid number of tickets." +
                            "The request has not been processed.");
                    ticketsBoughtValidity = false;
                } else if (numberOfTickets > 0) {
                    if (confirmedActivity.getmaxNumberOfTicketsAvailable() - numberOfTickets <= 0) {
                        confirmedActivity.buyATicket(numberOfTickets);
                    } else if (confirmedActivity.getmaxNumberOfTicketsAvailable() > 0) {
                        System.out.println("There are not enough tickets available for this activity.");
                        ticketsBoughtValidity = false;
                    }

                }

                //adding the tickets for the activity to the customer
                if (ticketsBoughtValidity == true) {
                    confirmedCustomer.boughtTickets(numberOfTickets, activityRequested);
                }

                //test
                System.out.println();

            } else if (userChoice.equals("r")) {
                //TODO: add logic in here
            } else {
                System.out.println("You have selected an invalid option, try again");
            }
        }
    }
}