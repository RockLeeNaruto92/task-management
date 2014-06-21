package hust.arrowtech.taskmanagement.Bean;

import hust.arrowtech.taskmanagement.entity.Skill;
import hust.arrowtech.taskmanagement.entity.SkillCategory;
import hust.arrowtech.taskmanagement.service.SkillCategoryController;
import hust.arrowtech.taskmanagement.service.SkillController;
import hust.arrowtech.taskmanagement.util.Page;
import hust.arrowtech.taskmanagement.util.Utils;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class AddSkillBean implements Serializable {
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
	private String skillCategory;
	private int skillId;
	
	@Inject
	IndexBean indexBean;
	@Inject
	ViewCategoryBean viewCategoryBean;
	@Inject
	SkillController skillController;
	@Inject
	SkillCategoryController skillCategoryController;

	public AddSkillBean() {
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


	public String getSkillCategory() {
		return skillCategory;
	}


	public void setSkillCategory(String skillCategory) {
		this.skillCategory = skillCategory;
	}


	@PostConstruct
	public void postConstruct() {
	}

	public void addSkill() {
		if (checkForm()){
			SkillCategory theSkillCategory = skillCategoryController.find(skillCategory);
			
			if (theSkillCategory != null){
				Skill skill = skillController.addSkill(skillName, skillDescription, skillStatus, theSkillCategory);
				
				Utils.addMessage("Added skill " + skillName + " to skill category " + skillCategory);
				indexBean.setPath(Page.VIEW_CATEGORY);
				viewCategoryBean.setId(theSkillCategory.getId());
				
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
	
	public List<String> getSkillCategoriesList(){
		return skillCategoryController.getSkillCategoriesName();
	}
}
