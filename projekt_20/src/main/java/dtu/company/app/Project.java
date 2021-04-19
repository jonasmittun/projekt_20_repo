package dtu.company.app;

public class Project {
    private String projectName;
    private int projectLeaderID;

    public Project(String projectName, int projectLeader){
        this.projectName = projectName;
        this.projectLeaderID = projectLeader;
    }

    public String getProject(){
        return projectName;
    }

    public int getProjectLeader(){
        return projectLeaderID;
    }
}
