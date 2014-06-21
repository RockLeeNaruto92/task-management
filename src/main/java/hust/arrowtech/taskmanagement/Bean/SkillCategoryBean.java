package hust.arrowtech.taskmanagement.Bean;

import hust.arrowtech.taskmanagement.entity.SkillCategory;
import hust.arrowtech.taskmanagement.service.SkillCategoryController;
import hust.arrowtech.taskmanagement.util.Page;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class SkillCategoryBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<SkillCategory> skillLists;
	
	@Inject
	SkillCategoryController skillCategoryController;
	@Inject
	IndexBean indexBean;
	@Inject
	SkillCategoryBean skillCategoryBean;

	/**
	 * 
	 */

	public SkillCategoryBean() {
		// TODO Auto-generated constructor stub
	}

	public List<SkillCategory> getSkillLists() {
		if (skillLists == null){
			skillLists = skillCategoryController.getSkillCategories();
		}
		return skillLists;
	}

	public void setSkillLists(List<SkillCategory> skillLists) {
		this.skillLists = skillLists;
	}

	@PostConstruct
	public void postConstruct() {
	}
	
	public String getStatus(SkillCategory skill){
		return skill.getStatus()? "Enable" : "Disable";
	}
	
	public void onClick(int choose) {
		switch (choose) {
		case 0:
			indexBean.setPath(Page.ADD_SKILL_CATEGORY);
			reinit();
			break;

		default:
			break;
		}
	}
	
	public void reinit(){
		this.skillLists = null;
	}
}
