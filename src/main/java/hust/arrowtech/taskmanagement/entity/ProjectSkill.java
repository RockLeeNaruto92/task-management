package hust.arrowtech.taskmanagement.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the project_skill database table.
 * 
 */
@Entity
@Table(name="project_skill")
@NamedQuery(name="ProjectSkill.findAll", query="SELECT p FROM ProjectSkill p")
public class ProjectSkill implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ProjectSkillPK id;

	@Column(name="experience_year")
	private Integer experienceYear;

	private Integer level;

	//bi-directional many-to-one association to Project
	@ManyToOne
	@JoinColumn(name = "project_id", referencedColumnName = "id", insertable = false, updatable = false)
	private Project project;

	//bi-directional many-to-one association to Skill
	@ManyToOne
	@JoinColumn(name = "skill_id", referencedColumnName = "id", insertable = false, updatable = false)
	private Skill skill;

	public ProjectSkill() {
	}

	public ProjectSkillPK getId() {
		return this.id;
	}

	public void setId(ProjectSkillPK id) {
		this.id = id;
	}

	public Integer getExperienceYear() {
		return this.experienceYear;
	}

	public void setExperienceYear(Integer experienceYear) {
		this.experienceYear = experienceYear;
	}

	public Integer getLevel() {
		return this.level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Project getProject() {
		return this.project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Skill getSkill() {
		return this.skill;
	}

	public void setSkill(Skill skill) {
		this.skill = skill;
	}

}