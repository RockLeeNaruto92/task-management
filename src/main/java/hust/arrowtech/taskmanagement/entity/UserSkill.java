package hust.arrowtech.taskmanagement.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the user_skill database table.
 * 
 */
@Entity
@Table(name="user_skill")
@NamedQuery(name="UserSkill.findAll", query="SELECT u FROM UserSkill u")
public class UserSkill implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private UserSkillPK id;

	private Integer level;

	@Column(name="year_of_experience")
	private Integer yearOfExperience;

	//bi-directional many-to-one association to Skill
	@ManyToOne
	@JoinColumn(name = "skill_id", referencedColumnName = "id", insertable = false, updatable = false)
	private Skill skill;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="username", referencedColumnName = "username", insertable = false, updatable = false)
	private User user;

	public UserSkill() {
	}

	public UserSkillPK getId() {
		return this.id;
	}

	public void setId(UserSkillPK id) {
		this.id = id;
	}

	public Integer getLevel() {
		return this.level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getYearOfExperience() {
		return this.yearOfExperience;
	}

	public void setYearOfExperience(Integer yearOfExperience) {
		this.yearOfExperience = yearOfExperience;
	}

	public Skill getSkill() {
		return this.skill;
	}

	public void setSkill(Skill skill) {
		this.skill = skill;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}