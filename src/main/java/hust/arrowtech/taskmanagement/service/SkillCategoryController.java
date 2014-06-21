package hust.arrowtech.taskmanagement.service;

import hust.arrowtech.taskmanagement.entity.SkillCategory;
import hust.arrowtech.taskmanagement.util.EmCreator;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.Query;
import javax.transaction.Transactional;

@Named
@Transactional
public class SkillCategoryController implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private static int skillCategoryId = 0; 

	@Inject
	EmCreator emCreator;
	
	/**
	 * 
	 * @param name
	 * @param description
	 * @param status
	 * @return
	 */
	public int addSkillCategory(String name, String description, boolean status){
		SkillCategory skillCategory = new SkillCategory(++skillCategoryId, description, name, status);
		
		this.emCreator.getEm().persist(skillCategory);
		assert skillCategory != null;
		
		return skillCategoryId;
	}
	
	/**
	 * 
	 * @param id
	 * @param name
	 * @param description
	 * @param status
	 */
	public void editSkillCategory(int id, String name, String description, boolean status){
		SkillCategory skillCategory = this.find(id);
		
		if (skillCategory != null){
			skillCategory.setName(name);
			skillCategory.setDescription(description);
			skillCategory.setStatus(status);
			
			this.emCreator.getEm().merge(skillCategory);
		}
	}
	
	/**
	 * 
	 * @param id
	 */
	public void removeSkill(int id){
		SkillCategory skillCategory = this.find(id);
		
		this.removeSkill(skillCategory);
	}
	
	/**
	 * 
	 * @param skill
	 */
	public void removeSkill(SkillCategory skill){
		if (skill != null){
			this.emCreator.getEm().remove(skill);
		}
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public SkillCategory find(int id){
		return this.emCreator.getEm().find(SkillCategory.class, id);
	}
	
	public SkillCategory find(String name){
		Query query = this.emCreator.getEm().createQuery("SELECT s FROM SkillCategory s WHERE s.name = :name");
		
		query.setParameter("name", name);
		
		return (SkillCategory) query.getSingleResult();
	}
	
	/**
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<SkillCategory> getSkillCategories(){
		Query query = this.emCreator.getEm().createQuery("SELECT s FROM SkillCategory s");
		
		return query.getResultList();
	}
	
	/**
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<String> getSkillCategoriesName(){
		Query query = this.emCreator.getEm().createQuery("SELECT s.name FROM SkillCategory s");
		
		return query.getResultList();
	}
}
