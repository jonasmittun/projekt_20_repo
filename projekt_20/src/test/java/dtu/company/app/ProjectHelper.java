package dtu.company.app;

public class ProjectHelper { //Asger

	private CompanyApp companyApp;

	public ProjectHelper (CompanyApp companyApp){
		this.companyApp = companyApp;
	}
	
	public Project getExampleProject() {
		return new Project("Library App");
	}
}