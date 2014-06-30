package hust.arrowtech.taskmanagement.service;

import hust.arrowtech.taskmanagement.entity.Project;
import hust.arrowtech.taskmanagement.entity.TaskType;
import hust.arrowtech.taskmanagement.util.EmCreator;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.Query;
import javax.transaction.Transactional;

@Named
@Transactional
public class TasktypeController implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private static int taskTypeId = 0;

	@Inject
	EmCreator emCreator;
	
	/**
	 * Find tasktype by id
	 * @param id
	 * @return
	 */
	public TaskType find(int id){
		return this.emCreator.getEm().find(TaskType.class, id);
	}
	
	/**
	 * Get list of tasktype from database
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<TaskType> getAllTasktypes(){
		String queryStr = "SELECT t FROM TaskType t";
		Query query = this.emCreator.getEm().createQuery(queryStr);
		
		return query.getResultList();
	}
	
	/**
	 * Add new tasktype object that no have id to database
	 * @param taskType
	 * @return
	 */
	public TaskType add(TaskType taskType){
		taskType.setId(taskTypeId++);
		
		this.emCreator.getEm().persist(taskType);
		assert taskType != null;
		
		return taskType;
	}
	
	/**
	 * Get hashmap for select tasktype
	 * @return
	 */
	public HashMap<String, Integer> getHashMapTasktype(){
		HashMap<String, Integer> hashmap = new HashMap<String, Integer>();
		List<TaskType> tasktypes = this.getAllTasktypes();
		
		for (TaskType taskType : tasktypes) {
			hashmap.put(taskType.getName(), taskType.getId());
		}
		
		return hashmap;
	}
	
	/**
	 * Add project to tasktype project list
	 * @param tasktype
	 * @param project
	 * @return
	 */
	public TaskType addProject(TaskType tasktype, Project project){
		tasktype.addProject(project);
		
		this.emCreator.getEm().merge(tasktype);
		return tasktype;
	}
}
