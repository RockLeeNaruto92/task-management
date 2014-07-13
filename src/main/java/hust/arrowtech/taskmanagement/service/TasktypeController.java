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
	 * Find tasktype by name
	 * @param name
	 * @return
	 */
	public TaskType find(String name){
		String queryStr = "SELECT t FROM TaskType t WHERE t.name = :name";
		Query query = this.emCreator.getEm().createQuery(queryStr);
		
		query.setParameter("name", name);
		
		return (TaskType) query.getSingleResult();
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
	 * Remove tasktype from tasktypes list by name
	 * @param tasktypes
	 * @param tasktype
	 * @return
	 */
	public List<TaskType> removeTasktype(List<TaskType> tasktypes, TaskType tasktype){
		for (TaskType taskType : tasktypes) {
			if (taskType.getName().equals(tasktype.getName())){
				tasktypes.remove(taskType);
				
				return tasktypes;
			}
		}
		
		return tasktypes;
	}
	/**
	 * Get hashmap for select tasktype
	 * @return
	 */
	public HashMap<String, Integer> getHashMapTasktype(List<TaskType> tasktypes){
		HashMap<String, Integer> hashmap = new HashMap<String, Integer>();
		
		for (TaskType taskType : tasktypes) {
			hashmap.put(taskType.getName(), taskType.getId());
		}
		
		return hashmap;
	}
	
	public HashMap<String, Integer> getHashMapTaskType(){
		List<TaskType> tasktypes = this.getAllTasktypes();
		
		return getHashMapTasktype(tasktypes);
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
	
	/**
	 * 
	 * @param tasktype
	 * @param project
	 * @return
	 */
	public TaskType removeProject(TaskType tasktype, Project project){
		tasktype.removeProject(project);
		
		tasktype = this.emCreator.getEm().merge(tasktype);
		return tasktype;
	}
}
