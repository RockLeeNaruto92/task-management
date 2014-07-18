package hust.arrowtech.taskmanagement.Bean;

import hust.arrowtech.taskmanagement.entity.Skill;
import hust.arrowtech.taskmanagement.entity.SkillCategory;
import hust.arrowtech.taskmanagement.entity.User;
import hust.arrowtech.taskmanagement.entity.UserSkill;
import hust.arrowtech.taskmanagement.entity.UserSkillPK;
import hust.arrowtech.taskmanagement.service.CategoryController;
import hust.arrowtech.taskmanagement.service.UserController;
import hust.arrowtech.taskmanagement.service.UserSkillController;
import hust.arrowtech.taskmanagement.util.Page;
import hust.arrowtech.taskmanagement.util.Topic;
import hust.arrowtech.taskmanagement.util.Utils;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.TabChangeEvent;

@Named
@ViewScoped
public class UserManagementBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2406018016768670150L;
	
	private List<SkillCategory> categories;
	private List<User> users;
	private User user;
	private Skill skill;
	private UserSkill userSkill;
	
	
	private boolean addUserDlgShow;
	private boolean editUserDlgShow;
	private boolean addSkillDlgShow;
	private String path;
	private String managerUsername;
	
	
	@Inject
	UserController uController;
	@Inject
	CategoryController cController;
	@Inject
	UserSkillController usController;
	@Inject
	IndexBean indexBean;
	
	
	public UserManagementBean(){
	}
	
	/**************************************/
	/* START GETTER AND SETTER METHOD*/

	public List<User> getUsers() {
		if (users == null){
			users = uController.getAllUser();
		}
		
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<SkillCategory> getCategories() {
		if (categories == null){
			categories = cController.getAllCategories();
		}
		return categories;
	}

	public void setCategories(List<SkillCategory> categories) {
		this.categories = categories;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Skill getSkill() {
		return skill;
	}

	public void setSkill(Skill skill) {
		this.skill = skill;
	}

	public UserSkill getUserSkill() {
		return userSkill;
	}

	public void setUserSkill(UserSkill userSkill) {
		this.userSkill = userSkill;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getManagerUsername() {
		return managerUsername;
	}

	public void setManagerUsername(String managerUsername) {
		this.managerUsername = managerUsername;
	}

	public boolean isAddUserDlgShow() {
		return addUserDlgShow;
	}

	public void setAddUserDlgShow(boolean addUserDlgShow) {
		this.addUserDlgShow = addUserDlgShow;
	}

	public boolean isAddSkillDlgShow() {
		return addSkillDlgShow;
	}

	public void setAddSkillDlgShow(boolean addSkillDlgShow) {
		this.addSkillDlgShow = addSkillDlgShow;
	}

	public boolean isEditUserDlgShow() {
		return editUserDlgShow;
	}

	public void setEditUserDlgShow(boolean editUserDlgShow) {
		this.editUserDlgShow = editUserDlgShow;
	}

	/* END GETTER AND SETTER METHOD*/
	/**************************************/
	/**
	 * Do when constructed
	 */
	@PostConstruct
	public void postConstruct(){
		this.user = new User();
		this.userSkill = new UserSkill();
		this.userSkill.setId(new UserSkillPK());
		
		this.addUserDlgShow = false;
		this.addSkillDlgShow = false;
		this.editUserDlgShow = false;
		this.path = Page.USER_BASIC_INFO;
	}
	
	/**
	 * On Add user button click
	 */
	public void onAddUserBtnClick(){
		this.addUserDlgShow = true;
	}
	
	/**
	 * On AddUserDialog's Add button Click
	 */
	public void onAddUserDlgAddBtnClick(){
		// check form is true
		if (checkForm()){
			// check existed username
			if (uController.find(user.getUsername()) != null){
				Utils.addMessage("Username is already existed!");
				return;
			}
			
			// if username not existed
			// -> add 
			user = uController.add(user);
			Utils.addMessage("Added new user: \"" + user.getUsername() + "\"");
			this.addUserDlgShow = false;
			
			reinit();
		}
	}
	
	/**
	 * On AddUserDialog's Cancel button click
	 */
	public void onAddUserDlgCancelBtnClick(){
		this.addUserDlgShow = false;
	}
	
	/**
	 * On basic information link click
	 */
	public void onBasicInformationLinkClick(){
		this.path = Page.USER_BASIC_INFO;
	}
	
	/**
	 * On SKILL SET link click
	 */
	public void onSkillSetLinkClick(){
		this.path = Page.USER_SKILL_SET;
	}
	
	/**
	 * On Edit link click
	 */
	public void onEditLinkClick(){
		this.editUserDlgShow = true;
	}
	
	/**
	 * on edit user dialog's save button click
	 */
	public void onEditUserDlgSaveBtnClick(){
		if (checkForm()){
			user = uController.save(user);
			
			Utils.addMessage("Saved!");
			this.editUserDlgShow = false;
		}
	}
	
	/**
	 * on edit user dialog's cancel button click
	 */
	public void onEditUserDlgCancelBtnClick(){
		this.editUserDlgShow = false;
	}
	
	/**
	 * on add skill btn click
	 */
	public void onAddSkillBtnClick(){
		System.out.println("Usermanagement: onAddSkillBtnclick");
		for (UserSkill us : user.getUserSkills()) {
			System.out.println("skill.Id : " + us.getSkill().getId() + "-" + us.getSkill().getName());
		}
		
		this.addSkillDlgShow = true;
	}
	
	/**
	 * On AddSkillDialog's Add button click
	 */
	public void onAddSkillDlgAddBtnClick(){
		// Check user already had skill?
		UserSkill us = usController.find(user.getUsername(), userSkill.getId().getSkillId());
		
		if (us != null){
			// user already had skill
			Utils.addMessage("User \"" + user.getUsername()
					+ "\" already had skill \""
					+ us.getSkill().getName() + "\"");
			return;
		}
		
		// else
		userSkill.getId().setUsername(user.getUsername());
		userSkill = usController.add(userSkill);
		Utils.addMessage("Added skill to user \"" + user.getUsername() + "\" skills set!");
		
		this.addSkillDlgShow = false;
	}
	
	/**
	 * On AddSkillDialog's cancel button click
	 */
	public void onAddSkillDlgCancelBtnClick(){
		this.addSkillDlgShow = false;
	}
	
	/**
	 * On Edit Project Form's Save button Click
	 */
	public void onEditFormSaveBtnClick(){
		if (checkForm()){
			if (!managerUsername.equals("")){
				if (managerUsername.equals(user.getUsername())){
					Utils.addMessage("Manager must different with user!");
					return;
				}
				
				User manager = uController.find(managerUsername);
				
				if (manager == null){
					Utils.addMessage("User \"" + managerUsername +"\" is not existed!");
					return;
				}
				
				user.setManager(uController.find(managerUsername));
			}
			
			user = uController.save(user);
			
			Utils.addMessage("Save");
			this.path = Page.USER_BASIC_INFO;
		}
	}
	
	/**
	 * Check inputed form when click add button in add user dialog
	 * @return
	 */
	public boolean checkForm(){
		// Check username
		if (user.getUsername().equals("")){
			Utils.addMessage("Please input name field!");
			return false;
		}
		
		// Check fullname
		if (user.getFullname().equals("")){
			Utils.addMessage("Please input full name field!");
			return false;
		}
		
		// Check date of birth
		if (user.getDoB() == null){
			Utils.addMessage("Please input date of birth field!");
			return false;
		}
		
		// Check hired date
		if (user.getHiredDate() == null){
			Utils.addMessage("Please input hired date field!");
			return false;
		}
		
		// Check position
		if (user.getPosition().equals("")){
			Utils.addMessage("Please input position field!");
			return false;
		}
		
		return true;
	}
	
	
	/**
	 * Do when select row
	 * @param event
	 */
	public void onRowSelect(SelectEvent event){
		user = (User)event.getObject();
		
		for (UserSkill us : user.getUserSkills()) {
			System.out.println("on row select: " + us.getSkill().getId());
		}
		
		indexBean.setPath(Page.USER_VIEW);
		indexBean.setTopic(Topic.USER_INFO);
	}
	
	/**
	 * Reinit
	 */
	public void reinit(){
		this.user = new User();
		this.users = null;
		
		this.addUserDlgShow = false;
		this.addSkillDlgShow = false;
	}
	
	/**
	 * Return String null or X or number of year of experience
	 * @param choose
	 * @param skillId
	 * @return
	 */
	public String viewSkillInfo(int choose, int skillId){
		UserSkill us = usController.find(user.getUsername(), skillId);
		
		if (us == null)
			return null;
		
		switch (choose) {
		case 0: // show level A
			if (us.getLevel() == 0) 
				return "X";
			else 
				return null;

		case 1: // show level B
			if (us.getLevel() == 1)
				return "X";
			else 
				return null;
			
		case 2: // show level C
			if (us.getLevel() == 2)
				return "X";
			else
				return null;
			
		case 3:
			return us.getYearOfExperience() + "";
			
		default:
			break;
		}
		
		return null;
	}
	
	/**
	 * Get suggest list user when onChange
	 * @param tag
	 * @return
	 */
	public List<String> getSuggestlistUserMethod(String tag){
		return uController.getListUser(tag);
	}
	
	/**
	 * 
	 * @param event
	 */
	public void onTabChange(TabChangeEvent event){
		String tabTitle = event.getTab().getTitle();
		
		if (tabTitle.equals("Basic information")){
			onBasicInformationLinkClick();
		}else {
			onSkillSetLinkClick();
		}
	}
	
	/**
	 * on export button click
	 */
	public void onExportBtnClick(){
		String fileName = user.getUsername() + "-skills.xls";
		String sheetName = "Skills set";

		Map<String, Object[]> data = new TreeMap<String, Object[]>();
		int i = 0;
		
		data.put(i++ + "", new Object[]{Utils.GROUP_HEADER, user.getUsername()});
		data.put(i++ + "", new Object[]{Utils.GROUP_HEADER, "Name", "Level A", "Level B", "Level C", "Experience year"});

		for (SkillCategory category : categories) {
			data.put(i++ + "",new Object[] {Utils.GROUP_HEADER, category.getName()});

			for (Skill skill : category.getSkills()) {
				UserSkill us = usController.find(user.getUsername(), skill.getId());
				Object[] objArray = new Object[6];
				
				objArray[0] = Utils.MEMBER;
				objArray[1] = skill.getName();
				
				if (us != null){
					objArray[2] = (us.getLevel() == 0) ? "X" : "";
					objArray[3] = (us.getLevel() == 1) ? "X" : "";
					objArray[4] = (us.getLevel() == 2) ? "X" : "";
					objArray[5] = us.getYearOfExperience().toString(); 
				}
				
				data.put(i++ + "", objArray);
				
			}
		}
		
		Utils.writeToExcel(fileName, sheetName, data);
		Utils.addMessage("Export to file!");
	}
}
