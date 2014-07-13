package hust.arrowtech.taskmanagement.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the user database table.
 * 
 */
@Entity(name = "Users")
@NamedQuery(name="User.findAll", query="SELECT u FROM Users u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String username;

	@Temporal(TemporalType.DATE)
	@Column(name="\"doB\"")
	private Date doB;

	private String fullname;

	private Integer gender;

	@Temporal(TemporalType.DATE)
	@Column(name="\"hiredDate\"")
	private Date hiredDate;

	private String position;

	//bi-directional many-to-many association to Project
	@ManyToMany(mappedBy="users", fetch=FetchType.EAGER)
	private List<Project> projects;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="manager")
	private User manager;

	//bi-directional many-to-one association to User
	@OneToMany(mappedBy="manager", fetch=FetchType.EAGER)
	private List<User> staffs;

	//bi-directional many-to-one association to UserSkill
	@OneToMany(mappedBy="user", fetch=FetchType.EAGER)
	private List<UserSkill> userSkills;

	public User() {
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getDoB() {
		return this.doB;
	}

	public void setDoB(Date doB) {
		this.doB = doB;
	}

	public String getFullname() {
		return this.fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public Integer getGender() {
		return this.gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public Date getHiredDate() {
		return this.hiredDate;
	}

	public void setHiredDate(Date hiredDate) {
		this.hiredDate = hiredDate;
	}

	public String getPosition() {
		return this.position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public List<Project> getProjects() {
		return this.projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	public User getManager() {
		return this.manager;
	}

	public void setManager(User manager) {
		this.manager = manager;
	}

	public List<User> getStaffs() {
		return this.staffs;
	}

	public void setStaffs(List<User> staffs) {
		this.staffs = staffs;
	}

	public User addStaff(User staff) {
		getStaffs().add(staff);
		staff.setManager(this);

		return staff;
	}

	public User removeStaff(User staff) {
		getStaffs().remove(staff);
		staff.setManager(null);

		return staff;
	}

	public List<UserSkill> getUserSkills() {
		return this.userSkills;
	}

	public void setUserSkills(List<UserSkill> userSkills) {
		this.userSkills = userSkills;
	}

	public UserSkill addUserSkill(UserSkill userSkill) {
		getUserSkills().add(userSkill);
		userSkill.setUser(this);

		return userSkill;
	}

	public UserSkill removeUserSkill(UserSkill userSkill) {
		getUserSkills().remove(userSkill);
		userSkill.setUser(null);

		return userSkill;
	}

}