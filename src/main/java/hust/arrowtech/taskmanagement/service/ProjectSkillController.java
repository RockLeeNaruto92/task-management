package hust.arrowtech.taskmanagement.service;

import hust.arrowtech.taskmanagement.entity.ProjectSkill;
import hust.arrowtech.taskmanagement.entity.ProjectSkillPK;
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

	/**
	 * Find project skill by id
	 * @param projectId
	 * @param skillId
	 * @return
	 */
	public ProjectSkill find(int projectId, int skillId){
		ProjectSkillPK id = new ProjectSkillPK(projectId, skillId);
		
		return this.emCreator.getEm().find(ProjectSkill.class, id);
	}
	
	/**
	 * Add new project skill object
	 * @return
	 */
	public ProjectSkill add(ProjectSkill projectSkill){
		this.emCreator.getEm().persist(projectSkill);
		assert projectSkill != null;
		
		return projectSkill;
	}
	
	/**
	 * 
	 * @param pss
	 * @return
	 */
//	public List<ProjectSkill> sortBySkillId(List<ProjectSkill> pss){
//		
//	}
}
