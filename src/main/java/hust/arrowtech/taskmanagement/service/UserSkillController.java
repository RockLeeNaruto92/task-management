package hust.arrowtech.taskmanagement.service;

import hust.arrowtech.taskmanagement.entity.UserSkill;
import hust.arrowtech.taskmanagement.entity.UserSkillPK;
import hust.arrowtech.taskmanagement.util.EmCreator;

import java.io.Serializable;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

@Named
@Transactional
public class UserSkillController implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Inject
	EmCreator emCreator;
	
	/**
	 * Find userskill by username and skillId
	 * @param username
	 * @param skillId
	 * @return
	 */
	public UserSkill find(String username, int skillId){
		UserSkillPK id = new UserSkillPK(username, skillId);
		
		return this.emCreator.getEm().find(UserSkill.class, id);
	}
	
	/**
	 * Add new user skill
	 * @param userSkill
	 * @return
	 */
	public UserSkill add(UserSkill userSkill){
		this.emCreator.getEm().persist(userSkill);
		assert userSkill != null;
		
		return userSkill;
	}
}
