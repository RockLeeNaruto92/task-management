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

	//bi-directional many-to-one association to Milestone
	@OneToMany(mappedBy="project", fetch=FetchType.EAGER)
	private List<Milestone> milestones;

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

	//bi-directional many-to-one association to ProjectSkill
	@OneToMany(mappedBy="project", fetch=FetchType.EAGER)
	private List<ProjectSkill> projectSkills;

	//bi-directional many-to-one association to Task
	@OneToMany(mappedBy="project", fetch=FetchType.EAGER)
	private List<Task> tasks;

	//bi-directional many-to-many association to TaskType
	@ManyToMany(mappedBy="projects", fetch=FetchType.EAGER)
	private List<TaskType> taskTypes;

	//bi-directional many-to-many association to User
	@ManyToMany(mappedBy="projects", fetch=FetchType.EAGER)
	private List<User> users;

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

	public List<Milestone> getMilestones() {
		return this.milestones;
	}

	public void setMilestones(List<Milestone> milestones) {
		this.milestones = milestones;
	}

	public Milestone addMilestone(Milestone milestone) {
		getMilestones().add(milestone);
		milestone.setProject(this);

		return milestone;
	}

	public Milestone removeMilestone(Milestone milestone) {
		getMilestones().remove(milestone);
		milestone.setProject(null);

		return milestone;
	}

	public List<Skill> getSkills() {
		return this.skills;
	}

	public void setSkills(List<Skill> skills) {
		this.skills = skills;
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

	public List<Task> getTasks() {
		return this.tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	public Task addTask(Task task) {
		getTasks().add(task);
		task.setProject(this);

		return task;
	}

	public Task removeTask(Task task) {
		getTasks().remove(task);
		task.setProject(null);

		return task;
	}

	public List<TaskType> getTaskTypes() {
		return this.taskTypes;
	}

	public void setTaskTypes(List<TaskType> taskTypes) {
		this.taskTypes = taskTypes;
	}

	public List<User> getUsers() {
		return this.users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	public void addUser(User user){
		this.users.add(user);
	}
	
	public void removeUser(User user){
		this.users.remove(user);
	}
	
	public void addTasktype(TaskType tasktype){
		this.taskTypes.add(tasktype);
	}
	
	public void removeTasktype(TaskType tasktype){
		this.taskTypes.remove(tasktype);
	}

}