package hust.arrowtech.taskmanagement.service;

import hust.arrowtech.taskmanagement.entity.Project;
import hust.arrowtech.taskmanagement.entity.ProjectSkill;
import hust.arrowtech.taskmanagement.entity.ProjectSkillPK;
import hust.arrowtech.taskmanagement.entity.Skill;
import hust.arrowtech.taskmanagement.util.EmCreator;

import java.io.Serializable;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

@Named
@Transactional
public class ProjectSkillController implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Inject
	EmCreator emCreator;
	
	public ProjectSkillPK addProjectSkill(int projectId, int skillId, int level, int experienceYear){
		ProjectSkill projectSkill = new ProjectSkill(projectId, skillId, experienceYear, level);
		
		this.emCreator.getEm().persist(projectSkill);
		assert projectSkill != null;
		
		return projectSkill.getId();
	}
	
	public ProjectSkill find(ProjectSkillPK id){
		return this.emCreator.getEm().find(ProjectSkill.class, id);
	}
	
	public ProjectSkill find(Project project, Skill skill){
		return this.emCreator.getEm().find(ProjectSkill.class, new ProjectSkillPK(project.getId(), skill.getId()));
	}
	
	public void update(ProjectSkill ps, int experienceYear, int level){
		ps.setExperienceYear(experienceYear);
		ps.setLevel(level);
	}
}
