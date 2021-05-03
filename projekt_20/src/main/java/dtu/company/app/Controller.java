package dtu.company.app;

public class Controller {

	static CompanyApp companyApp;
	static View view;
	static int CurrentUserID = -1;
	
	public static void main(String[] args) {
		//Scanner scanner = new Scanner(System.in);
		
		companyApp = new CompanyApp();
		
		view = new View();
		
		view.StartUpText();
		
		//Something while id is not accepted by user keep doing this
		
		view.UserIDText();
		
		CurrentUserID = Integer.parseInt(view.UserInput());
		
		System.out.println(view.ConfirmUserID(CurrentUserID));
		
		companyApp.setUser(CurrentUserID);
		
		System.out.println("User set in companyApp is now: " + companyApp.getUser());
		
	}

}
