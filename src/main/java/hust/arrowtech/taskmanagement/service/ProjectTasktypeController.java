package hust.arrowtech.taskmanagement.service;

import hust.arrowtech.taskmanagement.entity.ProjectTasktype;
import hust.arrowtech.taskmanagement.entity.ProjectTasktypePK;
import hust.arrowtech.taskmanagement.util.EmCreator;

import java.io.Serializable;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

@Named
@Transactional
public class ProjectTasktypeController implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	EmCreator emCreator;
	
	public ProjectTasktype find(ProjectTasktypePK id){
		return this.emCreator.getEm().find(ProjectTasktype.class, id);
	}
	
	/**
	 * 
	 * @param projectId
	 * @param TasktypeId
	 */
	public void remove(int projectId, int tasktypeId){
		ProjectTasktypePK id = new ProjectTasktypePK(projectId, tasktypeId);
		ProjectTasktype tasktype = this.find(id);
		
		this.emCreator.getEm().remove(tasktype);
	}
	
	/**
	 * 
	 * @param projectId
	 * @param tasktypeId
	 */
	public ProjectTasktype add(int projectId, int tasktypeId){
		ProjectTasktype projectTasktype = new ProjectTasktype(projectId, tasktypeId);
		
		this.emCreator.getEm().persist(projectTasktype);
		assert projectTasktype != null;
		
		return projectTasktype;
	}
}
