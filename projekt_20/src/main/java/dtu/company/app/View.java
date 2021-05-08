package dtu.company.app;

import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
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
	public String projectOverview(ArrayList<String> list){
		System.out.println("\t \t ProjectName:");

		//Prints all projects for the user
		for(int i = 0; i < list.size(); i++){
			System.out.println("[" + (i + 1) + "]\t \t " + list.get(i));
		}

		//Asks user for selection
		PageBreak();
		System.out.println("Please select one of " + (list.size()) + " projects");

		int input = userIntInput();

		if (input > list.size()){
			System.out.println("Please choose a value from 1 to" + list.size());
		}

		String chosenProject = list.get(input-1);
		return chosenProject;
	}

	public int projectAccessMenu(){
		System.out.println("Choose an action for the selected project");

		System.out.println("[1]- " + "Add activity");
		System.out.println("[2]- " + "Assign employee");
		System.out.println("[3]- " + "Edit project name");
		System.out.println("[4]- " + "Set project deadline");

		int input = -1;
		input = this.scanner.nextInt();

		if (input > -1 && input < 4) {
			System.out.print("Going to... ");
			switch (input) {
				case 0: System.out.println("User Selection"); 		break;
				case 1: System.out.println("Add activity"); 		break;
				case 2: System.out.println("Assign employee");  	break;
				case 3: System.out.println("Edit project name"); 	break;
				case 4: System.out.println("Set project deadline"); break;
			}
			return input;
		} else {
			System.out.println("Your input of '" + input + "' is not recognized as a valid option!");
			System.out.println("Input any key to return to Main Menu...");
			this.scanner.next();
			return -1;
		}
	}

	public String activityOverview(ArrayList<String> list){
		System.out.println("\t \t ProjectName: \t ActivityName: \t ActivityID:" );

		//Prints all activities for the user
		for (int i = 0; i < list.size(); i++){
			String projectName = list.get(i).substring(0,list.get(i).indexOf(':'));
			String activityName = list.get(i).substring(list.get(i).indexOf(':')+1,list.get(i).lastIndexOf(':'));
			int activityID = Integer.parseInt(list.get(i).substring(list.get(i).lastIndexOf(':')+1,list.get(i).length()));
			if (i < 9) {
				System.out.println("[" + (i + 1) + "]\t \t \"" + projectName + "\" \t \"" + activityName + "\" \t \t" + activityID);
			} else {
				System.out.println("[" + (i + 1) + "]\t \"" + projectName + "\" \t \"" + activityName + "\" \t \t" + activityID);
			}
		}

		//Asks user for selection
		System.out.println("");
		System.out.println("Please select one of " + (list.size()) + " activities");

		int input = this.scanner.nextInt();

		if (input > list.size()){
			return "";
		}

		//Updates selection with asked hours
		String projectName = list.get(input).substring(0,list.get(input).indexOf(':'));
		String activityID = list.get(input).substring(list.get(input).lastIndexOf(':')+1,list.get(input).length());

		String s = projectName + ":" + activityID;

		System.out.println("Please input number of hours worked on activity \"" + activityID + "\"");

		double hours = this.scanner.nextDouble()*2;
		input = (int) Math.round(hours);

		s = s + ":" + input;
		return s;
	}

	private int userIntInput(){
		int input;
		try {
			input = this.scanner.nextInt();
		} catch (Exception e){
			input = -1;
		}
		return input;
	}
	
	public void PageBreak() {
		System.out.println("\n # # # # # \n");
	}


	public void viewProjects(ArrayList<String> projects) {
		for (int i = 1; i < projects.size(); i++) {
			System.out.println(projects.get(i));
		}
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

	public int ProjectMenu(int currentUserID) {
		System.out.println("Welcome to Project Menu, employee " + currentUserID + "!");
		System.out.println("The time is currently: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("| yyyy/mm/dd | HH:mm |")));
		System.out.println("Please choose an action from the list...");

		System.out.println("[1]- " + "View Projects");
		System.out.println("[2]- " + "Add new project");
		System.out.println("[9]- " + "Exit Program");
		System.out.println("[0]- " + "User Selection");

		int input = -1;
		input = this.scanner.nextInt();

		if (input > -1 && input < 3 || input == 9 || input == 0) {
			System.out.print("Going to... ");
			switch (input) {
				case 0: System.out.println("User Selection");	break;
				case 1: System.out.println("View projects"); break;
				case 2: System.out.println("Add new project");	break;
				case 9: System.out.println("Exit program");	break;
			}
			return input;
		} else {
			System.out.println("Your input of '" + input + "' is not recognized as a valid option!");
			System.out.println("Input any key to return to Main Menu...");
			this.scanner.next();
			return -1;
		}
	}

	public int RegisterMenu(int currentUserID) {
		System.out.println("Welcome to Register Menu, employee " + currentUserID + "!");
		System.out.println("The time is currently: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("| yyyy/mm/dd | HH:mm |")));
		System.out.println("Please choose an action from the list...");

		System.out.println("[1]- " + "Add hours");
		System.out.println("[2]- " + "Remove hours");
		System.out.println("[9]- " + "Exit Program");
		System.out.println("[0]- " + "User Selection");

		int input = -1;
		input = this.scanner.nextInt();

		if (input > -1 && input < 3 || input == 9 || input == 0) {
			System.out.print("Going to... ");
			switch (input) {
				case 0: System.out.println("User Selection");	break;
				case 1: System.out.println("Add hours"); break;
				case 2: System.out.println("Remove hours");	break;
				case 9: System.out.println("Exit program");	break;
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
