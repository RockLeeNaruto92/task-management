package hust.arrowtech.taskmanagement.Bean;

import hust.arrowtech.taskmanagement.service.ProjectController;
import hust.arrowtech.taskmanagement.util.Page;
import hust.arrowtech.taskmanagement.util.Utils;

import java.io.Serializable;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class AddProjectBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private int projectId;
	private String projectName;
	private String projectDescription;
	private String projectCustomer;
	private Date projectStartDate;
	private Date projectExpectedEndDate;

	private String labelInputName;
	private String labelInputDescription;
	private String labelInputCustomer;
	private String labelInputStartDate;
	private String labelInputExpectedEnddate;
	private String btnSubmmit;
	private String path;

	@Inject
	ProjectController projectController;
	@Inject
	ViewProjectBean viewProjectBean;
	@Inject
	IndexBean indexBean;

	public AddProjectBean() {
		// TODO Auto-generated constructor stub
	}

	public String getLabelInputName() {
		return labelInputName;
	}

	public void setLabelInputName(String labelInputName) {
		this.labelInputName = labelInputName;
	}

	public String getLabelInputDescription() {
		return labelInputDescription;
	}

	public void setLabelInputDescription(String labelInputDescription) {
		this.labelInputDescription = labelInputDescription;
	}

	public String getLabelInputCustomer() {
		return labelInputCustomer;
	}

	public void setLabelInputCustomer(String labelInputCustomer) {
		this.labelInputCustomer = labelInputCustomer;
	}

	public String getLabelInputStartDate() {
		return labelInputStartDate;
	}

	public void setLabelInputStartDate(String labelInputStartDate) {
		this.labelInputStartDate = labelInputStartDate;
	}

	public String getLabelInputExpectedEnddate() {
		return labelInputExpectedEnddate;
	}

	public void setLabelInputExpectedEnddate(String labelInputExpectedEnddate) {
		this.labelInputExpectedEnddate = labelInputExpectedEnddate;
	}

	public String getBtnSubmmit() {
		return btnSubmmit;
	}

	public void setBtnSubmmit(String btnSubmmit) {
		this.btnSubmmit = btnSubmmit;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectDescription() {
		return projectDescription;
	}

	public void setProjectDescription(String projectDescription) {
		this.projectDescription = projectDescription;
	}

	public String getProjectCustomer() {
		return projectCustomer;
	}

	public void setProjectCustomer(String projectCustomer) {
		this.projectCustomer = projectCustomer;
	}

	public Date getProjectStartDate() {
		return projectStartDate;
	}

	public void setProjectStartDate(Date projectStartDate) {
		this.projectStartDate = projectStartDate;
	}

	public Date getProjectExpectedEndDate() {
		return projectExpectedEndDate;
	}

	public void setProjectExpectedEndDate(Date projectExpectedEndDate) {
		this.projectExpectedEndDate = projectExpectedEndDate;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@PostConstruct
	public void postConstruct() {
		this.labelInputName = "Name";
		this.labelInputDescription = "Description";
		this.labelInputCustomer = "Customer";
		this.labelInputStartDate = "Start date";
		this.labelInputExpectedEnddate = "Expected end date";
		this.btnSubmmit = "Submmit";
	}

	public void addProject() {
		
		if (checkForm()){
			projectId = projectController.addProject(projectCustomer,
					projectDescription, projectName, projectExpectedEndDate,
					projectStartDate);
			
			if (projectId != 0) {
				Utils.addMessage("Successfull!");
				viewProjectBean.setProjectId(projectId);
				indexBean.setPath(Page.VIEW_PROJECT);
				reinit();
			}
		}
	}

	public boolean checkForm() {
		if (this.projectName.equals("")) {
			Utils.addMessage("Please input name field!");
			return false;
		}

		if (this.projectCustomer.equals("")) {
			Utils.addMessage("Please input customer field!");
			return false;
		}

		if (this.projectStartDate == null) {
			Utils.addMessage("Please input start date field!");
			return false;
		}

		if (this.projectExpectedEndDate == null) {
			Utils.addMessage("Please input expected end date field!");
			return false;
		}

		if (this.projectStartDate.compareTo(this.projectExpectedEndDate) > 0) {
			Utils.addMessage("Start date field must be before Expected end date!");
			return false;
		}

		return true;
	}
	
	public void reinit(){
		this.projectName = null;
		this.projectCustomer = null;
		this.projectDescription = null;
		this.projectStartDate = null;
		this.projectExpectedEndDate = null;
	}
}
