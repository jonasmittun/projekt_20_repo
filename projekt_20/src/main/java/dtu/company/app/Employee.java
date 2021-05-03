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
        if (daysWorkInHalfHours + halfHours < 48) {
            this.daysWorkInHalfHours = daysWorkInHalfHours + halfHours;
            this.weeksWorkInHalfHours = weeksWorkInHalfHours + daysWorkInHalfHours;
        } else {
            throw new Exception("Can't work more than 24 hours a day");
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
