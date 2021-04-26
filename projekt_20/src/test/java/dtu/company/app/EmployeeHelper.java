package dtu.company.app;

public class EmployeeHelper {
    private int id;
    private int activities;

    public EmployeeHelper(int id){
        this.id = id;
        this.activities = 0;
    }

    public int getId() {
        return id;
    }

    public int getActivities(){
        return activities;
    }
}
