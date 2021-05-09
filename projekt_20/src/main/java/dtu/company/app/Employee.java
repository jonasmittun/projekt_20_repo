package dtu.company.app;

public class Employee {
    private int id;
    private int numberOfActivities;
    private int daysWorkInHalfHours;
    private int weeksWorkInHalfHours;

    public Employee(int id){
        this.id = id;
        this.numberOfActivities = 0;
        this.daysWorkInHalfHours = 0;
        this.weeksWorkInHalfHours = 0;
    }

	public int getId() {
		return this.id;
	}

	public int getNumberOfActivities(){
        return numberOfActivities;
    }

    public void addActivity(){
        this.numberOfActivities++;
    }

    public void setNumberOfActivities(int numberOfActivities){
        this.numberOfActivities = numberOfActivities;
    }

    public void removeActivity() {
        numberOfActivities--;
    }

    public void addDaysWorkInHalfHours(int halfHours) throws Exception {
        assert halfHours >= 0 : "Precondition violated";
        int halfHoursAtPre = daysWorkInHalfHours;
        if (halfHours > 0) {
            if (daysWorkInHalfHours + halfHours <= 48) {
                this.daysWorkInHalfHours = daysWorkInHalfHours + halfHours;
                this.weeksWorkInHalfHours = weeksWorkInHalfHours + halfHours;
            } else {
                throw new Exception("Employee can't work more than 24 hours. Try again");
            }
        } else {
            throw new Exception("Invalid input. Please insert a positive Integer...");
        }
        assert halfHoursAtPre + halfHours == daysWorkInHalfHours;
    }

    public void removeWeeksWorkInHalfHours(int halfHours) throws Exception {
        if (weeksWorkInHalfHours - halfHours > 0) {
            this.weeksWorkInHalfHours = weeksWorkInHalfHours - halfHours;
        } else {
            halfHours = weeksWorkInHalfHours;
            double hours = weeksWorkInHalfHours/2;
            System.out.println("You have emptied your weeks work");
            System.out.println("Removed " + hours + " hours from weeks work");
            System.out.println("");
            this.weeksWorkInHalfHours = 0;
        }
    }

    public void resetDaysWork() {
        this.daysWorkInHalfHours = 0;
    }

    public void resetWeeksWork() {
        this.weeksWorkInHalfHours = 0;
    }

    public int getDaysWorkInHalfHours(){
        return daysWorkInHalfHours;
    }

    public int getWeeksWorkInHalfHours(){
        return weeksWorkInHalfHours;
    }
}
