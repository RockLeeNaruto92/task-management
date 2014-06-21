package hust.arrowtech.taskmanagement.Bean;

import hust.arrowtech.taskmanagement.entity.User;
import hust.arrowtech.taskmanagement.service.UserController;
import hust.arrowtech.taskmanagement.util.Utils;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class AddUserBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private static final String PAGE_VIEW_USER = "viewUser.xhtml";
	
	private String labelInputUsername;
	private String labelInputFullname;
	private String labelInputDoB;
	private String labelInputHiredDate;
	private String labelInputGender;
	private String labelInputPosition;
	private String labelInputManager;
	
	private String btnSubmit;
	private String genderMale;
	private String genderFemale;
	private String genderOther;
	
	private String username;
	private String fullname;
	private Date doB;
	private Date hiredDate;
	private int gender;
	private String position;
	private String manager;
	
	@Inject
	UserController userController;
	@Inject
	ViewUserBean viewUserBean;
	@Inject
	IndexBean indexBean;
	
	public AddUserBean() {
		// TODO Auto-generated constructor stub
	}

	public String getLabelInputUsername() {
		return labelInputUsername;
	}

	public void setLabelInputUsername(String labelInputUsername) {
		this.labelInputUsername = labelInputUsername;
	}

	public String getLabelInputFullname() {
		return labelInputFullname;
	}

	public void setLabelInputFullname(String labelInputFullname) {
		this.labelInputFullname = labelInputFullname;
	}

	public String getLabelInputDoB() {
		return labelInputDoB;
	}

	public void setLabelInputDoB(String labelInputDoB) {
		this.labelInputDoB = labelInputDoB;
	}

	public String getLabelInputHiredDate() {
		return labelInputHiredDate;
	}

	public void setLabelInputHiredDate(String labelInputHiredDate) {
		this.labelInputHiredDate = labelInputHiredDate;
	}

	public String getLabelInputGender() {
		return labelInputGender;
	}

	public void setLabelInputGender(String labelInputGender) {
		this.labelInputGender = labelInputGender;
	}

	public String getLabelInputPosition() {
		return labelInputPosition;
	}

	public void setLabelInputPosition(String labelInputPosition) {
		this.labelInputPosition = labelInputPosition;
	}

	public String getLabelInputManager() {
		return labelInputManager;
	}

	public void setLabelInputManager(String labelInputManager) {
		this.labelInputManager = labelInputManager;
	}

	public String getBtnSubmit() {
		return btnSubmit;
	}

	public void setBtnSubmit(String btnSubmit) {
		this.btnSubmit = btnSubmit;
	}

	public String getGenderMale() {
		return genderMale;
	}

	public void setGenderMale(String genderMale) {
		this.genderMale = genderMale;
	}

	public String getGenderFemale() {
		return genderFemale;
	}

	public void setGenderFemale(String genderFemale) {
		this.genderFemale = genderFemale;
	}

	public String getGenderOther() {
		return genderOther;
	}

	public void setGenderOther(String genderOther) {
		this.genderOther = genderOther;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public Date getDoB() {
		return doB;
	}

	public void setDoB(Date doB) {
		this.doB = doB;
	}

	public Date getHiredDate() {
		return hiredDate;
	}

	public void setHiredDate(Date hiredDate) {
		this.hiredDate = hiredDate;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	@PostConstruct
	public void postConstruct() {
		this.labelInputUsername = "Username";
		this.labelInputFullname = "Fullname";
		this.labelInputDoB = "Date of Birth";
		this.labelInputHiredDate = "Hired date";
		this.labelInputGender = "Gender";
		this.labelInputPosition = "Position";
		this.labelInputManager = "Manager";
		
		this.btnSubmit = "Submmit";
		
		this.genderMale = "Male";
		this.genderFemale = "Female";
		this.genderOther = "Other";
	}

	public void addUser() {
		if (checkForm()){
			username = userController.addUser(username, fullname, position,
					gender, doB, hiredDate, manager);
			Utils.addMessage("Successfull!");
			
			indexBean.setPath(PAGE_VIEW_USER);
			indexBean.setTempUser(username);
			
			reinit();
		}
	}

	public boolean checkForm() {
		if (username.equals("")){
			Utils.addMessage("Please input username field!");
			return false;
		}
		
		if (fullname.equals("")){
			Utils.addMessage("Please input fullname field!");
			return false;
		}
		
		if (doB == null){
			Utils.addMessage("Please input date of birth field!");
			return false;
		}
		
		if (hiredDate == null){
			Utils.addMessage("Please input hired date field!");
			return false;
		}
		
		if (!manager.equals("")){
			User user = userController.find(manager);
			
			if (user == null){
				Utils.addMessage("Manager not exist!");
				return false;
			}
		}
		
		return true;
	}
	
	public List<String> autocomplete(String string){
		List<String> list = userController.getList(string);
		
		return list;
	}
	
	public List<String> getUserList(){
		List<String> list = userController.getList();
		
		list.add(0, "");
		
		return list;
	}
	
	public void reinit(){
		this.username = null;
		this.fullname = null;
		this.gender = 0;
		this.doB = null;
		this.hiredDate = null;
		this.manager = null;
		this.position = null;
	}
}
