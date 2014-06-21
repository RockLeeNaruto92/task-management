package hust.arrowtech.taskmanagement.service;

import hust.arrowtech.taskmanagement.entity.User;
import hust.arrowtech.taskmanagement.util.EmCreator;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.Query;
import javax.transaction.Transactional;

@Named
@Transactional
public class UserController implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	EmCreator emCreator;
	
	public User find(final String username){
		return this.emCreator.getEm().find(User.class, username);
	}
	
	@SuppressWarnings("unchecked")
	public List<String> getList(final String tag){
		Query query = this.emCreator.getEm().createQuery("SELECT u.username FROM User u WHERE u.username LIKE :tag");
		
		query.setParameter("tag", tag + "%");
		query.setMaxResults(10);
		
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<String> getList(){
		Query query = this.emCreator.getEm().createQuery("SELECT u.username FROM User u");
		
		return query.getResultList();
	}
	
	public String addUser(final String username, final String fullname,
			final String position, final int gender, final Date doB,
			final Date hiredDate, final String manager) {
		User user = new User(username, doB, fullname, gender, hiredDate, position);
		user.setManager(find(manager));
		
		this.emCreator.getEm().persist(user);
		assert user != null;
		
		return username;
	}
}
