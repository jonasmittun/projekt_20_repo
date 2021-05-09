package dtu.company.app;

import java.sql.SQLOutput;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class View {
	
	public Scanner scanner;
	public Project project;
	public Boolean[] test;
	
	public View() {
		scanner = new Scanner(System.in);
		this.test = new Boolean[40];
	}
	
	public void StartUpText() {
		System.out.println("Company management software 0.0.1");
		System.out.println("Copyright CC something something");
		System.out.println("Software started successfully!");
		this.test[0] = true;
	}
	
	public void ShutDownText() {
		System.out.println("Exiting software!");
		System.out.println("Input any key to continue...");
		this.scanner.next();
		this.test[1] = true;
	}
	
	public void UserIDText() {
		System.out.println("Please enter user ID:");
		this.test[2] = true;
	}
	
	public boolean ConfirmUserID(int ID) {
		String input = ""; 
		System.out.println("You have input the id: " + ID);
		System.out.println("Please input 'y' if the selected ID is correct, else input 'n' to go back.");
		input = this.scanner.next();
		if(input.equalsIgnoreCase("y")) {
			this.test[3] = true;
			return true;
		} else {
			System.out.println("Going back...");
			this.test[4] = true;
			return false;
		}
	}

	public int UserInput() {
		int input = -1;
		
		input = this.scanner.nextInt();
		this.test[5] = true;
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
		this.test[6] = true;
		return chosenProject;
	}

	public void getProjectOverview(ArrayList<Activity> activities, String projectName){
		System.out.println("Project overview for: \"" + projectName + "\"");
		System.out.println("");
		System.out.println("\t\tActivity: \tID: \tEmployees: \tWork:");
		for (int i = 0; i < activities.size(); i++){
			Activity activity = activities.get(i);
			double activityHours = activity.getWorkedHalfHours()/2;
			System.out.println("[" + i + "] \t" + activity.getActivityName() + "\t" +
					activity.getActivityID() + "\t\t" + activity.getEmployees().size() + "\t\t\t" +
					activityHours);
		}
		this.test[7] = true;
	}

	public int projectAccessMenu(){
		System.out.println("Choose an action for the selected project");

		System.out.println("[1]- " + "Add activity");
		System.out.println("[2]- " + "Assign employee");
		System.out.println("[3]- " + "Edit project name");
		System.out.println("[4]- " + "Set project deadline");
		System.out.println("[5]- " + "Get project overview");

		int input = -1;
		input = this.scanner.nextInt();

		if (input > -1 && input < 6) {
			System.out.print("Going to... ");
			switch (input) {
				case 0: System.out.println("User Selection"); 		break;
				case 1: System.out.println("Add activity"); 		break;
				case 2: System.out.println("Assign employee");  	break;
				case 3: System.out.println("Edit project name"); 	break;
				case 4: System.out.println("Set project deadline"); break;
				case 5: System.out.println("Get project overview"); break;
			}
			this.test[8] = true;
			return input;
		} else {
			System.out.println("Your input of '" + input + "' is not recognized as a valid option!");
			System.out.println("Input any key to return to Main Menu...");
			this.scanner.next();
			this.test[9] = true;
			return -1;
		}
	}

	public Project addProjectMenu(int currentUserID) throws Exception {
		System.out.println("Welcome to AddProject Menu" + " Employee " + currentUserID);
		System.out.println("Please enter the name for your new project:");

		String input = this.scanner.next();

		System.out.println("You have chosen the name " + "\"" + input + "\"" + " for your new project.");
		System.out.println("Press y to confirm, n to rename");

		String input2 = this.scanner.next();
		String projectName = "";

		//User decision
		if(input2.equalsIgnoreCase("y")){
			System.out.println("Name Confirmed: " + input + "!");
			projectName = input;
		}
		else if (input2.equalsIgnoreCase("n")) {
				System.out.println("Enter new name for your project:");
				input = this.scanner.next();
				System.out.println("New name for your project " + input);
				System.out.println("Press y to confirm, n to restart the operation");
				String input3 = this.scanner.next();
				if (input3.equalsIgnoreCase("y")) {
					System.out.println("Name confirmed!");
					projectName = input;
				}else if(input3.equalsIgnoreCase("n")){
					System.out.println("Restarting the operation...");
					this.test[10] = true;
					return null;
				}
		}

		System.out.println("Add a deadline for the project? Press y to add a deadline. " +
				"Press any other key than 'y' to creat your project without start-end dates.");

		String input4 = this.scanner.next();

		//User decision
		if (input4.equalsIgnoreCase("y")){
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			LocalDate startDate = LocalDate.now();
			System.out.println("Enter a deadline for your project [dd/MM/yyyy]:");
			LocalDate endDate = LocalDate.parse(scanner.next(), formatter);

			System.out.println("Your project is being created with startDate: " + startDate +
					" and endDate: " + endDate + "...");

			//Error handling for invalid deadline
			try {
				this.project = new Project(projectName, startDate, endDate);
			}catch (Exception e){
				System.out.println("Invalid deadline, process is restarting");
				PageBreak();
				this.test[11] = true;
				return null;
			}

			//Project created with deadline
			System.out.println("Project " + "\"" +projectName + "\"" + " is created with startDate: " +
					startDate + " and endDate: " + endDate + "!");

			//Project created without deadline
		}else{
			this.project = new Project(projectName);
			System.out.println("Project " + "\"" +projectName + "\"" + " is created!");
		}
		this.test[12] = true;
		return project;
	}

	public Activity activityOverviewAndAssignEmployee(Project project){
		System.out.println("Activity list for the chosen project " + project.getProjectName() + ":");

		//Gets activity list from the project
		ArrayList<Activity> activities = project.getActivities();

		//Print all the activities
		for(int i = 0; i < activities.size(); i++){
			if (i < 9) {
				System.out.println("[" + (i + 1) + "]\t \t" + activities.get(i).getActivityName());
			}else{
				System.out.println("[" + (i + 1) + "]\t" + activities.get(i).getActivityName());
			}
		}

		//Asks user for selection
		PageBreak();
		System.out.println("Please select one of the " + (activities.size()) + " activities");

		int input = userIntInput() - 1;
		this.test[13] = true;
		return activities.get(input);
	}

	public int chooseEmployee(){
		System.out.println("Please enter employee ID to assign ");
		int input = userIntInput();
		this.test[14] = true;
		return input;
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

		int input = this.scanner.nextInt() - 1;

		if (input > list.size()){
			this.test[15] = true;
			return "";
		}

		//Updates selection with asked hours
		String projectName = list.get(input).substring(0,list.get(input).indexOf(':'));
		String activityID = list.get(input).substring(list.get(input).lastIndexOf(':')+1,list.get(input).length());

		String s = projectName + ":" + activityID;

		System.out.println("Please input number of hours worked on activity " + activityID);

		double hours = this.scanner.nextDouble()*2;
		input = (int) Math.round(hours);

		s = s + ":" + input;
		this.test[16] = true;
		return s;
	}

	public void companyOverview(ArrayList<Project> projects, ArrayList<Employee> employees) throws Exception {
		System.out.println("\tProjects: \tDeadline: \tEmployees: \tWeekhours: ");
		for (int i = 0; i < employees.size() || i < projects.size(); i++){
			String projectName = (projects.get(i) == null) ? "\n" : projects.get(i).getProjectName();
			String deadline = "";
			try {
				deadline = (projects.get(i).getDeadline() == null) ? "\n" : String.valueOf(projects.get(i).getDeadline());
			} catch (Exception e) {
				deadline = "N/A";
			}
			String employeeID = (employees.get(i) == null) ? "\n" : "employee " + employees.get(i).getId();
			double weekHours = employees.get(i).getWeeksWorkInHalfHours()/2;

			System.out.print("\t" + projectName + "\t" +
					deadline + "\t\t\t");
			System.out.print(employeeID + "\t" + weekHours + "\n");
		}
		this.test[17] = true;
	}

	private int userIntInput(){
		int input;
		try {
			input = this.scanner.nextInt();
		} catch (Exception e){
			input = -1;
		}
		this.test[18] = true;
		return input;
	}

	public Activity addActivity(int activityID, String project, int userID) throws Exception {
		Activity activity = new Activity(activityID);
		activity.setActivityName("activity " + activityID, 0);
		System.out.println("You are about to to add an activity to \"" + project +"\"");
		System.out.println("Press 'y' to continue, press 'n' to return to Project Menu");
		String input = this.scanner.next();
		if (input.equalsIgnoreCase("y")){
			System.out.println("The activities default name is: \"" + activity.getActivityName() + "\"");
			System.out.println("Press 'y' to keep it, press 'n' to change it");
			String input2 = this.scanner.next();
			if (input2.equalsIgnoreCase("y")){
				System.out.println("Activity, \"" + activity.getActivityName() + "\" with ID, \"" + activity.getActivityID() + "\" has been added to the project, \"" + project + "\"" );
				this.test[19] = true;
				return activity;
			} else if (!input2.equalsIgnoreCase("y")){
				System.out.println("Insert the activities new name");
				String newName = this.scanner.next();
				activity.setActivityName(newName,0);
				System.out.println("Name has been updated to \"" + newName + "\"");
				System.out.println("Activity, \"" + activity.getActivityName() + "\" with ID, \"" + activity.getActivityID() + "\" has been added to the project, \"" + project + "\"" );
				this.test[20] = true;
				return activity;
			}
			System.out.println("Activity, \"" + activity.getActivityName() + "\" with ID, \"" + activity.getActivityID() + "\" has been added to the project, \"" + project + "\"" );
		} else if (!input.equalsIgnoreCase("y")){
			this.test[21] = true;
			return null;
		}
		this.test[22] = true;
		return activity;
	}
	
	public void PageBreak() {
		System.out.println("\n # # # # # \n");
		this.test[23] = true;
	}

	public void viewProjects(ArrayList<String> projects) {
		for (int i = 1; i < projects.size(); i++) {
			System.out.println(projects.get(i));
		}
		this.test[24] = true;
	}

	public int MainMenu(int currentUserID) {
		System.out.println("Welcome to [CompanyName]'s [SoftwareName], employee " + currentUserID + "!");
		System.out.println("The time is currently: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("| yyyy/mm/dd | HH:mm |")));
		System.out.println("Please choose an action from the list...");
		
		System.out.println("[1]- " + "Time Registration");
		System.out.println("[2]- " + "Projects");
		System.out.println("[3]- " + "Company Overview");
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
			case 3: System.out.println("Company Overview"); 	break;
			case 4: System.out.println("Management"); 			break;
			case 5: System.out.println("Reports"); 				break;
			case 6: System.out.println("xxx"); 					break;
			case 7: System.out.println("yyy"); 					break;
			case 8: System.out.println("zzz"); 					break;
			case 9: System.out.println("Exit program");			break;
			}
			this.test[25] = true;
			return input;
		} else {
			System.out.println("Your input of '" + input + "' is not recognized as a valid option!");
			System.out.println("Input any key to return to Main Menu...");
			this.scanner.next();
			this.test[26] = true;
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
			this.test[27] = true;
			return input;
		} else {
			System.out.println("Your input of '" + input + "' is not recognized as a valid option!");
			System.out.println("Input any key to return to Main Menu...");
			this.scanner.next();
			this.test[28] = true;
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
			this.test[29] = true;
			return input;
		} else {
			System.out.println("Your input of '" + input + "' is not recognized as a valid option!");
			System.out.println("Input any key to return to Main Menu...");
			this.scanner.next();
			this.test[30] = true;
			return -1;
		}
	}

	public void printErrorMessage(ErrorMessageHolder errorMessage) {
		System.out.println(errorMessage.getErrorMessage());
	}
}
