package dtu.company.app;

public class ActivitySteps {
    private CompanyApp companyApp;
    private ErrorMessageHolder errorMessage;

    public ActivitySteps(ErrorMessageHolder errorMessage, CompanyHelper companyHelper){
        this.companyApp = companyHelper.getCompanyHelper();
        this.errorMessage = errorMessage;
    }


}
