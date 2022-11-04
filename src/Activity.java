public class Activity {

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

    //TODO: Make this an appropriate format for output
    @Override
    public String toString() {
        return "Activity{" +
                "activityName='" + activityName + '\'' +
                ", maxNumberOfTickets=" + maxNumberOfTickets +
                '}';
    }
}
