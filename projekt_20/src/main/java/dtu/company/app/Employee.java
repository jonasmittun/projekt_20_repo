package dtu.company.app;

public class Employee {
    private int id;
    private int activities;

    public Employee(int id){
        this.id = id;
        this.activities = 0;
    }


	public int getId() {
		return id;
	}

	public int getActivities(){
        return activities;
    }

    public void addActivity(){
        this.activities++;
    }

    public void setActivities(int activities){
        this.activities = activities;
    }

    public void removeActivity() {
        activities--;
    }
}
