package hust.arrowtech.taskmanagement.entity;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the skill_category database table.
 * 
 */
@Entity
@Table(name="skill_category")
@NamedQuery(name="SkillCategory.findAll", query="SELECT s FROM SkillCategory s")
public class SkillCategory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	private String description;

	private String name;

	private Boolean status;

	//bi-directional many-to-one association to Skill
	@OneToMany(mappedBy="skillCategory", fetch=FetchType.EAGER)
	private List<Skill> skills;

	public SkillCategory() {
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

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getStatus() {
		return this.status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public List<Skill> getSkills() {
		return this.skills;
	}

	public void setSkills(List<Skill> skills) {
		this.skills = skills;
	}

	public Skill addSkill(Skill skill) {
		getSkills().add(skill);
		skill.setSkillCategory(this);

		return skill;
	}

	public Skill removeSkill(Skill skill) {
		getSkills().remove(skill);
		skill.setSkillCategory(null);

		return skill;
	}

}