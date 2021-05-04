package dtu.company.app;

import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
		System.out.println("Input any key to continue...");
		this.scanner.next();
	}
	
	public void UserIDText() {
		System.out.println("Please enter user ID:");
	}
	
	public boolean ConfirmUserID(int ID) {
		String input = ""; 
		System.out.println("You have input the id: " + ID);
		System.out.println("Please input 'y' if the selected ID is correct, else input 'n' to go back.");
		input = this.scanner.next();
		if(input.equalsIgnoreCase("y")) {
			return true;
		} else {
			System.out.println("Going back...");
			return false;
		}
	}

	public int UserInput() {
		int input = -1;
		
		input = this.scanner.nextInt();
		
		return input;
	}

	public String RegisterMenu(ArrayList<String> list){
		System.out.println("\t ProjectName \t ActivityName \t ActivityID" );

		//Prints all activities for the user
		for (int i = 0; i < list.size(); i++){
			String projectName = list.get(i).substring(0,list.get(i).indexOf(':'));
			String activityName = list.get(i).substring(list.get(i).indexOf(':'),list.get(i).lastIndexOf(':'));
			int activityID = Integer.parseInt(list.get(i).substring(list.get(i).lastIndexOf(':'),list.get(i).length()-1));
			System.out.println("[" + i + "]\t" + projectName + "\t" + activityName + "\t" + activityID);
		}

		//Asks user for selection
		System.out.println("");
		System.out.println("Please select one <int> of " + (list.size()-1) + " activities");
		int input = this.scanner.nextInt();

		//Updates selection with asked hours
		String projectName = list.get(input).substring(0,list.get(input).indexOf(':'));
		String activityID = list.get(input).substring(list.get(input).lastIndexOf(':'),list.get(input).length()-1);

		String s = projectName + ":" + activityID;
		PageBreak();

		System.out.println("Please input <int> number of half hours worked on activity " + activityID);
		input = this.scanner.nextInt();

		s = s + ":" + input;
		return s;
	}
	
	public void PageBreak() {
		System.out.println("\n # # # # # \n");
	}

	public int MainMenu(int currentUserID) {
		System.out.println("Welcome to [CompanyName]'s [SoftwareName], employee " + currentUserID + "!");
		System.out.println("The time is currently: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("| yyyy/mm/dd | HH:mm |")));
		System.out.println("Please choose an action from the list...");
		
		System.out.println("[1]- " + "Time Registration");
		System.out.println("[2]- " + "Projects");
		System.out.println("[3]- " + "Activities");
		System.out.println("[4]- " + "Management");
		System.out.println("[5]- " + "Reports");
		System.out.println("[6]- " + "xxx");
		System.out.println("[7]- " + "yyy");
		System.out.println("[8]- " + "zzz");
		System.out.println("[9]- " + "Exit Program");
		System.out.println("[0]- " + "User Selection");
		
		int input = -1;
		input = this.scanner.nextInt();
		
		if (input > -1 && input < 10) {
			System.out.print("Going to... ");
			switch (input) {
			case 0: System.out.println("User Selection"); 		break;
			case 1: System.out.println("Time Registration"); 	break;
			case 2: System.out.println("Projects"); 			break;
			case 3: System.out.println("Activities"); 			break;
			case 4: System.out.println("Management"); 			break;
			case 5: System.out.println("Reports"); 				break;
			case 6: System.out.println("xxx"); 					break;
			case 7: System.out.println("yyy"); 					break;
			case 8: System.out.println("zzz"); 					break;
			case 9: System.out.println("Exit program");			break;
			}
			return input;
		} else {
			System.out.println("Your input of '" + input + "' is not recognized as a valid option!");
			System.out.println("Input any key to return to Main Menu...");
			this.scanner.next();
			return -1;
		}
	}
	
}
