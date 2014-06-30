package hust.arrowtech.taskmanagement.service;

import hust.arrowtech.taskmanagement.entity.Skill;
import hust.arrowtech.taskmanagement.entity.SkillCategory;
import hust.arrowtech.taskmanagement.util.EmCreator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.Query;
import javax.transaction.Transactional;

@Named
@Transactional
public class CategoryController implements Serializable {
	private static final long serialVersionUID = 1L;

	private static int categoryId = 0;
	private static int skillId = 0;

	@Inject
	EmCreator emCreator;

	/**
	 * find category by id
	 * 
	 * @param id
	 * @return
	 */
	public SkillCategory find(int id) {
		return this.emCreator.getEm().find(SkillCategory.class, id);
	}

	/**
	 * Get all categories in database
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<SkillCategory> getAllCategories() {
		String queryStr = "SELECT c FROM SkillCategory c";
		Query query = this.emCreator.getEm().createQuery(queryStr);

		return query.getResultList();
	}

	/**
	 * Add new skill category to database
	 * 
	 * @param category
	 * @return
	 */
	public SkillCategory add(SkillCategory category) {
		category.setId(categoryId++);

		this.emCreator.getEm().persist(category);
		assert category != null;

		return category;
	}

	/**
	 * Add new skill to category
	 * @param category
	 * @param skill
	 * @return
	 */
	public SkillCategory addSkill(SkillCategory category, Skill skill){
		
		skill.setId(skillId++);
		this.emCreator.getEm().persist(skill);
		assert skill != null;
		
		category.addSkill(skill);
		this.emCreator.getEm().merge(category);
		
		return category;
	}
	
	/**
	 * 
	 * @return
	 */
	public List<SelectItem> getSkillList(){
		List<SelectItem> skills = new ArrayList<SelectItem>();
		List<SkillCategory> categories = this.getAllCategories();
		
		for (SkillCategory category : categories) {
			SelectItemGroup group = new SelectItemGroup(category.getName());
			SelectItem[] itemArray = new SelectItem[category.getSkills().size()];
			int count = 0;
			
			for (Skill skill : category.getSkills()) {
				itemArray[count++] = new SelectItem(skill.getId(), skill.getName());
			}
			
			group.setSelectItems(itemArray);
			
			skills.add(group);
		}
		
		return skills;
		
	}
}
