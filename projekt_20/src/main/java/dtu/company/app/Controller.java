package dtu.company.app;

import java.util.ArrayList;

public class Controller {

	static CompanyApp companyApp;
	static View view;
	static int currentUserID = -1;
	private static ArrayList<String> userActivities;
	private static ArrayList<String> userProjects;
	
	public static void main(String[] args) throws Exception {
		companyApp = new CompanyApp();
		view = new View();

		companyApp.setSampleCompany();

		view.StartUpText();
		
		view.PageBreak();
		
		SelectUser();
		
		view.PageBreak();

		MainMenu();

		//Gather users activities from CompanyApp

		view.ShutDownText();
		
	}
	
	public static void SelectUser() {
		boolean idConfirmed = false;
		while(!idConfirmed) {
			view.UserIDText();
			currentUserID = view.UserInput();
			idConfirmed = view.ConfirmUserID(currentUserID);
		}
		companyApp.setUser(currentUserID);
		System.out.println("User set in companyApp is now: " + companyApp.getUser());
	}
	
	public static void MainMenu() throws Exception {
		int result = -1;
		while(result == -1) {
			result = view.MainMenu(currentUserID);
		}
		switch(result) {
		case 0: view.PageBreak(); SelectUser();	break;
		case 1: view.PageBreak(); RegisterMenu();	break;
		case 2: view.PageBreak(); ProjectMenu(); System.out.println("placeholder2");	break;
		case 3: view.PageBreak(); companyOverview();	break;
		case 4: view.PageBreak(); /*User selection metode her*/ System.out.println("placeholder4");	break;
		case 5: view.PageBreak(); /*User selection metode her*/ System.out.println("placeholder5");	break;
		case 6: view.PageBreak(); /*User selection metode her*/ System.out.println("placeholder6");	break;
		case 7: view.PageBreak(); /*User selection metode her*/ System.out.println("placeholder7");	break;
		case 8: view.PageBreak(); /*User selection metode her*/ System.out.println("placeholder8");	break;
		case 9: return;
		}
		view.PageBreak();
		MainMenu();
	}

	public static void RegisterMenu() throws Exception {
		int result = -1;
		while(result == -1) {
			result = view.RegisterMenu(currentUserID);
		}
		switch(result) {
			case 0: view.PageBreak(); SelectUser();	break;
			case 1: view.PageBreak(); addHours(currentUserID);	break;
			case 2: view.PageBreak(); removeHours(currentUserID);	break;
			case 9: return;
		}
		view.PageBreak();
		RegisterMenu();
	}

	public static void ProjectMenu() throws Exception {
		int result = -1;
		while(result == -1) {
			result = view.ProjectMenu(currentUserID);
		}
		switch(result) {
			case 0: view.PageBreak(); SelectUser();	break;
			case 1: view.PageBreak(); accessProject(currentUserID);	break;
			case 2: view.PageBreak(); addProject(currentUserID);	break;
			case 9: return;
		}
		view.PageBreak();
		ProjectMenu();
	}

	private static void companyOverview() {
		ArrayList<Project> projects;
		projects = companyApp.getProjects();
		ArrayList<Employee> employees;
	}

	public static void addHours(int CurrentUserID) throws Exception {
		//Gets user activities
		userActivities = companyApp.getUserActivities(CurrentUserID);

		if (userActivities.isEmpty()){
			System.out.println("You have no active activities");
			return;
		}

		//Runs Register Menu to get users to register hours
		String s = view.activityOverview(userActivities);

		if (s.equals("")){
			System.out.println("Please enter a value between 1 and " + userActivities.size());
			return;
		}

		//Saves users decision
		//System.out.println(s);
		String projectName = s.substring(0,s.indexOf(':'));
		int activityID = Integer.parseInt(s.substring(s.indexOf(':')+1,s.lastIndexOf(':')));
		int halfHours = Integer.parseInt(s.substring(s.lastIndexOf(':')+1,s.length()));

		//Registers users hours
		companyApp.registerDaysWork(CurrentUserID,halfHours,activityID,projectName);

		double activityWork = companyApp.getProject(projectName).getActivityWithID(activityID).getWorkedHalfHours()/2;
		double weeksWork = companyApp.getEmployee(CurrentUserID).getWeeksWorkInHalfHours()/2;

		System.out.println("Hours worked on activity: " + activityWork + " | Hours worked this week: " + weeksWork);
	}

	public static void removeHours(int CurrentUserID) throws Exception {
		//Gets user activities
		userActivities = companyApp.getUserActivities(CurrentUserID);

		if (userActivities.isEmpty()){
			System.out.println("You have no active activities");
			return;
		}

		//Runs Register Menu to get users to register hours
		String s = view.activityOverview(userActivities);

		if (s.equals("")){
			System.out.println("Please enter a value between 1 and " + userActivities.size());
			return;
		}

		//Saves users decision
		//System.out.println(s);
		String projectName = s.substring(0,s.indexOf(':'));
		int activityID = Integer.parseInt(s.substring(s.indexOf(':')+1,s.lastIndexOf(':')));
		int halfHours = Integer.parseInt(s.substring(s.lastIndexOf(':')+1,s.length()));

		//Registers users hours
		companyApp.removeWeeksWork(CurrentUserID,halfHours,activityID,projectName);

		double activityWork = companyApp.getProject(projectName).getActivityWithID(activityID).getWorkedHalfHours()/2;
		double weeksWork = companyApp.getEmployee(CurrentUserID).getWeeksWorkInHalfHours()/2;

		System.out.println("Hours worked on activity: " + activityWork + " | Hours worked this week: " + weeksWork);
	}

	//TO BE CONTINUED (BORAN)
	public static void accessProject(int currentUserID) throws Exception {
		//Gets user projects
		userProjects = companyApp.getLeaderProjects(currentUserID);

		//Runs Project Menu to get overview of the current user projects
		view.projectOverview(userProjects);

	}

	public static void addProject(int currentUserID) throws Exception{

	}

}
