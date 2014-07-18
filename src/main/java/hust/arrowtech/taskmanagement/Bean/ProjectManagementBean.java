package hust.arrowtech.taskmanagement.Bean;

import hust.arrowtech.taskmanagement.entity.Milestone;
import hust.arrowtech.taskmanagement.entity.Project;
import hust.arrowtech.taskmanagement.entity.ProjectSkill;
import hust.arrowtech.taskmanagement.entity.ProjectSkillPK;
import hust.arrowtech.taskmanagement.entity.ProjectTasktype;
import hust.arrowtech.taskmanagement.entity.ProjectTasktypePK;
import hust.arrowtech.taskmanagement.entity.Skill;
import hust.arrowtech.taskmanagement.entity.SkillCategory;
import hust.arrowtech.taskmanagement.entity.Task;
import hust.arrowtech.taskmanagement.entity.TaskType;
import hust.arrowtech.taskmanagement.entity.User;
import hust.arrowtech.taskmanagement.entity.UserSkill;
import hust.arrowtech.taskmanagement.service.CategoryController;
import hust.arrowtech.taskmanagement.service.MilestoneController;
import hust.arrowtech.taskmanagement.service.ProjectController;
import hust.arrowtech.taskmanagement.service.ProjectSkillController;
import hust.arrowtech.taskmanagement.service.ProjectTasktypeController;
import hust.arrowtech.taskmanagement.service.TaskController;
import hust.arrowtech.taskmanagement.service.TasktypeController;
import hust.arrowtech.taskmanagement.service.UserController;
import hust.arrowtech.taskmanagement.util.Page;
import hust.arrowtech.taskmanagement.util.Topic;
import hust.arrowtech.taskmanagement.util.Utils;

import java.io.Serializable;
import java.security.spec.PSSParameterSpec;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;

@Named
@ViewScoped
public class ProjectManagementBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2406018016768670150L;

	private List<Project> projects;
	private List<SkillCategory> categories;
	private List<User> users;
	private List<Integer> index;
	// Add, edit
	private Project project;
	private TaskType tasktype;
	private Task task;
	private User user;
	private Milestone milestone;
	private ProjectSkill projectSkill;

	private boolean addProjectDlgShow;
	private boolean assignUserDlgShow;
	private boolean addSkillDlgShow;
	private boolean addMilestoneDlgShow;
	private boolean addTasktypeDlgShow;
	private boolean addTaskDlgShow;
	private boolean editProjectDlgShow;

	private boolean isValidForm;
	private String path;
	private String username;

	@Inject
	ProjectController prController;
	@Inject
	UserController uController;
	@Inject
	CategoryController cController;
	@Inject
	ProjectSkillController psController;
	@Inject
	MilestoneController mController;
	@Inject
	TasktypeController ttController;
	@Inject
	TaskController tController;
	@Inject
	ProjectTasktypeController ptController;
	@Inject
	IndexBean indexBean;

	public ProjectManagementBean() {

	}

	/***********************************************************************/
	/***********************************************************************/
	/* START GETTER AND SETTER METHOD */

	public List<Project> getProjects() {
		if (projects == null) {
			projects = prController.getAllProject();
		}

		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	public List<SkillCategory> getCategories() {
		if (categories == null) {
			categories = cController.getAllCategories();
		}
		return categories;
	}

	public void setCategories(List<SkillCategory> categories) {
		this.categories = categories;
	}

	public List<User> getUsers() {
		if (users == null) {
			users = uController.getAllUser();

			for (User user : project.getUsers()) {

				users.remove(user);
			}
		}

		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public TaskType getTasktype() {
		return tasktype;
	}

	public void setTasktype(TaskType tasktype) {
		this.tasktype = tasktype;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public Milestone getMilestone() {
		return milestone;
	}

	public void setMilestone(Milestone milestone) {
		this.milestone = milestone;
	}

	public ProjectSkill getProjectSkill() {
		return projectSkill;
	}

	public void setProjectSkill(ProjectSkill projectSkill) {
		this.projectSkill = projectSkill;
	}

	public boolean isValidForm() {
		return isValidForm;
	}

	public void setValidForm(boolean isValidForm) {
		this.isValidForm = isValidForm;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public boolean isAddProjectDlgShow() {
		return addProjectDlgShow;
	}

	public void setAddProjectDlgShow(boolean addProjectDlgShow) {
		this.addProjectDlgShow = addProjectDlgShow;
	}

	public boolean isAssignUserDlgShow() {
		return assignUserDlgShow;
	}

	public void setAssignUserDlgShow(boolean assignUserDlgShow) {
		this.assignUserDlgShow = assignUserDlgShow;
	}

	public boolean isAddSkillDlgShow() {
		return addSkillDlgShow;
	}

	public void setAddSkillDlgShow(boolean addSkillDlgShow) {
		this.addSkillDlgShow = addSkillDlgShow;
	}

	public boolean isAddMilestoneDlgShow() {
		return addMilestoneDlgShow;
	}

	public void setAddMilestoneDlgShow(boolean addMilestoneDlgShow) {
		this.addMilestoneDlgShow = addMilestoneDlgShow;
	}

	public boolean isAddTasktypeDlgShow() {
		return addTasktypeDlgShow;
	}

	public void setAddTasktypeDlgShow(boolean addTasktypeDlgShow) {
		this.addTasktypeDlgShow = addTasktypeDlgShow;
	}

	public boolean isAddTaskDlgShow() {
		return addTaskDlgShow;
	}

	public void setAddTaskDlgShow(boolean addTaskDlgShow) {
		this.addTaskDlgShow = addTaskDlgShow;
	}

	public boolean isEditProjectDlgShow() {
		return editProjectDlgShow;
	}

	public void setEditProjectDlgShow(boolean editProjectDlgShow) {
		this.editProjectDlgShow = editProjectDlgShow;
	}

	public List<Integer> getIndex() {
		if (index == null) {
			int[] array = prController.getUserPriorityShow(project, getUsers());

			index = new ArrayList<Integer>();
			for (int i : array) {
				index.add(0, i);
			}
		}

		return index;
	}

	public void setIndex(List<Integer> index) {
		this.index = index;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	/* END GETTER AND SETTER METHOD */
	/***********************************************************************/
	/***********************************************************************/

	/**
	 * Do when constructed
	 */
	@PostConstruct
	public void postConstruct() {
		this.project = new Project();
		this.user = new User();
		this.tasktype = new TaskType();
		this.task = new Task();
		this.task.setTaskType(new TaskType());
		this.milestone = new Milestone();
		this.projectSkill = new ProjectSkill();
		this.projectSkill.setId(new ProjectSkillPK());

		this.addProjectDlgShow = false;
		this.assignUserDlgShow = false;
		this.addSkillDlgShow = false;
		this.addMilestoneDlgShow = false;
		this.addTasktypeDlgShow = false;
		this.addTaskDlgShow = false;
		this.editProjectDlgShow = false;

		this.path = Page.PROJECT_BASIC_INFO;
	}

	/***********************************************************************/
	/***********************************************************************/
	/* Event on click */

	/****** ADD PROJECT FUNCTION */
	/**
	 * on add project button click
	 */
	public void onAddProjectBtnClick() {
		this.addProjectDlgShow = true;
	}

	/**
	 * On AddProjectDialog's Add Button Click
	 */
	public void onAddProjectDlgAddBtnClick() {
		if (checkAddProjectForm()) {
			project = prController.add(project);
			Utils.addMessage("Added new project: \"" + project.getName() + "\"");

			this.addProjectDlgShow = false;
			reinit();
		}
	}

	/**
	 * Check form is inputed
	 * 
	 * @return
	 */
	private boolean checkAddProjectForm() {
		// Check name
		if (project.getName().equals("")) {
			Utils.addMessage("Please input name field!");
			return false;
		}

		// Check description
		if (project.getDescription().equals("")) {
			Utils.addMessage("Please input description field!");
			return false;
		}

		// Check customer
		if (project.getCustomer().equals("")) {
			Utils.addMessage("Please input customer field!");
			return false;
		}

		// Check start date
		if (project.getStartDate() == null) {
			Utils.addMessage("Please input start date field!");
			return false;
		}

		// Check expected end date
		if (project.getExpectedEndDate() == null) {
			Utils.addMessage("Please input expected end date field!");
			return false;
		}

		// Compare start date and end date
		if (project.getExpectedEndDate().compareTo(project.getStartDate()) < 0) {
			Utils.addMessage("Start date field must be before expected end date field!");
			return false;
		}

		return true;
	}

	/**
	 * On AddProjectDialog's Cancel button click
	 */
	public void onAddProjectDlgCancelBtnClick() {
		this.addProjectDlgShow = false;
	}

	/****** END ADD PROJECT FUNCTION */
	/*********************************/

	/****** EDIT PROJECT FUNCTION */
	/**
	 * On edit link click
	 */
	public void onEditLinkClick() {
		this.editProjectDlgShow = true;
	}

	/**
	 * on edit project dialog's save button click
	 */
	public void onEditProjectDlgSaveBtnClick() {
		if (checkAddProjectForm()) {
			// Check form ->true
			project = prController.save(project);

			Utils.addMessage("Save");
			this.editProjectDlgShow = false;
		}
	}

	/**
	 * on edit project dialog's cancel button click
	 */
	public void onEditProjectDlgCancelBtnClick() {
		this.editProjectDlgShow = false;
	}

	/****** END EDIT PROJECT FUNCTION */
	/**********************************/

	/***** ASSIGN USER TO PROJECT FUNCTION */
	/**
	 * On assign user button click
	 */
	public void onAssignUserBtnClick() {
		this.assignUserDlgShow = true;
	}

	/**
	 * 
	 * @param user
	 */
	public void onAssignUserLinkClick(User user) {
		uController.addProject(user, project);
		project.addUser(user);

		Utils.addMessage("Added user \"" + user.getUsername()
				+ "\" to project's user list!");
	}

	/**
	 * On assignUserDialog's add button click
	 */
	public void onAssignUserDlgAddBtnClick() {
		// Click vao button assign user
		// Check form
		if (checkAssignUserForm()) {
			// check username is existed
			user = uController.find(username);
			if (user == null) {
				Utils.addMessage("User \"" + username + "\" is not existed!");
				return;
			}

			// Check if user already in project's user list
			if (prController.isProjectContainUser(project, user)) {
				Utils.addMessage("User is already in this project's user list!");
				return;
			}

			// else -> add to project's user list
			uController.addProject(user, project);
			project.addUser(user);

			Utils.addMessage("Added user \"" + user.getUsername()
					+ "\" to project's user list!");
			this.assignUserDlgShow = false;
		}
	}

	/**
	 * on assignUserDialog's cancel button click
	 */
	public void onAssignUserDlgCancelBtnClick() {
		this.assignUserDlgShow = false;
	}

	/**
	 * Check assign user form in assign user dialog
	 * 
	 * @return
	 */
	public boolean checkAssignUserForm() {
		if (username.equals("")) {
			Utils.addMessage("Please input username field!");
			return false;
		}

		return true;
	}

	/****** END ASSIGN USER TO PROJECT FUNCTION */
	/********************************************/

	/****** ADD SKILL TO PROJECT FUNCTION */
	/**
	 * On addSkill button click
	 */
	public void onAddSkillBtnClick() {
		System.out.println("on Add skill btn click");
		for (ProjectSkill ps : project.getProjectSkills()) {
			System.out.println("Skill id: " + ps.getSkill().getId() + "-"
					+ ps.getSkill().getName());
		}

		this.addSkillDlgShow = true;
	}

	/**
	 * on addSkillDialog's add button click
	 */
	public void onAddSkillDlgAddBtnClick() {
		ProjectSkill ps = psController.find(project.getId(), projectSkill
				.getId().getSkillId());

		if (ps != null) {
			// if project already had skillId
			Utils.addMessage("Project already had skill \""
					+ ps.getSkill().getName() + "\"");
			return;
		}

		// else -> add skill to project's skill set
		projectSkill.getId().setProjectId(project.getId());
		projectSkill = psController.add(projectSkill);

		Utils.addMessage("Added skill to project \"" + project.getName()
				+ "\" skills set!");
		this.addSkillDlgShow = false;
	}

	/**
	 * on addSkillDialog's cancel button click
	 */
	public void onAddSkillDlgCancelBtnClick() {
		this.addSkillDlgShow = false;
	}

	/****** END ADD SKILL TO PROJECT FUNCTION */
	/******************************************/

	/****** ADD MILESTONE OF PROJECT FUNCTION */
	/**
	 * On add milestone button click
	 */
	public void onAddMilestoneBtnClick() {
		this.addMilestoneDlgShow = true;
	}

	/**
	 * On addMilestoneDialog's add button click
	 */
	public void onAddMilestoneDlgAddBtnClick() {
		// check form is true?
		if (checkAddMilestoneForm()) {
			// set project for milestone
			milestone.setProject(project);
			// add milestone to database
			mController.add(milestone);

			// project add milestone
			project = prController.addMilestone(project, milestone);

			Utils.addMessage("Added milestone \"" + milestone.getName() + "\"");

			this.addMilestoneDlgShow = false;
			this.milestone = new Milestone();
		}
	}

	/**
	 * On addMilestoneDialog's cancel button click
	 */
	public void onAddMilestoneDlgCancelBtnClick() {
		this.addMilestoneDlgShow = false;
	}

	/**
	 * Check inputed add milestone form
	 * 
	 * @return
	 */
	public boolean checkAddMilestoneForm() {
		// Check name
		if (milestone.getName().equals("")) {
			Utils.addMessage("Please input milestone's name field!");
			return false;
		}

		// Check Date
		if (milestone.getDueDate() == null) {
			Utils.addMessage("Please input milestone's dueDate field!");
			return false;
		}

		return true;
	}

	/****** END ADD SKILL TO PROJECT FUNCTION */
	/******************************************/

	/****** END ADD TASK OF PROJECT FUNCTION */
	/**
	 * on add task button click
	 */
	public void onAddTaskBtnClick() {
		this.addTaskDlgShow = true;
	}

	/**
	 * on add task dialog's add button click
	 */
	public void onAddTaskDlgAddBtnClick() {
		// check inputed add task form
		if (checkAddTaskForm()) {
			// find tasktype
			// task.setTaskType(ttController.find(task.getTaskType().getId()));
			// task.setProject(project);

			ProjectTasktype pt = new ProjectTasktype(project.getId(), task
					.getTaskType().getId());
			System.out.println("pt-projectId: " + pt.getId().getProjectId());
			System.out.println("pt-tasktypeId: " + pt.getId().getTaskTypeId());

			task.setProjectTasktype(new ProjectTasktype(project.getId(), task
					.getTaskType().getId()));
			// add task to database
			tController.add(task);
			// add task to project
			prController.addTask(project, task);

			Utils.addMessage("Add new task \"" + task.getName()
					+ "\" to project");
			this.addTaskDlgShow = false;

			this.task = new Task();
			this.task.setTaskType(new TaskType());
		}
	}

	/**
	 * on add task dialog's cancel button click
	 */
	public void onAddTaskDlgCancelBtnClick() {
		this.addTaskDlgShow = false;
	}

	/**
	 * Check inputed add task form
	 * 
	 * @return
	 */
	public boolean checkAddTaskForm() {
		// Check name
		if (task.getName().equals("")) {
			Utils.addMessage("Please input task's name field!");
			return false;
		}

		// check description
		if (task.getDescription().equals("")) {
			Utils.addMessage("Please input task's description field!");
			return false;
		}

		// check estimate point
		if (task.getEstimatePoint() == null) {
			Utils.addMessage("Please input task's estimate point field!");
			return false;
		}

		// check start date
		if (task.getStartDate() == null) {
			Utils.addMessage("Please input task's start date field!");
			return false;
		}

		// check due date
		if (task.getDueDate() == null) {
			Utils.addMessage("Please input task's due date field!");
			return false;
		}

		// check start date is before due date
		if (task.getStartDate().compareTo(task.getDueDate()) > 0) {
			Utils.addMessage("Task's start date must be before task's due date!");
			return false;
		}
		return true;
	}

	/***** END ADD TASK OF PROJECT FUNCTION */
	/***************************************/

	/***** ADD TASKTYPE FUNCTION */

	/**
	 * On add tasktype button click
	 */
	public void onAddTasktypeBtnClick() {
		this.addTasktypeDlgShow = true;
	}

	/**
	 * On add tasktype dialog's add button click
	 */
	public void onAddTasktypeDlgAddBtnClick() {
		// Find tasktype by id
		tasktype = ttController.find(tasktype.getId());
		// Check project already had tasktype???
		if (ptController.find(new ProjectTasktypePK(project.getId(), tasktype
				.getId())) != null) {
			Utils.addMessage("Project already had task type \""
					+ tasktype.getName() + "\"");

			return;
		}

		// Add tasktype to project tasktypes list
		project.addTasktype(tasktype);
		ptController.add(project.getId(), tasktype.getId());

		// Announce
		Utils.addMessage("Added task type \"" + tasktype.getName() + "\"");

		this.addTasktypeDlgShow = false;
		this.tasktype = new TaskType();
	}

	/**
	 * On add tasktype dialog's cancel button click
	 */
	public void onAddTasktypeDlgCancelBtnClick() {
		this.addTasktypeDlgShow = false;
	}

	/***** ENDT ADD TASKTYPE FUCNTION */

	/**
	 * Do when select a row
	 * 
	 * @param event
	 */
	public void onRowSelect(SelectEvent event) {
		project = (Project) event.getObject();

		indexBean.setTopic(Topic.PROJECT_INFO);
		indexBean.setPath(Page.PROJECT_INFO);
	}

	/**
	 * 
	 */
	public void reinit() {
		this.project = new Project();
		this.projects = null;
	}

	/**
	 * 
	 * @param choose
	 * @param skillId
	 * @return
	 */
	public String viewSkillInfo(int choose, int skillId) {
		ProjectSkill ps = psController.find(project.getId(), skillId);

		if (ps == null)
			return null;

		switch (choose) {
		case 0: // sho level A
			if (ps.getLevel() == 0)
				return "X";
			else
				return null;

		case 1: // show level B
			if (ps.getLevel() == 1)
				return "X";
			else
				return null;

		case 2: // show level C
			if (ps.getLevel() == 3)
				return "X";
			else
				return null;

		case 3:
			return ps.getExperienceYear() + "";

		default:
			break;
		}

		return null;
	}

	/**
	 * 
	 * @param tag
	 * @return
	 */
	public List<String> addUserCompleteMethod(String tag) {
		return uController.getListUser(tag);
	}

	/**
	 * 
	 * @param event
	 */
	public void onMilestoneRowEdit(RowEditEvent event) {
		Milestone milestone = (Milestone) event.getObject();

		milestone = mController.update(milestone);
		Utils.addMessage("Saved!");
	}

	/**
	 * 
	 * @param event
	 */
	public void onTaskRowEdit(RowEditEvent event) {
		Task task = (Task) event.getObject();

		task = tController.update(task);
		Utils.addMessage("Saved!");
	}

	/**
	 * Remove the selected tasktype
	 */
	public void removeTasktype() {
		project.removeTasktype(tasktype);
		ptController.remove(project.getId(), tasktype.getId());

		Utils.addMessage("Remove tasktype \"" + tasktype.getName() + "\"");
	}

	/**
	 * Remove the selected milestone
	 */
	public void removeMilestone() {
		project.removeMilestone(milestone);
		mController.remove(milestone);

		Utils.addMessage("Remove milestone \"" + milestone.getName() + "\"");
	}

	/**
	 * 
	 */
	public void onAssignSuggestUserBtnClick(User user) {
		prController.addUser(project, user);

		Utils.addMessage("Added user \"" + user.getUsername()
				+ "\" to project's user list!");
	}

	/**
	 * 
	 * @param user
	 * @return
	 */
	public String getDisable(User user) {
		if (prController.isProjectContainUser(project, user)) {
			return "true";
		}

		return "false";
	}

	/**
	 * 
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public String showSqlDate(Date dateStr) throws ParseException {
		DateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");
		DateFormat inputFormat1 = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat inputFormat2 = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
		Date date = null;

		try {
		    date = inputFormat1.parse(dateStr.toString());
		} catch (ParseException ex) {
		}
		
		if (date == null) {
		    // Invalid date format
			date = inputFormat2.parse(dateStr.toString());
		}
		
		return outputFormat.format(date);
	}
	
	/**
	 * On export button click
	 */
	public void onExportBtnClick(){
		String fileName = project.getName() + "-user-skill.xls";
		String sheetName = "User - Skill";

//		Map<String, Object[]> data = new TreeMap<String, Object[]>();
//		int i = 0;
//		
//		data.put(i++ + "", new Object[]{Utils.GROUP_HEADER, project.getName()});
//		data.put(i++ + "", new Object[]{Utils.GROUP_HEADER, "Name", "Level A", "Level B", "Level C"});
//
//		for (SkillCategory category : categories) {
//			data.put(i++ + "",new Object[] {Utils.GROUP_HEADER, category.getName()});
//
//			for (Skill skill : category.getSkills()) {
//				ProjectSkill ps = psController.find(project.getId(), skill.getId());
//				Object[] objArray = new Object[5];
//				
//				objArray[0] = Utils.MEMBER;
//				objArray[1] = skill.getName();
//				
//			}
//		}
//		
//		Utils.writeToExcel(fileName, sheetName, data);
//		Utils.addMessage("Export to file!");
	}
}
