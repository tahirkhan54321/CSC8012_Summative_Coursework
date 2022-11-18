/**
 * This class stores aggregate information about activities.
 * E.g. how many tickets have been bought for each activity and how many are available for each activity.
 * It also tells the program what to do when a customer buys or cancels tickets for an activity.
 *
 * @author Tahir Khan
 *
 */

public class Activity implements Comparable<Activity>  {

    private String activityName;
    private int maxNumberOfTicketsAvailable;

    public Activity(String activityName, int maxNumberOfTickets) {
        this.activityName = activityName;
        this.maxNumberOfTicketsAvailable = maxNumberOfTickets;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public int getmaxNumberOfTicketsAvailable() {
        return maxNumberOfTicketsAvailable;
    }

    public void setmaxNumberOfTicketsAvailable(int maxNumberOfTickets) {
        this.maxNumberOfTicketsAvailable = maxNumberOfTickets;
    }

    /**
     * conditionals for validity of buying a ticket are located in the main method.
     */
    public int buyTickets(int buyTickets) {
        return this.maxNumberOfTicketsAvailable -= buyTickets;
    }

    /**
     * on aggregate, customers can't sell more tickets than those which have been bought.
     * so maxNumberOfTickets available can't exceed the original amount from the input file, no need for a check in this method
     */
    public int sellTickets(int sellTickets) {
        return this.maxNumberOfTicketsAvailable += sellTickets;
    }

    /**
     * Overriding the compareTo method to allow an arrayList of activities to be sorted
     * by using the SortedArrayList class
     * @param activity the object to be compared.
     * @return 1,0,-1 accordingly
     */
    @Override
    public int compareTo(Activity activity) {
        int activityComparison = this.activityName.compareTo(activity.getActivityName());
        if (activityComparison !=0) {
            /*
            tests
            System.out.println("activity going into compareTo method: " + activity.getActivityName()
                    + " comparing to: " + this.activityName);
            System.out.println(activityComparison);
             */
            return activityComparison;
        }
        else {
            return 0;
        }
    }

    @Override
    public String toString() {
        return this.activityName + " has " + this.maxNumberOfTicketsAvailable + " tickets available.";
    }
}
