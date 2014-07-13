package hust.arrowtech.taskmanagement.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the project_tasktype database table.
 * 
 */
@Entity
@Table(name="project_tasktype")
@NamedQuery(name="ProjectTasktype.findAll", query="SELECT p FROM ProjectTasktype p")
public class ProjectTasktype implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ProjectTasktypePK id;

	//bi-directional many-to-one association to Task
	@OneToMany(mappedBy="projectTasktype", fetch=FetchType.EAGER)
	private List<Task> tasks;

	public ProjectTasktype(int projectId, int taskTypeId) {
		this.id = new ProjectTasktypePK(projectId, taskTypeId);
	}
	
	public ProjectTasktype(){
	}

	public ProjectTasktypePK getId() {
		return this.id;
	}

	public void setId(ProjectTasktypePK id) {
		this.id = id;
	}

	public List<Task> getTasks() {
		return this.tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	public Task addTask(Task task) {
		getTasks().add(task);
		task.setProjectTasktype(this);

		return task;
	}

	public Task removeTask(Task task) {
		getTasks().remove(task);
		task.setProjectTasktype(null);

		return task;
	}

}