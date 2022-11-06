public class Activity implements Comparable<Activity> {

    private String activityName;
    private int maxNumberOfTickets;

    public Activity(String activityName, int maxNumberOfTickets) {
        this.activityName = activityName;
        this.maxNumberOfTickets = maxNumberOfTickets;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public int getMaxNumberOfTickets() {
        return maxNumberOfTickets;
    }

    public void setMaxNumberOfTickets(int maxNumberOfTickets) {
        this.maxNumberOfTickets = maxNumberOfTickets;
    }

    //TODO: check this logic is the right way around
    @Override
    public int compareTo(Activity activity) {
        int activityComparison = this.activityName.compareTo(activity.getActivityName());
        if (activityComparison !=0) {
            /* tests
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

    //TODO: Make this an appropriate format for output
    @Override
    public String toString() {
        return "Activity{" +
                "activityName='" + activityName + '\'' +
                ", maxNumberOfTickets=" + maxNumberOfTickets +
                '}';
    }
}
