package hust.arrowtech.taskmanagement.service;

import hust.arrowtech.taskmanagement.entity.ProjectSkill;
import hust.arrowtech.taskmanagement.entity.Skill;
import hust.arrowtech.taskmanagement.entity.UserSkill;
import hust.arrowtech.taskmanagement.util.EmCreator;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

@Named
@Transactional
public class SkillController implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	EmCreator emCreator;
	
	/**
	 * Update skill  
	 * @param skill
	 * @return
	 */
	public Skill update(Skill skill){
		skill = this.emCreator.getEm().merge(skill);
		
		return skill;
	}
	
	/**
	 * return the number of same skill in both projectSkill list and userSkill list
	 * pss and uss must be sort by skill id 
	 * @param pss : projectSkill list
	 * @param uss : userSkill list
	 * @return
	 */
	public int getNumberOfSameSkill(List<ProjectSkill> pss, List<UserSkill> uss){
		int i = 0, j = 0, count = 0;
		
		// duyet tu dau den cuoi projectSkill list
		while (i < pss.size()){
			
			while (uss.get(j).getSkill().getId() != pss.get(i).getSkill().getId()){
				// vi tri j cua userSkill list den khi gap userSkill phu hop
				j++;
				if (j == uss.size()) // neu khong co userSkill nao phu hop -> return
					return count;
			}
			
			// tim duoc project skill phu hop voi user skill -> tang bien dem
			count ++; 
			// Tiep tuc tim kiem
			i++;
			j++;
		}
		
		return count;
	}
}
