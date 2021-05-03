package dtu.company.app;

import java.text.DateFormat;
import java.util.ArrayList;
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

	public int[] RegisterMenu(ArrayList<String> list){
		for (int i = 0; i < list.size(); i++){
			String projectName = list.get(i).substring(0,list.get(i).indexOf(':'));
			String activityName = list.get(i).substring(list.get(i).indexOf(':'),list.get(i).lastIndexOf(':'));
			int activityID = Integer.parseInt(list.get(i).substring(list.get(i).lastIndexOf(':'),list.get(i).length()-1));
			System.out.println("[" + i + "]\t" + projectName + "\t" + activityName);
		}
		System.out.println("");
		System.out.println("Please select one of " + (list.size()-1) + " activities");
		int[] input = new int[2];
		input[0] = this.scanner.nextInt();
		System.out.println("--------------------------------------------------------");

		return input;
	}
	
	public void PageBreak() {
		System.out.println("\n # # # # # \n");
	}

	public void MainMenu(int currentUserID) {
		System.out.println("Welcome to [CompanyName]'s [SoftwareName], employee " + currentUserID + "!");
		System.out.println("The time is currently: " + DateFormat.HOUR_OF_DAY0_FIELD);
		System.out.println("Please choose an action from the list below, or type [0] to return to User Selection...");
		
		System.out.println("[1]. " + "");
		System.out.println("[1]. " + "");
		System.out.println("[1]. " + "");
		System.out.println("[1]. " + "");
		System.out.println("[1]. " + "");
		System.out.println("[1]. " + "");
		System.out.println("[9]. " + "Exit Program");
	}
	
}
