import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * This class contains all the logic and I/O for the program.
 * It populates the activity and customer arraylists.
 * Then takes user inputs and performs checks/validation on them.
 * Then performs the required actions.
 */
public class MainProgram {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        SortedArrayList<Activity> sortedActivities = new SortedArrayList<Activity>();
        SortedArrayList<Customer> sortedCustomers = new SortedArrayList<Customer>();
        HashMap<String, Integer> allCustomerActivities = new HashMap<>();
        ArrayList<Activity> activities = new ArrayList<Activity>();
        ArrayList<Customer> customers = new ArrayList<Customer>();
        final int MAXNUMBEROFACTIVITIES = 3;

        try {
            Scanner fileReader = new Scanner(Paths.get("input.txt"));
            int numberOfActivities = Integer.parseInt(fileReader.nextLine());
            /*
            read in activities and add to the activities arraylist
            build a hashmap of activities which can be used in the customer object
             */
            for (int i = 0; i < numberOfActivities; i++) {
                String activityName = fileReader.nextLine();
                int activityTickets = Integer.parseInt(fileReader.nextLine());
                Activity activityElement = new Activity(activityName, activityTickets);
                sortedActivities.addElement(sortedActivities, activityElement);
                allCustomerActivities.put(activityName, 0);
            }
            activities = sortedActivities;

            /*
            Test
            System.out.println("Number of activities registered: " + numberOfActivities);
             */

            /*
            populate the customers arraylist
             */
            int numberOfCustomers = Integer.parseInt(fileReader.nextLine());

            for (int i = 0; i < numberOfCustomers; i++) {
                String line = fileReader.nextLine();
                String fullName[] = line.split(" ");
                String firstName = fullName[0];
                String surname = fullName[1];
                HashMap<String, Integer> eachCustomerActivities = new HashMap<>(allCustomerActivities);
                Customer customer = new Customer(firstName, surname, eachCustomerActivities);
                sortedCustomers.addElement(sortedCustomers, customer);
            }
            customers = sortedCustomers;

            /*
            Test
            System.out.println("Number of customers registered: " + numberOfCustomers);
             */

        } catch (IOException e) {
            System.out.println("The file does not exist.\n");
        }

        /*
        tests to see whether all activities/customers are registered to arraylists - pass x2
        System.out.println(activities);
        System.out.println(customers);
         */

        while (true) {
            printCustomerChoices();
            String userChoice = scanner.nextLine();

            if (userChoice.equals("f")) {
                break;
            } else if (userChoice.equals("a")) {
                printActivities(activities);
                System.out.println("");
            } else if (userChoice.equals("c")) {
                printCustomers(customers); //note that the hashmap for each customer's activities is by definition unordered
                System.out.println("");
            } else if (userChoice.equals("t") || userChoice.equals("r")) {
                /*
                verifying name exists and creating a confirmed string and object to use in later parts of the program
                 */
                String confirmedName = null;
                Customer confirmedCustomer = new Customer(null, null, null);
                System.out.println("What is your full name?");
                String nameLine = scanner.nextLine();
                boolean validName = true;
                try {
                    confirmedName = populateConfirmedCustomerName(nameLine, customers);
                    confirmedCustomer = populateConfirmedCustomer(nameLine, customers);
                    if (confirmedName == null) {
                        System.out.println("Your customer name was invalid. The request has not been processed.\n");
                        continue;
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("You haven't included both a first name and last name separated by a space.\n");
                    validName = false;
                }
                if (validName == false) {
                    continue;
                }

                /*
                verifying activity exists and creating a confirmed string and object to use in later parts of the program
                 */
                String confirmedActivityName = null;
                Activity confirmedActivity = new Activity(null, 0);
                System.out.println("What is the name of the activity you'd like to update tickets for?");
                String activityRequested = scanner.nextLine();
                confirmedActivityName = populateConfirmedActivityName(activities, activityRequested);
                confirmedActivity = populateConfirmedActivity(activities, activityRequested);
                if (confirmedActivityName == null) {
                    System.out.println("Your activity name was invalid. The request has not been processed.\n");
                    continue;
                }

                if (userChoice.equals("t")) {
                    /*
                    checking if there are enough tickets left for the activity
                     */
                    boolean noOfTicketsBoughtValidity = false;
                    System.out.println("How many tickets would you like to buy?");
                    int numberOfTickets = Integer.valueOf(scanner.nextLine());
                    if (numberOfTickets <= 0) {
                        System.out.println("You have input an invalid number of tickets." +
                                "The request has not been processed.\n");
                        noOfTicketsBoughtValidity = false;
                    } else if (numberOfTickets > 0) {
                        if (confirmedActivity.getmaxNumberOfTicketsAvailable() - numberOfTickets >= 0) {
                            noOfTicketsBoughtValidity = true;
                        } else if (confirmedActivity.getmaxNumberOfTicketsAvailable() - numberOfTickets < 0) {
                            confirmedCustomer.notEnoughTicketsLetter(confirmedActivity);
                            System.out.println(confirmedName + " has tried to book too many tickets for " + confirmedActivityName +
                                    ".\nLetter printed.\n");
                            noOfTicketsBoughtValidity = false;
                        }
                    }

                    /*
                    adding the tickets for the activity to the customer if valid
                     */
                    if (noOfTicketsBoughtValidity == true && confirmedCustomer.getUniqueActivityCounter() < MAXNUMBEROFACTIVITIES) {
                        confirmedCustomer.boughtTickets(numberOfTickets, activityRequested);
                        confirmedActivity.buyTickets(numberOfTickets);
                        System.out.println(confirmedName + " has bought " + numberOfTickets + " ticket(s) for " +
                                confirmedActivityName + ".\n");
                    } else if (confirmedCustomer.getUniqueActivityCounter() >= 3) {
                        System.out.println(confirmedName + " can only order tickets for a maximum of three activities. " +
                                "The request has not been processed.\n");
                    }

                    /*
                    Test statements
                    System.out.println("activity we bought tickets for: " + confirmedActivity +
                            "customer we added tickets to: " + confirmedCustomer);
                    System.out.println("Customer's activity hashmap: " + confirmedCustomer.getActivityMap());
                    System.out.println("Customer's unique activity count: " + confirmedCustomer.getUniqueActivityCounter());
                    System.out.println("Activity's max no of available tickets: " + confirmedActivity.getmaxNumberOfTicketsAvailable());
                    */

                } else if (userChoice.equals("r")) {
                    /*
                    check if there are enough tickets left for the activity.
                    note: if each customer can only sell as many tickets as they own and no more,
                    then the aggregate number of tickets for the activity can't exceed the original amount
                    by the act of cancelling tickets
                     */
                    System.out.println("How many tickets would you like to cancel?");
                    int numberOfTickets = Integer.valueOf(scanner.nextLine());
                    int ticketsRemainingForCustomer = confirmedCustomer.getActivityMap().get(confirmedActivityName)
                            - numberOfTickets;

                    if (ticketsRemainingForCustomer >= 0) {
                        confirmedCustomer.soldTickets(numberOfTickets, confirmedActivityName);
                        confirmedActivity.sellTickets(numberOfTickets);
                        System.out.println(confirmedName + " has cancelled " + numberOfTickets + " ticket(s) for " +
                                confirmedActivityName + ".\n");
                    }
                    if (ticketsRemainingForCustomer == 0) {
                        //decrement activity uniqueCounter
                        confirmedCustomer.clearedTicketsFromActivity();
                    }
                    if (ticketsRemainingForCustomer < 0) {
                        System.out.println(confirmedName + " has tried to cancel more tickets than they own. " +
                                "The request has not been processed.\n");
                    }

                    /*
                    Test statements
                    System.out.println("Activity we sold tickets for: " + confirmedActivity +
                            ". Customer we removed tickets from: " + confirmedCustomer);
                    System.out.println("Customer's activity hashmap: " + confirmedCustomer.getActivityMap());
                    System.out.println("Customer's unique activity count: " + confirmedCustomer.getUniqueActivityCounter());
                    System.out.println("Activity's max no of available tickets: " + confirmedActivity.getmaxNumberOfTicketsAvailable());
                     */
                }

            } else {
                System.out.println("You have selected an invalid option, try again.\n");
            }
        }
    }

    private static void printCustomerChoices() {
        //customer choices
        System.out.println("Select an option:");
        System.out.println("f - to finish running the program.");
        System.out.println("a - to display on the screen information about all the activities.");
        System.out.println("c - to display on the screen information about all the customers.");
        System.out.println("t - to update the stored data when tickets are bought by one of the registered customers.");
        System.out.println("r - to update the stored data when a registered customer cancels tickets for a booking.");
    }

    private static void printActivities(ArrayList<Activity> activities) {
        for (Activity activity : activities) {
            System.out.println(activity.toString());
        }
    }

    private static void printCustomers(ArrayList<Customer> customers) {
        for (Customer customer : customers) {
            System.out.println("\n" + customer.getFullName() + " has tickets for these activities:");
            customer.getActivityMap().forEach((activity, tickets) -> System.out.println(activity + ": " + tickets));
            /*
            this way of iterating over a hashmap was taken from stack overflow:
            https://stackoverflow.com/questions/46898/how-do-i-efficiently-iterate-over-each-entry-in-a-java-map
            Original Author - Stack Overflow, "The Coordinator"
            Modifying Author – Tahir Khan
            This is a Java stream which allows us to iterate over a hashmap
            and print out the names of the activities as well as the number of tickets bought
             */
        }
    }

    private static String populateConfirmedCustomerName(String nameLine, ArrayList<Customer> customers) {
        String fullName[] = nameLine.split(" ");
        String firstName = fullName[0];
        String surname = fullName[1];
        String confirmedName = null;
        for (Customer customer : customers) {  //TODO: can this be replaced with a binary search once the list is sorted?
            if (customer.getFirstName().equals(firstName) && customer.getSurname().equals(surname)) {
                //customer is valid
                confirmedName = firstName + " " + surname;
            }
        }
        return confirmedName;
    }

    private static Customer populateConfirmedCustomer(String nameLine, ArrayList<Customer> customers) {
        String fullName[] = nameLine.split(" ");
        String firstName = fullName[0];
        String surname = fullName[1];
        Customer confirmedCustomer = new Customer(null, null, null);
        for (Customer customer : customers) {
            if (customer.getFirstName().equals(firstName) && customer.getSurname().equals(surname)) {
                //customer is valid
                confirmedCustomer = customer;
            }
        }
        return confirmedCustomer;
    }

    private static String populateConfirmedActivityName(ArrayList<Activity> activities,
                                                        String activityRequested) {
        String confirmedActivityName = null;
        for (Activity activity : activities) {
            if (activity.getActivityName().equals(activityRequested)) {
                //activity is valid
                confirmedActivityName = activityRequested;
                break;
            }
        }
        return confirmedActivityName;
    }

    private static Activity populateConfirmedActivity(ArrayList<Activity> activities,
                                                      String activityRequested) {
        Activity confirmedActivity = new Activity(null, 0);
        for (Activity activity : activities) {
            if (activity.getActivityName().equals(activityRequested)) {
                //activity is valid
                confirmedActivity = activity;
                break;
            }
        }
        return confirmedActivity;
    }
}