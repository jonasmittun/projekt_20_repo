package dtu.company.app;

import java.time.LocalDate;
import java.util.ArrayList;

public class Controller {

	static CompanyApp companyApp;
	static View view;
	static int currentUserID = -1;
	private static ArrayList<String> userActivities;
	private static ArrayList<String> userProjects;
	private static ErrorMessageHolder errorMessage = new ErrorMessageHolder();


	public static void main(String[] args) throws Exception {
		companyApp = new CompanyApp();
		view = new View();

		companyApp = setSampleCompany(companyApp);

		view.StartUpText();

		view.PageBreak();

		SelectUser();

		checkForExpiredProjects();

		view.PageBreak();

		MainMenu();

		//Gather users activities from CompanyApp

		view.ShutDownText();

	}

	public static void SelectUser() {
		boolean idConfirmed = false;
		while(!idConfirmed) {
			view.UserIDText();
			currentUserID = view.UserIntInput();
			try {
				companyApp.setUser(currentUserID);
				idConfirmed = view.ConfirmUserID(currentUserID);
				return;
			} catch (Exception e) {
				errorMessage.setErrorMessage(e.getMessage());
				view.printErrorMessage(errorMessage);
				SelectUser();
			}
		}
		System.out.println("User set in companyApp is now: " + companyApp.getUser());
	}

	public static void checkForExpiredProjects() {
		int counter = 0;
		for (Project project : companyApp.getProjects()) {
			if (currentUserID == project.getProjectLeaderID() && project.isExpired()) {
				counter++;
			}
		}
		if (counter > 0) {
			view.PageBreak();
			errorMessage.setErrorMessage("You currently have " + counter + " expired project(s)");
			view.printErrorMessage(errorMessage);
		}
	}

	public static void MainMenu() throws Exception {
		int result = -1;
		while(result == -1) {
			result = view.MainMenu(currentUserID);
		}
		switch(result) {
		case 0: view.PageBreak(); SelectUser();			break;
		case 1: view.PageBreak(); RegisterMenu();		break;
		case 2: view.PageBreak(); ProjectMenu(); 		break;
		case 3: view.PageBreak(); addEmployee();		break;
		case 4: view.PageBreak(); companyOverview();	break;
		case 5: view.PageBreak(); employeeOverview(currentUserID);	break;
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
			result = view.RegisterMenu(currentUserID);
		}
		switch(result) {
			case 0: view.PageBreak(); SelectUser();					break;
			case 1: view.PageBreak(); addHours(currentUserID);		break;
			case 2: view.PageBreak(); removeHours(currentUserID);	break;
			case 9: return;
		}
		view.PageBreak();
		RegisterMenu();
	}

	public static void ProjectMenu() throws Exception {
		int result = -1;
		while(result == -1) {
			result = view.ProjectMenu(currentUserID);
		}
		switch(result) {
			case 0: view.PageBreak(); SelectUser();	break;
			case 1: view.PageBreak(); accessProject(currentUserID);	break;
			case 2: view.PageBreak(); addProject(currentUserID);	break;
			case 9: return;
		}
		view.PageBreak();
		ProjectMenu();
	}

	public static void ProjectAccessMenu(String chosenProject) throws Exception {
		int result = -1;
		boolean isProjectLeader = companyApp.getProject(chosenProject).getProjectLeaderID()==currentUserID;
		while(result == -1) {
			result = view.projectAccessMenu(isProjectLeader); //projectAccessMenu i view
		}
		if (companyApp.getProject(chosenProject).getProjectLeaderID()==currentUserID) {
			switch (result) {
				case 1:
					view.PageBreak();
					addActivity(chosenProject);
					break;
				case 2:
					view.PageBreak();
					assignEmployee(chosenProject);
					break;
				case 3:
					view.PageBreak();
					editProjectName(chosenProject);
					break;
				case 4:
					view.PageBreak();
					setProjectDeadline(chosenProject);
					break;
				case 5:
					view.PageBreak();
					getProjectOverview(chosenProject);
					break;
				case 9:
					return;
			}
		} else if (companyApp.getProject(chosenProject).getProjectLeaderID()==0) {
			switch (result) {
				case 1:
					view.PageBreak();
					setProjectLeader(chosenProject);
					break;
				case 9:
					break;
			}
		}
		view.PageBreak();
		ProjectAccessMenu(chosenProject);
	}

	private static void setProjectLeader(String chosenProject) {
		int projectLeader = view.setProjectLeader(chosenProject);
		if (projectLeader == -1 ){
			return;
		} else {
			companyApp.getProject(chosenProject).setProjectLeaderID(projectLeader);
		}

	}

	public static void addEmployee() {
		int employeeID = companyApp.getEmployees().size() + 1;
		Employee employee = new Employee(employeeID);
		if (view.addEmployee(employeeID)) {
			companyApp.addNewEmployee(employee);
		} else {
			return;
		}
	}

	public static void editProjectName(String chosenProject) throws Exception{
		String oldName = companyApp.getProject(chosenProject).getProjectName();
		String newName = view.editProjectNameMenu();
		while(newName == null){
			newName = view.editProjectNameMenu();
		}
		try {
			companyApp.getProject(chosenProject).setProjectName(newName, currentUserID);
		}catch (Exception e){
			System.out.println("Operation failed. Please check the project name and try again.");
		}
		System.out.println(oldName + " has successfully edited to " + newName);

	}

	private static void assignEmployee(String chosenProject) throws Exception {
			//Get the project from the app with the name chosenProject
			Project project = companyApp.getProject(chosenProject);

			Activity activity = view.activityOverviewAndAssignEmployee(project);
			int employeeID = view.chooseEmployee();

			//Error handling - if employee has more than 20 activities, process fails
			try {
				activity.assignEmployee(employeeID, companyApp.getEmployee(employeeID).getNumberOfActivities(), currentUserID);
			}catch(Exception e){
				System.out.println("The process has failed. Check employees working hours/employee ID and try again.");
				view.PageBreak();
				assignEmployee(chosenProject);
			}
			System.out.println("Employee " + employeeID + " is successfully assigned to the "
					+ activity.getActivityName() + project.getProjectName());

	}

	private static void setProjectDeadline(String chosenProject) {
		try {
			LocalDate newDeadline = view.editProjectDeadline();
			int year = newDeadline.getYear();
			int month = newDeadline.getMonthValue();
			int day = newDeadline.getDayOfMonth();
			companyApp.getProject(chosenProject).setDeadline(year, month, day);
			errorMessage.setErrorMessage("Deadline has been updated!");
			view.printErrorMessage(errorMessage);
		} catch (Exception e) {
			errorMessage.setErrorMessage(e.getMessage());
			view.printErrorMessage(errorMessage);
		}
	}

	private static void getProjectOverview(String chosenProject) {
		ArrayList<Activity> activities;
		activities = companyApp.getProject(chosenProject).getActivities();
		view.getProjectOverview(activities, chosenProject);
	}

	private static void companyOverview() throws Exception {
		ArrayList<Project> projects;
		projects = companyApp.getProjects();
		ArrayList<Employee> employees;
		employees = companyApp.getEmployees();

		view.companyOverview(projects, employees);
	}

	private static void employeeOverview(int CurrentUserID) {

		view.employeeOverview(companyApp, CurrentUserID);
	}

	public static void addHours(int CurrentUserID) throws Exception {
		//Gets user activities
		userActivities = companyApp.getUserActivities(CurrentUserID);

		if (userActivities.isEmpty()){
			System.out.println("You have no active activities");
			return;
		}

		//Runs Register Menu to get users to register hours
		String s = view.activityOverview(userActivities);

		if (s.equals("")){
			System.out.println("Please enter a value between 1 and " + userActivities.size());
			return;
		}

		//Saves users decision
		//System.out.println(s);
		String projectName = s.substring(0,s.indexOf(':'));
		int activityID = Integer.parseInt(s.substring(s.indexOf(':')+1,s.lastIndexOf(':')));
		int halfHours = Integer.parseInt(s.substring(s.lastIndexOf(':')+1,s.length()));

		//Registers users hours

		try {
			companyApp.registerDaysWork(CurrentUserID, halfHours, activityID, projectName);
		} catch (Exception e){
			System.out.println("You can't work more than 24 hours. Try again");
			System.out.println("");
		}
		double activityWork = companyApp.getProject(projectName).getActivityWithID(activityID).getWorkedHalfHours()/2;
		double weeksWork = companyApp.getEmployee(CurrentUserID).getWeeksWorkInHalfHours()/2;

		System.out.println("Hours worked on activity: " + activityWork + " | Hours worked this week: " + weeksWork);
	}

	public static void removeHours(int CurrentUserID) throws Exception {
		//Gets user activities
		userActivities = companyApp.getUserActivities(CurrentUserID);

		if (userActivities.isEmpty()){
			System.out.println("You have no active activities");
			return;
		}

		//Runs Register Menu to get users to register hours
		String s = view.activityOverview(userActivities);

		if (s.equals("")){
			System.out.println("Please enter a value between 1 and " + userActivities.size());
			return;
		}

		//Saves users decision
		//System.out.println(s);
		String projectName = s.substring(0,s.indexOf(':'));
		int activityID = Integer.parseInt(s.substring(s.indexOf(':')+1,s.lastIndexOf(':')));
		int halfHours = Integer.parseInt(s.substring(s.lastIndexOf(':')+1,s.length()));

		//Registers users hours
		companyApp.removeWeeksWork(CurrentUserID,halfHours,activityID,projectName);

		double activityWork = companyApp.getProject(projectName).getActivityWithID(activityID).getWorkedHalfHours()/2;
		double weeksWork = companyApp.getEmployee(CurrentUserID).getWeeksWorkInHalfHours()/2;

		System.out.println("Hours worked on activity: " + activityWork + " | Hours worked this week: " + weeksWork);
	}


	public static void accessProject(int currentUserID) throws Exception {
		//Gets user projects
		userProjects = companyApp.getLeaderProjects(currentUserID);

		//Find leaderless projects
		for (int i = 0; i < companyApp.getProjects().size(); i++) {
			if (companyApp.getProjects().get(i).getProjectLeaderID() == 0){
				userProjects.add(companyApp.getProjects().get(i).getProjectName() + ":No project leader assigned");
			}
		}

		//Runs Project Menu to get overview of the current user projects
		String chosenProject = view.projectOverview(userProjects);
		chosenProject = chosenProject.substring(0,chosenProject.lastIndexOf(':'));
		ProjectAccessMenu(chosenProject);
	}

	private static void addActivity(String chosenProject) throws Exception {
		int activities = 1;
		try {
			activities = companyApp.getProject(chosenProject).getActivities().size() + 1;
		} catch (Exception e) {
			activities = 1;
		}
		Activity activity = view.addActivity(activities, chosenProject, currentUserID);
		companyApp.addActivity(activity, chosenProject);

	}


	public static void addProject(int currentUserID) throws Exception{
		Project project = view.addProjectMenu(currentUserID);
		while(project == null){
			project = view.addProjectMenu(currentUserID);
		}
		try {
			companyApp.addProject(project);
		}catch(Exception e){
			System.out.println("Operation failed. Check Project name and try again.");
		}
	}

	public static CompanyApp setSampleCompany(CompanyApp companyApp) throws Exception {
		//int numberActivities = 0;
		for (int j = 1; j < 11; j++){
			//Projects are named "project <int>"
			Project project = new Project("project "+j);
//			}
			companyApp.addProject(project);
		}

		//Project 3 is expired
		int year = LocalDate.now().getYear();
		int month = LocalDate.now().getMonthValue();
		int day = LocalDate.now().getDayOfMonth();
		companyApp.getProject("project 1").setDeadline(year, month, day);

		companyApp.getEmployees().removeAll(companyApp.getEmployees());
		Employee employee;
		for (int i = 1; i < 11; i++){
			employee = new Employee(i);
			companyApp.addNewEmployee(employee);
		}

		//Set ProjectLeaders
		companyApp.getProjects().get(0).setProjectLeaderID(1);
		companyApp.getProjects().get(1).setProjectLeaderID(1);
		companyApp.getProjects().get(2).setProjectLeaderID(1);
		companyApp.getProjects().get(3).setProjectLeaderID(4);
		//projectList.get(4).setProjectLeaderID(5);
		companyApp.getProjects().get(5).setProjectLeaderID(6);
		companyApp.getProjects().get(6).setProjectLeaderID(7);
		companyApp.getProjects().get(7).setProjectLeaderID(8);
		companyApp.getProjects().get(8).setProjectLeaderID(9);
		//projectList.get(9).setProjectLeaderID(10);

		//Adding activities
		int numberActivities = 0;
		for (int j = 1; j < 11; j++){
			numberActivities = numberActivities + 5;
			for(int k = 1; k <= numberActivities; k++) {
				//Activities are named "activity <int>" and get their index as id
				Activity activity = new Activity("activity " + k, k);

				//sets user to be the project leader of the project
				if (j < 4) {
					companyApp.setUser(1);
				} else if (j == 4) {
					companyApp.setUser(4);
				} else if (j == 6) {
					companyApp.setUser(6);
				} else if (j == 7) {
					companyApp.setUser(7);
				} else if (j == 8) {
					companyApp.setUser(8);
				} else if (j == 9) {
					companyApp.setUser(9);
				}

				//now the activity can be added to the project
				if (j != 5 && j!= 10) {
					if (j == 5) {
						System.out.println("test!!! at " + companyApp.getUser() + " er " + companyApp.getProject("project " + j).getProjectLeaderID());
					}
					companyApp.addActivity(activity, companyApp.getProject("project " + j).getProjectName());
				}
				if (numberActivities == 25) {
					numberActivities = 0;
					break;
				}
			}
		}

		//Assigning employees
		int place = 1;
		int holder = 1;
		for (int i = 4; i >= 1; i--) {

			for (int j = 4; j >= holder; j--) {
				//sets user to be the project leader
				if (j < 4) {
					companyApp.setUser(1);
				} else if (j == 4) {
					companyApp.setUser(4);
				} else if (j == 6) {
					companyApp.setUser(6);
				} else if (j == 7) {
					companyApp.setUser(7);
				} else if (j == 8) {
					companyApp.setUser(8);
				} else if (j == 9) {
					companyApp.setUser(9);
				}

				for (int k = place; k < place + 5; k++){
					if (j != 5 && j!= 10) {
						companyApp.assignEmployee(i, k, "project " + j);
						//System.out.println("Employee:" + i + " Project:" + j + " Activity" + k);
					}
				}
			}
			holder++;
			place = place + 5;
		}

		return companyApp;
	}

}
