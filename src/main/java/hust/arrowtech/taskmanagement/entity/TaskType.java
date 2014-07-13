package hust.arrowtech.taskmanagement.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the task_type database table.
 * 
 */
@Entity
@Table(name="task_type")
@NamedQuery(name="TaskType.findAll", query="SELECT t FROM TaskType t")
public class TaskType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	@Column(name="kind_of_task")
	private Integer kindOfTask;

	private String name;

	//bi-directional many-to-one association to Task
	@OneToMany(mappedBy="taskType", fetch=FetchType.EAGER)
	private List<Task> tasks;

	//bi-directional many-to-many association to Project
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(
		name="project_tasktype"
		, joinColumns={
			@JoinColumn(name="task_type_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="project_id")
			}
		)
	private List<Project> projects;

	public TaskType() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getKindOfTask() {
		return this.kindOfTask;
	}

	public void setKindOfTask(Integer kindOfTask) {
		this.kindOfTask = kindOfTask;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Task> getTasks() {
		return this.tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	public Task addTask(Task task) {
		getTasks().add(task);
		task.setTaskType(this);

		return task;
	}

	public Task removeTask(Task task) {
		getTasks().remove(task);
		task.setTaskType(null);

		return task;
	}

	public List<Project> getProjects() {
		return this.projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}
	
	public void addProject(Project project){
		this.projects.add(project);
	}
	
	public void removeProject(Project project){
		this.projects.add(project);
	}

}