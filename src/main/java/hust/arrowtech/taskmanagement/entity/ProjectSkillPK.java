package hust.arrowtech.taskmanagement.entity;

import java.io.Serializable;

import javax.persistence.*;

/**
 * The primary key class for the project_skill database table.
 * 
 */
@Embeddable
public class ProjectSkillPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="project_id", insertable=false, updatable=false)
	private Integer projectId;

	@Column(name="skill_id", insertable=false, updatable=false)
	private Integer skillId;

	public ProjectSkillPK() {
	}
	public ProjectSkillPK(Integer projectId, Integer skillId) {
		super();
		this.projectId = projectId;
		this.skillId = skillId;
	}
	public Integer getProjectId() {
		return this.projectId;
	}
	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}
	public Integer getSkillId() {
		return this.skillId;
	}
	public void setSkillId(Integer skillId) {
		this.skillId = skillId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ProjectSkillPK)) {
			return false;
		}
		ProjectSkillPK castOther = (ProjectSkillPK)other;
		return 
			this.projectId.equals(castOther.projectId)
			&& this.skillId.equals(castOther.skillId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.projectId.hashCode();
		hash = hash * prime + this.skillId.hashCode();
		
		return hash;
	}
}