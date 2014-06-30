package hust.arrowtech.taskmanagement.entity;

import java.io.Serializable;

import javax.persistence.*;

/**
 * The primary key class for the user_skill database table.
 * 
 */
@Embeddable
public class UserSkillPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private String username;

	@Column(name="skill_id", insertable=false, updatable=false)
	private Integer skillId;

	public UserSkillPK() {
	}
	public UserSkillPK(String username, Integer skillId) {
		super();
		this.username = username;
		this.skillId = skillId;
	}
	public String getUsername() {
		return this.username;
	}
	public void setUsername(String username) {
		this.username = username;
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
		if (!(other instanceof UserSkillPK)) {
			return false;
		}
		UserSkillPK castOther = (UserSkillPK)other;
		return 
			this.username.equals(castOther.username)
			&& this.skillId.equals(castOther.skillId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.username.hashCode();
		hash = hash * prime + this.skillId.hashCode();
		
		return hash;
	}
}