package hust.arrowtech.taskmanagement.entity;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the task database table.
 * 
 */
@Entity
@NamedQuery(name="Task.findAll", query="SELECT t FROM Task t")
public class Task implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	private String description;

	@Temporal(TemporalType.DATE)
	@Column(name="due_date")
	private Date dueDate;

	@Column(name="estimate_point")
	private Integer estimatePoint;

	private String name;

	@Temporal(TemporalType.DATE)
	@Column(name="start_date")
	private Date startDate;

	//bi-directional many-to-one association to Project
	@ManyToOne
	@JoinColumn(name = "project_id", referencedColumnName = "id", insertable = false, updatable = false)
	private Project project;

	//bi-directional many-to-one association to ProjectTasktype
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="project_id", referencedColumnName="project_id"),
		@JoinColumn(name="type", referencedColumnName="task_type_id")
		})
	private ProjectTasktype projectTasktype;

	//bi-directional many-to-one association to TaskType
	@ManyToOne
	@JoinColumn(name="type", referencedColumnName = "id", insertable = false, updatable = false)
	private TaskType taskType;

	public Task() {
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

	public Date getDueDate() {
		return this.dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Integer getEstimatePoint() {
		return this.estimatePoint;
	}

	public void setEstimatePoint(Integer estimatePoint) {
		this.estimatePoint = estimatePoint;
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

	public Project getProject() {
		return this.project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public ProjectTasktype getProjectTasktype() {
		return this.projectTasktype;
	}

	public void setProjectTasktype(ProjectTasktype projectTasktype) {
		this.projectTasktype = projectTasktype;
	}

	public TaskType getTaskType() {
		return this.taskType;
	}

	public void setTaskType(TaskType taskType) {
		this.taskType = taskType;
	}

}