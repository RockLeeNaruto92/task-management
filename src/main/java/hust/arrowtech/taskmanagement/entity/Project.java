package hust.arrowtech.taskmanagement.entity;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the project database table.
 * 
 */
@Entity
@NamedQuery(name="Project.findAll", query="SELECT p FROM Project p")
public class Project implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	private String customer;

	private String description;

	@Temporal(TemporalType.DATE)
	@Column(name="\"expectedEndDate\"")
	private Date expectedEndDate;

	private String name;

	@Temporal(TemporalType.DATE)
	@Column(name="\"startDate\"")
	private Date startDate;

	//bi-directional many-to-many association to Skill
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(
		name="project_skill"
		, joinColumns={
			@JoinColumn(name="project_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="skill_id")
			}
		)
	private List<Skill> skills;

	//bi-directional many-to-many association to User
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(
		name="project_user"
		, joinColumns={
			@JoinColumn(name="projectid")
			}
		, inverseJoinColumns={
			@JoinColumn(name="username")
			}
		)
	private List<User> users;

	//bi-directional many-to-one association to ProjectSkill
	@OneToMany(mappedBy="project", fetch=FetchType.EAGER)
	private List<ProjectSkill> projectSkills;

	public Project() {
	}

	public Project(Integer id, String customer, String description,
			Date expectedEndDate, String name, Date startDate) {
		super();
		this.id = id;
		this.customer = customer;
		this.description = description;
		this.expectedEndDate = expectedEndDate;
		this.name = name;
		this.startDate = startDate;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCustomer() {
		return this.customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getExpectedEndDate() {
		return this.expectedEndDate;
	}

	public void setExpectedEndDate(Date expectedEndDate) {
		this.expectedEndDate = expectedEndDate;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public List<Skill> getSkills() {
		return this.skills;
	}

	public void setSkills(List<Skill> skills) {
		this.skills = skills;
	}

	public List<User> getUsers() {
		return this.users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<ProjectSkill> getProjectSkills() {
		return this.projectSkills;
	}

	public void setProjectSkills(List<ProjectSkill> projectSkills) {
		this.projectSkills = projectSkills;
	}

	public ProjectSkill addProjectSkill(ProjectSkill projectSkill) {
		getProjectSkills().add(projectSkill);
		projectSkill.setProject(this);

		return projectSkill;
	}

	public ProjectSkill removeProjectSkill(ProjectSkill projectSkill) {
		getProjectSkills().remove(projectSkill);
		projectSkill.setProject(null);

		return projectSkill;
	}

	public void addUser(User user){
		this.users.add(user);
	}
	
	public void addSkill(Skill skill){
		this.skills.add(skill);
	}
}