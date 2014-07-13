package hust.arrowtech.taskmanagement.entity;

import java.io.Serializable;
import javax.persistence.*;


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

	public ProjectTasktype() {
	}

	public ProjectTasktypePK getId() {
		return this.id;
	}

	public void setId(ProjectTasktypePK id) {
		this.id = id;
	}

}