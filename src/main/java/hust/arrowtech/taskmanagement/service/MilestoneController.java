package hust.arrowtech.taskmanagement.service;

import hust.arrowtech.taskmanagement.entity.Milestone;
import hust.arrowtech.taskmanagement.util.EmCreator;

import java.io.Serializable;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

@Named
@Transactional
public class MilestoneController implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private static int milestoneId = 0;

	@Inject
	EmCreator emCreator;
	
	/**
	 * find the project by id
	 * @param id
	 * @return
	 */
	public Milestone find(int id){
		return this.emCreator.getEm().find(Milestone.class, id);
	}
	
	/**
	 * Add new milestone witt milestone object do not have id
	 * @param milestone
	 * @return
	 */
	public Milestone add(Milestone milestone){
		milestone.setId(milestoneId++);
		
		this.emCreator.getEm().persist(milestone);
		assert milestone != null;
		
		return milestone;
	}
	
	/**
	 * Remove milestone
	 * @param milestone
	 * @return
	 */
	public Milestone remove(Milestone milestone){
		milestone.setProject(null);
		milestone = this.emCreator.getEm().merge(milestone);
		this.emCreator.getEm().remove(milestone);
		
		return milestone;
	}
	
	/**
	 * Update existed milestone information
	 * @param milestone
	 * @return
	 */
	public Milestone update(Milestone milestone){
		this.emCreator.getEm().merge(milestone);
		
		return milestone;
	}
}
