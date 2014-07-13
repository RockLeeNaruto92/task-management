package hust.arrowtech.taskmanagement.entity;

import java.io.Serializable;

import javax.persistence.*;

/**
 * The primary key class for the project_tasktype database table.
 * 
 */
@Embeddable
public class ProjectTasktypePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="project_id", insertable=false, updatable=false)
	private Integer projectId;

	@Column(name="task_type_id", insertable=false, updatable=false)
	private Integer taskTypeId;

	public ProjectTasktypePK() {
	}
	public ProjectTasktypePK(Integer projectId, Integer taskTypeId) {
		super();
		this.projectId = projectId;
		this.taskTypeId = taskTypeId;
	}
	public Integer getProjectId() {
		return this.projectId;
	}
	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}
	public Integer getTaskTypeId() {
		return this.taskTypeId;
	}
	public void setTaskTypeId(Integer taskTypeId) {
		this.taskTypeId = taskTypeId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ProjectTasktypePK)) {
			return false;
		}
		ProjectTasktypePK castOther = (ProjectTasktypePK)other;
		return 
			this.projectId.equals(castOther.projectId)
			&& this.taskTypeId.equals(castOther.taskTypeId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.projectId.hashCode();
		hash = hash * prime + this.taskTypeId.hashCode();
		
		return hash;
	}
}