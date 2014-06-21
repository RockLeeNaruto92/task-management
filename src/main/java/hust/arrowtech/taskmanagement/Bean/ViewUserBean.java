package hust.arrowtech.taskmanagement.Bean;

import hust.arrowtech.taskmanagement.entity.User;
import hust.arrowtech.taskmanagement.service.UserController;
import hust.arrowtech.taskmanagement.util.Page;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class ViewUserBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	
	private String labelUsername;
	private String labelFullname;
	private String labelDoB;
	private String labelHiredDate;
	private String labelGender;
	private String labelPosition;
	private String labelManager;
	
	private String username;
	private String manager;
	private User user;
	
	@Inject
	UserController userController;
	@Inject
	IndexBean indexBean;
	@Inject
	ViewUserBean viewUserBean;
	@Inject
	AddUserBean addUserBean;

	public ViewUserBean() {
		// TODO Auto-generated constructor stub
	}

	public String getLabelUsername() {
		return labelUsername;
	}

	public void setLabelUsername(String labelUsername) {
		this.labelUsername = labelUsername;
	}

	public String getLabelFullname() {
		return labelFullname;
	}

	public void setLabelFullname(String labelFullname) {
		this.labelFullname = labelFullname;
	}

	public String getLabelDoB() {
		return labelDoB;
	}

	public void setLabelDoB(String labelDoB) {
		this.labelDoB = labelDoB;
	}

	public String getLabelHiredDate() {
		return labelHiredDate;
	}

	public void setLabelHiredDate(String labelHiredDate) {
		this.labelHiredDate = labelHiredDate;
	}

	public String getLabelGender() {
		return labelGender;
	}

	public void setLabelGender(String labelGender) {
		this.labelGender = labelGender;
	}

	public String getLabelPosition() {
		return labelPosition;
	}

	public void setLabelPosition(String labelPosition) {
		this.labelPosition = labelPosition;
	}

	public String getLabelManager() {
		return labelManager;
	}

	public void setLabelManager(String labelManager) {
		this.labelManager = labelManager;
	}

	public String getUsername() {
		setUsername(indexBean.getTempUser());
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public User getUser() {
		if (user == null){
			user = userController.find(getUsername());
		}
		
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public String getManager() {
		if (user.getManager() == null)
			return "null";
		return user.getManager().getUsername();
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public String getGender() {
		switch (user.getGender()) {
		case 0:
			return "Male";
		case 1:
			return "Female";
		case 2:
			return "Other";
		default:
			break;
		}
		
		return null;
	}
	
	@PostConstruct
	public void postConstruct() {
		this.labelUsername = "Username";
		this.labelFullname = "Fullname";
		this.labelDoB = "Date of birth";
		this.labelHiredDate = "Hired date";
		this.labelPosition = "Position";
		this.labelGender = "Gender";
		this.labelManager = "Manager";
		
	}
	
	public void onClick(int choose){
		switch(choose){
		case 0:
			indexBean.setPath(Page.VIEW_USER);
			break;
		case 1:
			System.out.println("viewUsername: " + username);
			break;
		default:
			break;
		}
	}
	
}
