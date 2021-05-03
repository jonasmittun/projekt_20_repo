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
		
		//Something while id is not accepted by user keep doing this
		
		view.UserIDText();
		
		CurrentUserID = Integer.parseInt(view.UserInput());
		
		System.out.println(view.ConfirmUserID(CurrentUserID));
		
		companyApp.setUser(CurrentUserID);
		
		System.out.println("User set in companyApp is now: " + companyApp.getUser());

		//Gather users activities from CompanyApp

		userActivities = companyApp.getUserActivities(CurrentUserID);

		view.RegisterMenu(userActivities);


		view.ShutDownText();
		
	}

}
