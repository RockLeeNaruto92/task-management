package hust.arrowtech.taskmanagement.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the skill database table.
 * 
 */
@Entity
@NamedQuery(name="Skill.findAll", query="SELECT s FROM Skill s")
public class Skill implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	private String description;

	private String name;

	private Boolean status;

	//bi-directional many-to-many association to Project
	@ManyToMany(mappedBy="skills", fetch=FetchType.EAGER)
	private List<Project> projects;

	//bi-directional many-to-one association to ProjectSkill
	@OneToMany(mappedBy="skill", fetch=FetchType.EAGER)
	private List<ProjectSkill> projectSkills;

	//bi-directional many-to-one association to SkillCategory
	@ManyToOne
	@JoinColumn(name="category")
	private SkillCategory skillCategory;

	//bi-directional many-to-one association to UserSkill
	@OneToMany(mappedBy="skill", fetch=FetchType.EAGER)
	private List<UserSkill> userSkills;

	public Skill() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getStatus() {
		return this.status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public List<Project> getProjects() {
		return this.projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	public List<ProjectSkill> getProjectSkills() {
		return this.projectSkills;
	}

	public void setProjectSkills(List<ProjectSkill> projectSkills) {
		this.projectSkills = projectSkills;
	}

	public ProjectSkill addProjectSkill(ProjectSkill projectSkill) {
		getProjectSkills().add(projectSkill);
		projectSkill.setSkill(this);

		return projectSkill;
	}

	public ProjectSkill removeProjectSkill(ProjectSkill projectSkill) {
		getProjectSkills().remove(projectSkill);
		projectSkill.setSkill(null);

		return projectSkill;
	}

	public SkillCategory getSkillCategory() {
		return this.skillCategory;
	}

	public void setSkillCategory(SkillCategory skillCategory) {
		this.skillCategory = skillCategory;
	}

	public List<UserSkill> getUserSkills() {
		return this.userSkills;
	}

	public void setUserSkills(List<UserSkill> userSkills) {
		this.userSkills = userSkills;
	}

	public UserSkill addUserSkill(UserSkill userSkill) {
		getUserSkills().add(userSkill);
		userSkill.setSkill(this);

		return userSkill;
	}

	public UserSkill removeUserSkill(UserSkill userSkill) {
		getUserSkills().remove(userSkill);
		userSkill.setSkill(null);

		return userSkill;
	}

}