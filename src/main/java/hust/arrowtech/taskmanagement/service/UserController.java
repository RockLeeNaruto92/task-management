package hust.arrowtech.taskmanagement.service;

import hust.arrowtech.taskmanagement.entity.User;
import hust.arrowtech.taskmanagement.entity.UserSkill;
import hust.arrowtech.taskmanagement.util.EmCreator;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.Query;
import javax.transaction.Transactional;

@Named
@Transactional
public class UserController implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Inject
	EmCreator emCreator;
	
	/**
	 * Find user by username
	 * @param username
	 * @return
	 */
	public User find(String username){
		return this.emCreator.getEm().find(User.class, username);
	}
	
	/**
	 * Get all user in database
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<User> getAllUser(){
		String queryStr = "SELECT u FROM User u";
		Query query = this.emCreator.getEm().createQuery(queryStr);
		
		return query.getResultList();
	}
	
	/**
	 * Add a new user to database
	 * @param user
	 * @return
	 */
	public User add(User user){
		
		this.emCreator.getEm().persist(user);
		assert user != null;
		
		return user;
	}
	
	/**
	 * Save user information
	 * @param user
	 * @return
	 */
	public User save(User user){
		this.emCreator.getEm().merge(user);
		
		return user;
	}
	
	/**
	 * Check user has skill that have skillId?
	 * @param user
	 * @param skillId
	 * @return
	 */
	public boolean isUserHasSkill(User user, int skillId){
		for (UserSkill us : user.getUserSkills()) {
			if (us.getSkill().getId() == skillId)
				return true;
		}
		
		return false;
	}
	
	/**
	 * Get all username begin by tag
	 * @param tag
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<String> getListUser(String tag){
		System.out.println("Tag:" + tag);
		
		String queryStr = "SELECT u.username FROM User u WHERE u.username LIKE :tag";
		Query query = this.emCreator.getEm().createQuery(queryStr);
		
		query.setParameter("tag", tag + "%");
		
		return query.getResultList();
		
	}
}
