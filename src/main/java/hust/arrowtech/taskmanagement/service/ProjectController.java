package hust.arrowtech.taskmanagement.service;

import hust.arrowtech.taskmanagement.entity.Milestone;
import hust.arrowtech.taskmanagement.entity.Project;
import hust.arrowtech.taskmanagement.entity.Task;
import hust.arrowtech.taskmanagement.entity.TaskType;
import hust.arrowtech.taskmanagement.entity.User;
import hust.arrowtech.taskmanagement.util.EmCreator;
import hust.arrowtech.taskmanagement.util.Utils;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.Query;
import javax.transaction.Transactional;

@Named
@Transactional
public class ProjectController implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private static int projectId = 0;

	@Inject
	EmCreator emCreator;
	
	/**
	 * find the project by id
	 * @param id
	 * @return
	 */
	public Project find(int id){
		return this.emCreator.getEm().find(Project.class, id);
	}
	
	/**
	 * Get all project in database
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Project> getAllProject(){
		String queryStr = "SELECT p FROM Project p";
		Query query = this.emCreator.getEm().createQuery(queryStr);
		
		return query.getResultList();
	}

	/**
	 * Add new project to database
	 * @param customer
	 * @param description
	 * @param expectedEndDate
	 * @param name
	 * @param startDate
	 * @return
	 */
	public Project add(String customer, String description,
			Date expectedEndDate, String name, Date startDate) {
		Project newProject = new Project(projectId++, customer, description,
				expectedEndDate, name, startDate);
		
		this.emCreator.getEm().persist(newProject);
		assert newProject != null;
		
		return newProject;
	}
	
	/**
	 * Add new project to database
	 * @param project
	 * @return
	 */
	public Project add(Project project){
		project.setId(projectId++);
		
		this.emCreator.getEm().persist(project);
		assert project != null;
		
		return project;
	}
	
	/**
	 * Assign user to project
	 * @param project
	 * @param user
	 * @return
	 */
	public Project addUser(Project project, User user){
		project.addUser(user);
		
		this.emCreator.getEm().merge(project);
		return project;
	}
	
	/**
	 * Save when edited project
	 * @param project
	 * @return
	 */
	public Project save(Project project){
		this.emCreator.getEm().merge(project);
		
		return project;
	}
	
	/**
	 * Check user is in project's user list
	 * @param project
	 * @param user
	 * @return
	 */
	public boolean isProjectContainUser(Project project, User user){
		for (User tempUser : project.getUsers()) {
			if (user.getUsername().equals(tempUser.getUsername()))
				return true;
		}
		
		return false;
	}
	
	/**
	 * Add new milestone to project 
	 * @param project
	 * @param milestone
	 * @return
	 */
	public Project addMilestone(Project project, Milestone milestone){
		project.addMilestone(milestone);
		
		this.emCreator.getEm().merge(project);
		return project;
	}
	
	/**
	 * 
	 * @param project
	 * @param tasktype
	 * @return
	 */
	public Project removeTasktype(Project project, TaskType tasktype){
		project.removeTasktype(tasktype);
		this.emCreator.getEm().merge(project);
		
		return project;
	}
	
	/**
	 * Check project had tasktype?
	 * @param project
	 * @param tasktype
	 * @return
	 */
	public boolean isProjectHadTasktype(Project project, TaskType tasktype){
		for (TaskType tp : project.getTaskTypes()) {
			if (tp.getId() == tasktype.getId()){
				System.out.println("tasktype-id: " + tasktype.getId());
				System.out.println("tasktype-name: " + tp.getName());
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Project add new tasktype
	 * @param project
	 * @param tasktype
	 * @return
	 */
	public Project addTasktype(Project project, TaskType tasktype){
		System.out.println("Project id: " + project.getId());
		System.out.println("Tasktype id: " + tasktype.getId());
		project.addTasktype(tasktype);
		this.emCreator.getEm().merge(project);
		
		return project;
	}
	
	
	/**
	 * Add task that not have id to project
	 * @param project
	 * @param task
	 * @return
	 */
	public Project addTask(Project project, Task task){
		project.addTask(task);
		
		this.emCreator.getEm().merge(project);
		return project;
	}
	
	/**
	 * get hash map 
	 * @param project
	 * @return
	 */
	public HashMap<String, TaskType> getHashMapTasktype(Project project){
		HashMap<String, TaskType> hashmap = new HashMap<String, TaskType>();
		
		for (TaskType tt : project.getTaskTypes()) {
			hashmap.put(tt.getName(), tt);
		}
		
		return hashmap;
	}
	
	/**
	 * 
	 * @param project
	 * @param users
	 * @return
	 */
	public int[] getUserPriorityShow(Project project, List<User> users){
		int[] sameSkillNumbers = new int[users.size()];
		int[] index = new int[users.size()];
		
		int i = 0;
		
		// get same skill for each user
		for (User user : users) {
			sameSkillNumbers[i] = Utils.getPoint(project, user);
			index[i] = i++;
		}
		
		// sort index
		for (i = 0; i < index.length; i++)
			for (int j = 0; j < index.length; j++){
				if (sameSkillNumbers[i] < sameSkillNumbers[j]){
					int temp = sameSkillNumbers[i];
					sameSkillNumbers[i] = sameSkillNumbers[j];
					sameSkillNumbers[j] = temp;
					
					temp = index[i];
					index[i] = index[j];
					index[j] = temp;
				}
			}
		
		return index;
	}
	
}
