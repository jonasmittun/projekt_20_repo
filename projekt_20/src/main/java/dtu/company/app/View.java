package dtu.company.app;

import java.util.Scanner;

public class View {
	
	public Scanner scanner;
	
	public View() {
		scanner = new Scanner(System.in);
	}
	
	public void StartUpText() {
		System.out.println("Company management software 0.0.1");
		System.out.println("Copyright CC something something");
		System.out.println("Software started successfully!");
	}
	
	public void ShutDownText() {
		System.out.println("Exiting software!");
		System.out.println("Press any key to continue...");
		this.scanner.nextLine();
	}
	
	public void UserIDText() {
		System.out.println("Please enter user ID:");
	}
	
	public boolean ConfirmUserID(int ID) {
		String input = ""; 
		System.out.println("You have input the id: " + ID);
		System.out.println("Please input 'y' if the selected ID is correct, else input 'n' to go back.");
		input = this.scanner.nextLine();
		if(input.equalsIgnoreCase("y")) {
			return true;
		} else {
			System.out.println("Going back...");
			return false;
		}
	}

	public String UserInput() {
		String input = "";
		
		input = this.scanner.nextLine();
		
		return input;
	}
	
}
