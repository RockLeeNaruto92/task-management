package hust.arrowtech.taskmanagement.Bean;

import hust.arrowtech.taskmanagement.entity.TaskType;
import hust.arrowtech.taskmanagement.service.TasktypeController;
import hust.arrowtech.taskmanagement.util.Utils;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class TasktypeManagementBean implements Serializable {
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 2406018016768670150L;
	
	
	private List<TaskType> taskTypes;
	private TaskType taskType;
	
	private boolean addTasktypeDlgShow;
	
	
	@Inject
	TasktypeController tController;
	
	public TasktypeManagementBean(){
	}
	
	/**************************************/
	/* START GETTER AND SETTER METHOD*/

	public List<TaskType> getTaskTypes() {
		if (taskTypes == null){
			taskTypes = tController.getAllTasktypes();
		}
		
		return taskTypes;
	}

	public void setTaskTypes(List<TaskType> taskTypes) {
		this.taskTypes = taskTypes;
	}

	public TaskType getTaskType() {
		return taskType;
	}

	public void setTaskType(TaskType taskType) {
		this.taskType = taskType;
	}

	public boolean isAddTasktypeDlgShow() {
		return addTasktypeDlgShow;
	}

	public void setAddTasktypeDlgShow(boolean addTasktypeDlgShow) {
		this.addTasktypeDlgShow = addTasktypeDlgShow;
	}

	/* END GETTER AND SETTER METHOD*/
	/**************************************/
	/**
	 * Do when constructed
	 */
	@PostConstruct
	public void postConstruct(){
		this.taskType = new TaskType();
		
		this.addTasktypeDlgShow = false;
	}
	
	/**
	 * On add new task type button click
	 */
	public void onAddNewTaskTypeBtnClick(){
		this.addTasktypeDlgShow = true;
	}
	
	/**
	 * On addTasktypeDialog's add button click
	 */
	public void onAddTasktypeDlgAddBtnClick(){
		// Check tasktype form is true?
		if (checkAddTasktypeForm()){
			taskType = tController.add(taskType);
			
			Utils.addMessage("Added new tasktype \"" + taskType.getName() +"\"");
			
			reinit();
			this.addTasktypeDlgShow = false;
		}
	}
	
	/**
	 * On add tasktype dialog's cancel button click
	 */
	public void onAddTasktypeDlgCancelBtnClick(){
		this.addTasktypeDlgShow = false;
	}
	
	/**
	 * Check inputed tasktype add form
	 * @return
	 */
	public boolean checkAddTasktypeForm(){
		// Check name
		if (taskType.getName().equals("")){
			Utils.addMessage("Please input tasktype's name field!");
			return false;
		}
		
		return true;
	}
	
	/**
	 * Reinit
	 */
	public void reinit(){
		this.taskType = new TaskType();
		this.taskTypes = null;
	}
}
