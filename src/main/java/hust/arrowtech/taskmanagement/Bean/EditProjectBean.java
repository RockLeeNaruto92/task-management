package hust.arrowtech.taskmanagement.Bean;

import hust.arrowtech.taskmanagement.entity.Project;
import hust.arrowtech.taskmanagement.service.ProjectController;
import hust.arrowtech.taskmanagement.util.Page;
import hust.arrowtech.taskmanagement.util.Utils;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class EditProjectBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private Project project;
	
	@Inject
	IndexBean indexBean;
	@Inject
	ProjectController projectController;
	@Inject
	ViewProjectBean viewProjectBean;

	public EditProjectBean() {
		// TODO Auto-generated constructor stub
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	@PostConstruct
	public void postConstruct() {
	}

	public void editProject() {
		
		if (checkForm()){
			projectController.editProject(project);
			
			Utils.addMessage("Save");
			indexBean.setPath(Page.VIEW_PROJECT);
			viewProjectBean.setProjectId(project.getId());
		}
	}

	public boolean checkForm() {
		if (project.getName().equals("")){
			Utils.addMessage("Please input name field!");
			return false;
		}
		
		if (project.getDescription().equals("")){
			Utils.addMessage("Please input description field!");
			return false;
		}
		
		if (project.getCustomer().equals("")){
			Utils.addMessage("Please input customer field!");
			return false;
		}
		
		if (project.getStartDate().compareTo(project.getExpectedEndDate()) > 0){
			Utils.addMessage("Start date field must be before Expected end date!");
			return false;
		}

		return true;
	}
	
	public void reinit(){
		
	}
}
