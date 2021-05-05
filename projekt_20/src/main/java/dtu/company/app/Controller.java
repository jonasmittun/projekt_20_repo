package dtu.company.app;

import java.util.ArrayList;

public class Controller {

	static CompanyApp companyApp;
	static View view;
	static int CurrentUserID = -1;
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
			CurrentUserID = view.UserInput();
			idConfirmed = view.ConfirmUserID(CurrentUserID);
		}
		companyApp.setUser(CurrentUserID);
		System.out.println("User set in companyApp is now: " + companyApp.getUser());
	}
	
	public static void MainMenu() throws Exception {
		int result = -1;
		while(result == -1) {
			result = view.MainMenu(CurrentUserID);
		}
		switch(result) {
		case 0: view.PageBreak(); SelectUser();	break;
		case 1: view.PageBreak(); RegisterMenu();	break;
		case 2: view.PageBreak(); projectMenu(CurrentUserID); System.out.println("placeholder2");	break;
		case 3: view.PageBreak(); /*User selection metode her*/ System.out.println("placeholder3");	break;
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
			result = view.RegisterMenu(CurrentUserID);
		}
		switch(result) {
			case 0: view.PageBreak(); SelectUser();	break;
			case 1: view.PageBreak(); addHours(CurrentUserID);	break;
			case 2: view.PageBreak(); removeHours(CurrentUserID);	break;
			case 9: return;
		}
		view.PageBreak();
		RegisterMenu();
	}

	public static void addHours(int CurrentUserID) throws Exception {
		//Gets user activities
		userActivities = companyApp.getUserActivities(CurrentUserID);

		//Runs Register Menu to get users to register hours
		String s = view.activityOverview(userActivities);

		//Saves users decision
		//System.out.println(s);
		String projectName = s.substring(0,s.indexOf(':'));
		int activityID = Integer.parseInt(s.substring(s.indexOf(':')+1,s.lastIndexOf(':')));
		int halfHours = Integer.parseInt(s.substring(s.lastIndexOf(':')+1,s.length()));

		//Registers users hours
		companyApp.registerDaysWork(CurrentUserID,halfHours,activityID,projectName);

		double daysWork = companyApp.getEmployee(CurrentUserID).getDaysWorkInHalfHours()/2;
		double weeksWork = companyApp.getEmployee(CurrentUserID).getWeeksWorkInHalfHours()/2;

		System.out.println("Hours worked today: " + daysWork + " | Hours worked this week: " + weeksWork);
	}

	public static void removeHours(int CurrentUserID) throws Exception {
		//Gets user activities
		userActivities = companyApp.getUserActivities(CurrentUserID);

		//Runs Register Menu to get users to register hours
		String s = view.activityOverview(userActivities);

		//Saves users decision
		//System.out.println(s);
		String projectName = s.substring(0,s.indexOf(':'));
		int activityID = Integer.parseInt(s.substring(s.indexOf(':')+1,s.lastIndexOf(':')));
		int halfHours = Integer.parseInt(s.substring(s.lastIndexOf(':')+1,s.length()));

		//Registers users hours
		companyApp.removeWeeksWork(CurrentUserID,halfHours,activityID,projectName);

		double daysWork = companyApp.getEmployee(CurrentUserID).getDaysWorkInHalfHours()/2;
		double weeksWork = companyApp.getEmployee(CurrentUserID).getWeeksWorkInHalfHours()/2;

		System.out.println("Hours worked this week: " + weeksWork);
	}

	//TO BE CONTINUED (BORAN)
	public static void projectMenu(int currentUserID) throws Exception {
		//Gets user projects
		ArrayList<Project> projects;
		projects = companyApp.getProjectLeaderProjects(currentUserID);
		for (Project project : projects){
			String projectName = project.getProjectName();
			userProjects.add(projectName);
			System.out.println(userProjects.get(0));
		}

		//Runs Project Menu from view
		view.projectMenu(currentUserID);

		view.viewProjects(userProjects);
	}

}
