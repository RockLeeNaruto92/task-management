package hust.arrowtech.taskmanagement.Bean;

import hust.arrowtech.taskmanagement.entity.Project;
import hust.arrowtech.taskmanagement.entity.ProjectSkill;
import hust.arrowtech.taskmanagement.entity.Skill;
import hust.arrowtech.taskmanagement.entity.SkillCategory;
import hust.arrowtech.taskmanagement.entity.User;
import hust.arrowtech.taskmanagement.service.ProjectController;
import hust.arrowtech.taskmanagement.service.ProjectSkillController;
import hust.arrowtech.taskmanagement.service.SkillCategoryController;
import hust.arrowtech.taskmanagement.service.SkillController;
import hust.arrowtech.taskmanagement.service.UserController;
import hust.arrowtech.taskmanagement.util.Page;
import hust.arrowtech.taskmanagement.util.Utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class ViewProjectBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final int CLICK_EDIT_PROJECT = 0;
	private static final int CLICK_ADD_USER = 1;
	private static final int CLICK_ADD_SKILL = 2;
	/**
	 * 
	 */
	private String labelName;
	private String labelDescription;
	private String labelCustomer;
	private String labelStartDate;
	private String labelExpectedEndDate;
	
	private Project project;
	private List<ProjectSkill> projectSkill;
	private int projectId;
	private String username;
	private String skillName;
	
	private List<SelectItem> skills;
	private int selectedSkill;
	private int experienceYear;
	private int level;
	
	@Inject
	SkillController skillController;
	@Inject
	ProjectController projectController;
	@Inject 
	UserController userController;
	@Inject
	ViewProjectBean viewProjectBean;
	@Inject
	EditProjectBean	editProjectBean;
	@Inject
	IndexBean indexBean;
	@Inject
	SkillCategoryController categoryController;
	@Inject
	ProjectSkillController psController;

	public ViewProjectBean() {
		// TODO Auto-generated constructor stub
	}

	public String getLabelName() {
		return labelName;
	}

	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}

	public String getLabelDescription() {
		return labelDescription;
	}

	public void setLabelDescription(String labelDescription) {
		this.labelDescription = labelDescription;
	}

	public String getLabelCustomer() {
		return labelCustomer;
	}

	public void setLabelCustomer(String labelCustomer) {
		this.labelCustomer = labelCustomer;
	}

	public String getLabelStartDate() {
		return labelStartDate;
	}

	public void setLabelStartDate(String labelStartDate) {
		this.labelStartDate = labelStartDate;
	}

	public String getLabelExpectedEndDate() {
		return labelExpectedEndDate;
	}

	public void setLabelExpectedEndDate(String labelExpectedEndDate) {
		this.labelExpectedEndDate = labelExpectedEndDate;
	}

	public List<ProjectSkill> getProjectSkill() {
		return project.getProjectSkills();
	}

	public void setProjectSkill(List<ProjectSkill> projectSkill) {
		this.projectSkill = projectSkill;
	}

	public Project getProject() {
		if (project == null){
			project = projectController.find(getProjectId());
		}
		
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSkillName() {
		return skillName;
	}

	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}

	public int getExperienceYear() {
		if (this.experienceYear > 10)
			this.experienceYear = 10;
		else if (this.experienceYear < 0)
			this.experienceYear = 0;
		return experienceYear;
	}

	public void setExperienceYear(int experienceYear) {
		this.experienceYear = experienceYear;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getSelectedSkill() {
		return selectedSkill;
	}

	public void setSelectedSkill(int selectedSkill) {
		this.selectedSkill = selectedSkill;
	}

	public List<SelectItem> getSkills() {
		if (skills == null){
			List<SkillCategory> categoryList = categoryController.getSkillCategories();
			
			skills = new ArrayList<SelectItem>();
			
			for (SkillCategory skillCategory : categoryList) {
				SelectItemGroup group = new SelectItemGroup(skillCategory.getName());
				SelectItem items[] = new SelectItem[skillCategory.getSkills().size()];
				int i = 0;
				
				for (Skill skill : skillCategory.getSkills()) {
					items[i++] = new SelectItem(skill.getId(), skill.getName());
				}
				
				group.setSelectItems(items);
				
				skills.add(group);
			}
		}
		return skills;
	}

	public void setSkills(List<SelectItem> skills) {
		this.skills = skills;
	}

	@PostConstruct
	public void postConstruct() {
		this.labelName = "Name";
		this.labelDescription = "Description";
		this.labelCustomer = "Customer";
		this.labelStartDate = "Start date";
		this.labelExpectedEndDate = "Expected end date";
	}
	
	public List<String> getSuggestList(String tag){
		return this.userController.getList(tag);
	}
	
	public void addUser(){
		User user = userController.find(username);
		viewProjectBean.projectId = projectId;
		
		if (user == null){
			Utils.addMessage("User " + username + " is not exist!");
			return;
		}
		
		if (projectController.containUser(project, username)){
			Utils.addMessage("Can't add because user already in project!");
			return;
		}
		
		projectController.addUser(project, user);
		Utils.addMessage("Added user " + username);
	}
	
	public void addSkill(){
		if (projectController.containeSkill(project, selectedSkill)){
			Utils.addMessage("Skill already exist in required skills list!");
			return;
		}
		
		Skill skill = skillController.find(selectedSkill);
		ProjectSkill ps;
		
		projectController.addSkill(project, skill);
		ps = psController.find(project, skill);
		
		psController.update(ps, experienceYear, level);
		project.addProjectSkill(ps);
		
		Utils.addMessage("Add skill!");
	}
	
	public void onClick(int choose){
		switch(choose){
		case CLICK_EDIT_PROJECT:
			indexBean.setPath(Page.EDIT_PROJECT);
			editProjectBean.setProject(project);
			break;
			
		case CLICK_ADD_USER: 
			addUser();
			break;
		
		case CLICK_ADD_SKILL:
			addSkill();
			break;

		default:
			break;
		}
	}
}
