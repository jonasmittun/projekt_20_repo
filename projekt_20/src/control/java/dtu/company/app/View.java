package dtu.company.app;

import java.sql.SQLOutput;
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
			try {
				input = this.scanner.nextInt();
			} catch (Exception e) {
				System.out.println("Wrong input format! Please input an integer!");
				input = -1;
			} 
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

		int input = UserIntInput();
		while (!(input <= list.size() && input > 0)) {
			System.out.println("Please choose a value from 1 to " + list.size());
			input = UserIntInput();
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

	public String editProjectNameMenu(){
		System.out.println("Enter the name you would like to change for the chosen project");
		//Asks user for the new name
		String input = this.scanner.next();
		return input;
	}

	public int projectAccessMenu(){
		System.out.println("Choose an action for the selected project");

		System.out.println("[1]- " + "Add activity");
		System.out.println("[2]- " + "Assign employee");
		System.out.println("[3]- " + "Edit project name");
		System.out.println("[4]- " + "Set project deadline");
		System.out.println("[5]- " + "Get project overview");
		System.out.println("[9]- " + "Return");

		int input = -1;
		try {
			input = this.scanner.nextInt();
		} catch (Exception e) {
			input = -1;
		}

		if (input > -1 && input < 10) {
			System.out.print("Going to... ");
			switch (input) {
				case 0: System.out.println("User Selection"); 		break;
				case 1: System.out.println("Add activity"); 		break;
				case 2: System.out.println("Assign employee");  	break;
				case 3: System.out.println("Edit project name"); 	break;
				case 4: System.out.println("Set project deadline"); break;
				case 5: System.out.println("Get project overview"); break;
				case 9: System.out.println("Return");
			}
			this.test[8] = true;
			return input;
		} else {
			System.out.println("Your input of is not recognized as a valid option!");
			System.out.println("Input any key to return to the menu...");
			this.scanner = new Scanner(System.in);
			try {
				this.scanner.next();
			} catch (Exception e) {
				System.out.println("Warning bad input when returning to Main Menu!!");
			}
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

		this.test[13] = true;
		int input = UserIntInput() - 1;

		return activities.get(input);
	}

	public int chooseEmployee(){
		System.out.println("Please enter employee ID to assign ");
		this.test[14] = true;
		int input = UserIntInput();
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

		int input = UserIntInput()-1;
		while (!(input <= list.size() && input > 0)) {
			System.out.println("Please choose a value from 1 to " + list.size());
			input = UserIntInput()-1;
		}

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
		System.out.println("\tProjects: \tDeadline: \tEmployees: \tHoursWorked: ");
		int size = employees.size() > projects.size() ? employees.size() : projects.size();
		System.out.println("");
		for (int i = 0; i < size; i++){
			String projectName = "\t";
			String deadline = "\t";
			if (i < projects.size()) {
				projectName = projects.get(i).getProjectName();
				try {
					deadline = String.valueOf(projects.get(i).getDeadline());
				} catch (Exception e) {
					deadline = "N/A";
				}
			}
			String employeeID = "\t";
			String hoursWorked = "\t";
			if (i < employees.size()) {
				employeeID = "employee " + employees.get(i).getId();
				hoursWorked = String.valueOf(employees.get(i).getWeeksWorkInHalfHours()/2);
			}

			System.out.print("\t" + projectName + "\t" +
					deadline + "\t\t\t");
			System.out.print(employeeID + "\t\t" + hoursWorked + "\n");
		}
		System.out.println("");
		System.out.println("Press any key to continue:");
		String input = this.scanner.next();

		this.test[17] = true;
	}

	public int UserIntInput(){
		int input = -1;
		while (input == -1) {
			this.scanner = new Scanner(System.in);
			try {
				input = this.scanner.nextInt();
			} catch (InputMismatchException m) {
				System.out.println("You need to insert an Integer. Try again...");

			}
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
		System.out.println("[3]- " + "Add employee");
		System.out.println("[4]- " + "Company Overview");
		System.out.println("[5]- " + "Reports");
		System.out.println("[6]- " + "xxx");
		System.out.println("[7]- " + "yyy");
		System.out.println("[8]- " + "zzz");
		System.out.println("[9]- " + "Exit Program");
		System.out.println("[0]- " + "User Selection");
		
		this.test[31] = true;
		
		int input = -1;
		try {
			input = this.scanner.nextInt();
		} catch (Exception e) {
			input = -1;
		}
		
		if (input > -1 && input < 10) {
			System.out.print("Going to... ");
			switch (input) {
			case 0: System.out.println("User Selection"); 		break;
			case 1: System.out.println("Time Registration"); 	break;
			case 2: System.out.println("Projects"); 			break;
			case 3: System.out.println("Add employee"); 	break;
			case 4: System.out.println("Company Overview"); 			break;
			case 5: System.out.println("Reports"); 				break;
			case 6: System.out.println("xxx"); 					break;
			case 7: System.out.println("yyy"); 					break;
			case 8: System.out.println("zzz"); 					break;
			case 9: System.out.println("Exit program");			break;
			}
			this.test[25] = true;
			return input;
		} else {
			System.out.println("Your input of is not recognized as a valid option!");
			System.out.println("Input any key to return to the menu...");
			this.scanner = new Scanner(System.in);
			try {
				this.scanner.next();
			} catch (Exception e) {
				System.out.println("Warning bad input when returning to Main Menu!!");
			}
			this.test[26] = true;
			PageBreak();
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
		try {
			input = this.scanner.nextInt();
		} catch (Exception e) {
			input = -1;
		}

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
			System.out.println("Your input of is not recognized as a valid option!");
			System.out.println("Input any key to return to the menu...");
			this.scanner = new Scanner(System.in);
			try {
				this.scanner.next();
			} catch (Exception e) {
				System.out.println("Warning bad input when returning to Main Menu!!");
			}
			PageBreak();
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
		try {
			input = this.scanner.nextInt();
		} catch (Exception e) {
			input = -1;
		}

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
			System.out.println("Your input of is not recognized as a valid option!");
			System.out.println("Input any key to return to the menu...");
			this.scanner = new Scanner(System.in);
			try {
				this.scanner.next();
			} catch (Exception e) {
				System.out.println("Warning: bad input when returning to Main Menu!!");
			}
			PageBreak();
			return -1;
		}
	}

	public void printErrorMessage(ErrorMessageHolder errorMessage) {
		System.out.println(errorMessage.getErrorMessage());
		test[32] = true;
	}

	public LocalDate editProjectDeadline() {
		boolean confirm = false;
		LocalDate date;
		int year;
		int month;
		int day;
		while (confirm == false) {
			System.out.println("Please choose a date");
			System.out.println("Enter year:");
			year = getUserInputInt(LocalDate.now().getYear(), LocalDate.now().getYear() + 100);
			System.out.println("Enter month:");
			month = getUserInputInt(1, 12);
			date = LocalDate.of(year, month, 1);
			System.out.println("Enter day:");
			day = getUserInputInt(1, date.lengthOfMonth());
			System.out.println("The date [year-month-day]: " + year + "-" + month + "-" + day + " has been chosen");
			confirm = ConfirmInput();
			date = LocalDate.of(year, month, day);
			if (confirm == true) {
				return date;
			}
		}
		return null;
	}

	public int getUserInputInt(int minSize,int maxSize) {
		int input = this.scanner.nextInt();
		while (input < minSize || input >= maxSize) {
			System.out.println("Input must be between " + minSize + " & " + maxSize + ". Please try again:");
			input = this.scanner.nextInt();
		}
		return input;
	}

	public boolean ConfirmInput() {
		String input = "";
		System.out.println("Please input 'y' if your input is correct, else input 'n' to go back.");
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

	public Boolean addEmployee(int employeeID) {
		System.out.println("You are about to add a new employee:");
		System.out.println("");
		System.out.println("Employee: \temployee " + employeeID + "\t ID: \t" + employeeID);
		System.out.println("");
		System.out.println("Press 'y' to continue, press 'n' to return to Project Menu");
		String input = this.scanner.next();
		if (input.equalsIgnoreCase("y")) {
			Employee employee = new Employee(employeeID);
			return true;
		} else {
			return false;
		}
	}
}
