package hust.arrowtech.taskmanagement.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * The persistent class for the milestone database table.
 * 
 */
@Entity
@NamedQuery(name = "Milestone.findAll", query = "SELECT m FROM Milestone m")
public class Milestone implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	@Temporal(TemporalType.DATE)
	@Column(name = "\"dueDate\"")
	private Date dueDate;

	private String name;

	@Column(name = "project_id", insertable = false, updatable = false)
	private Integer projectId;

	@Column(name = "version_number")
	private String versionNumber;

	// bi-directional many-to-one association to Project
	@ManyToOne
	private Project project;

	public Milestone() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDueDate() {
		return this.dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getProjectId() {
		return this.projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public String getVersionNumber() {
		return this.versionNumber;
	}

	public void setVersionNumber(String versionNumber) {
		this.versionNumber = versionNumber;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

}