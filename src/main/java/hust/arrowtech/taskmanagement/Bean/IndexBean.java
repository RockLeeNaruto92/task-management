package hust.arrowtech.taskmanagement.Bean;

import hust.arrowtech.taskmanagement.service.ProjectController;
import hust.arrowtech.taskmanagement.util.Page;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class IndexBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2406018016768670150L;
	
	public static final int ON_ADD_PROJECT_CLICK = 0;
	public static final int ON_ADD_USER_CLICK = 1;
	public static final int ON_SKILL_CATEGORY = 2;
	public static final int ON_ADD_SKILL_CLICK = 3;

	private String btnAddProject;
	private String btnAddUser;
	private String path;
	private String tempUser;

	@Inject
	ProjectController prController;

	public String getBtnAddProject() {
		return btnAddProject;
	}

	public void setBtnAddProject(String btnAddProject) {
		this.btnAddProject = btnAddProject;
	}

	public String getBtnAddUser() {
		return btnAddUser;
	}

	public void setBtnAddUser(String btnAddUser) {
		this.btnAddUser = btnAddUser;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getTempUser() {
		return tempUser;
	}

	public void setTempUser(String tempUser) {
		this.tempUser = tempUser;
	}

	@PostConstruct
	public void postConstruct() {
		this.btnAddProject = "Add new project";
		this.btnAddUser = "Add new user";
		
	}

	public void onClick(int choose) {
		switch (choose) {
		case ON_ADD_PROJECT_CLICK:
			this.path = Page.ADD_PROJECT;
			break;
			
		case ON_ADD_USER_CLICK:
			this.path = Page.ADD_USER;
			break;
			
		case ON_SKILL_CATEGORY:
			this.path = Page.SKILL_CATEGORY;
			break;
			
		case ON_ADD_SKILL_CLICK:
			this.path = Page.ADD_SKILL;
			break;

		default:
			break;
		}
	}
	
	public void close(){
		this.path = Page.DEFAULT;
	}
}
