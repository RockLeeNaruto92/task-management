package hust.arrowtech.taskmanagement.service;

import hust.arrowtech.taskmanagement.entity.Project;
import hust.arrowtech.taskmanagement.entity.Skill;
import hust.arrowtech.taskmanagement.entity.User;
import hust.arrowtech.taskmanagement.util.EmCreator;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

@Named
@Transactional
public class ProjectController implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private static int projectId = 0; 

	@Inject
	EmCreator emCreator;
	
	public int addProject(String customer, String description, String name, Date expectedEndDate, Date startDate){
		Project project = new Project(++projectId, customer, description, expectedEndDate, name, startDate);
		
		this.emCreator.getEm().persist(project);
		assert project != null;
		
		return projectId;
	}
	
	public Project find(int projectId){
		return this.emCreator.getEm().find(Project.class, projectId);
	}
	
	public void addUser(Project project, User user){
		project.addUser(user);
		
		this.emCreator.getEm().merge(project);
	}
	
	public void addSkill(Project project, Skill skill){
		project.addSkill(skill);
		
		this.emCreator.getEm().merge(project);
	}
	
	public boolean containUser(Project project, String username){
		List<User> users = project.getUsers();
		
		for (User user : users) {
			if (user.getUsername().equals(username)) 
				return true;
		}
		return false;
	}
	
	public boolean containeSkill(Project project, int skillId){
		for (Skill item : project.getSkills()) {
			if (skillId == item.getId())
				return true;
		}
		
		return false;
	}
	
	public void editProject(Project project){
		this.emCreator.getEm().merge(project);
	}

}
