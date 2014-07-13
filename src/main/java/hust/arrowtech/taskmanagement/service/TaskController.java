package hust.arrowtech.taskmanagement.service;

import hust.arrowtech.taskmanagement.entity.Project;
import hust.arrowtech.taskmanagement.entity.Task;
import hust.arrowtech.taskmanagement.util.EmCreator;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.Query;
import javax.transaction.Transactional;

@Named
@Transactional
public class TaskController implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private static int taskId = 0;

	@Inject
	EmCreator emCreator;
	
	/**
	 * Find task by id
	 * @param id
	 * @return
	 */
	public Task find(int id){
		return this.emCreator.getEm().find(Task.class, id);
	}
	
	/**
	 * Add new task to database that no have id
	 * @param task
	 * @return
	 */
	public Task add(Task task){
		task.setId(taskId++);
		
		this.emCreator.getEm().flush();
		this.emCreator.getEm().persist(task);
		assert task != null;
		
		return task;
	}

	/**
	 * Set project for task and update task in databawe
	 * @param task
	 * @param project
	 * @return
	 */
	public Task setProject(Task task, Project project){
		task.setProject(project);
		
		this.emCreator.getEm().merge(task);
		return task;
	}
	
	
	/**
	 * Get all task in database
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Task> getAllTask(){
		String queryStr = "SELECT t FROM Task t";
		Query query = this.emCreator.getEm().createQuery(queryStr);
		
		return query.getResultList();
	}
}
