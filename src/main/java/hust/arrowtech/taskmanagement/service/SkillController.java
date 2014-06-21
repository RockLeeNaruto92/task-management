package hust.arrowtech.taskmanagement.service;

import hust.arrowtech.taskmanagement.entity.Skill;
import hust.arrowtech.taskmanagement.entity.SkillCategory;
import hust.arrowtech.taskmanagement.util.EmCreator;

import java.io.Serializable;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

@Named
@Transactional
public class SkillController implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private static int skillId = 0; 

	@Inject
	EmCreator emCreator;
	
	/**
	 * 
	 * @param name
	 * @param description
	 * @param status
	 * @param category
	 */
	public Skill addSkill(String name, String description, boolean status, SkillCategory category){
		Skill skill = new Skill(++skillId, description, name, status);
		
		skill.setSkillCategory(category);
		
		this.emCreator.getEm().persist(skill);
		assert skill != null;
		
		return skill;
	}
	
	public Skill find(int id){
		return this.emCreator.getEm().find(Skill.class, id);
	}
}
