package hust.arrowtech.taskmanagement.Bean;

import hust.arrowtech.taskmanagement.service.ProjectController;
import hust.arrowtech.taskmanagement.service.SkillCategoryController;
import hust.arrowtech.taskmanagement.service.SkillController;
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
public class AddSkillCategoryBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	
	private String skillName;
	private String skillDescription;
	private boolean skillStatus = false;
	private int skillId;
	
	@Inject
	IndexBean indexBean;
	@Inject
	SkillCategoryController skillController;

	public AddSkillCategoryBean() {
		// TODO Auto-generated constructor stub
	}


	public String getSkillName() {
		return skillName;
	}


	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}


	public String getSkillDescription() {
		return skillDescription;
	}


	public void setSkillDescription(String skillDescription) {
		this.skillDescription = skillDescription;
	}


	public boolean isSkillStatus() {
		return skillStatus;
	}


	public void setSkillStatus(boolean skillStatus) {
		this.skillStatus = skillStatus;
	}


	@PostConstruct
	public void postConstruct() {
	}

	public void addSkillCategory() {
		
		if (checkForm()){
			skillId = skillController.addSkillCategory(skillName, skillDescription, skillStatus);
			
			if (skillId != 0){
				Utils.addMessage("Added skill!");
				indexBean.setPath(Page.SKILL_CATEGORY);
				reinit();
			}
		}
	}

	public boolean checkForm() {
		if (this.skillName.equals("")){
			Utils.addMessage("Please input name field!");
			return false;
		}
		
		if (this.skillDescription.equals("")){
			Utils.addMessage("Please input description field!");
			return false;
		}
		
		return true;
	}
	
	public void reinit(){
		this.skillName = null;
		this.skillDescription = null;
		this.skillStatus = false;
	}
	
	public String getStatus(){
		return skillStatus ? "Enable" : "Disable";
	}
	
	public void updateStatus(){
		this.skillName = null;
		this.skillDescription = null;
	}
}
