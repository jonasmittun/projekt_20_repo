package dtu.company.app;

import java.util.ArrayList;

public class Controller {

	static CompanyApp companyApp;
	static View view;
	static int CurrentUserID = -1;
	private static ArrayList<String> userActivities;
	
	public static void main(String[] args) {
		companyApp = new CompanyApp();
		view = new View();
		
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
			CurrentUserID = Integer.parseInt(view.UserInput());
			idConfirmed = view.ConfirmUserID(CurrentUserID);
		}
		companyApp.setUser(CurrentUserID);
		System.out.println("User set in companyApp is now: " + companyApp.getUser());
	}
	
	public static void MainMenu() {
		view.MainMenu(CurrentUserID);
	}

	public static void RegisterMenu(int CurrentUserID) throws Exception {
		//Gets user activities
		userActivities = companyApp.getUserActivities(CurrentUserID);

		//Runs Register Menu to get users to register hours
		String s = view.RegisterMenu(userActivities);

		//Saves users decision
		String projectName = s.substring(0,s.indexOf(':'));
		int activityID = Integer.parseInt(s.substring(s.indexOf(':'),s.lastIndexOf(':')));
		int halfHours = Integer.parseInt(s.substring(s.lastIndexOf(':'),s.length()-1));

		//Registers users hours
		companyApp.registerDaysWork(CurrentUserID,halfHours,activityID,projectName);

	}

}
