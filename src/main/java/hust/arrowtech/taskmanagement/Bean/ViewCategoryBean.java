package hust.arrowtech.taskmanagement.Bean;

import hust.arrowtech.taskmanagement.entity.SkillCategory;
import hust.arrowtech.taskmanagement.service.SkillCategoryController;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class ViewCategoryBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private int id;
	private SkillCategory category;


	@Inject
	SkillCategoryController categoryController;

	
	public ViewCategoryBean() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public SkillCategory getCategory() {
		if (category == null){
			category = categoryController.find(getId());
		}
		
		return category;
	}

	public void setCategory(SkillCategory category) {
		this.category = category;
	}

	@PostConstruct
	public void postConstruct() {
		
	}
	
	public String getStatus(boolean status){
		return status? "Enable" : "Disable";
	}
	
}
