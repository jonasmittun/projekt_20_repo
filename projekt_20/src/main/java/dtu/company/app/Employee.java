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
        //Precondition
        assert halfHours > 0 && daysWorkInHalfHours + halfHours <= 48: "Precondition";
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
        //Post-condition
        assert halfHoursAtPre + halfHours == daysWorkInHalfHours : "Post-condition";
    }

    public void removeWeeksWorkInHalfHours(int halfHours) throws Exception {
        if (weeksWorkInHalfHours - halfHours > 0) {
            this.weeksWorkInHalfHours = weeksWorkInHalfHours - halfHours;
        } else {
            this.weeksWorkInHalfHours = 0;
            throw new Exception("You have emptied your weeks work");
        }
    }

    public int getWeeksWorkInHalfHours(){
        return weeksWorkInHalfHours;
    }
}
