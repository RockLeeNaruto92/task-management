package hust.arrowtech.taskmanagement.Bean;

import hust.arrowtech.taskmanagement.util.Page;
import hust.arrowtech.taskmanagement.util.Topic;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.event.TabChangeEvent;

@Named
@ViewScoped
public class IndexBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2406018016768670150L;
	
	
	private String path;
	private String topic;
	
	
	public IndexBean(){
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	
	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	/* END GETTER AND SETTER */
	/**************************************/
	/**
	 * Do when constructed
	 */
	@PostConstruct
	public void postConstruct(){
		this.path = Page.DEFAULT;
		this.topic = Topic.DEFAULT;
	}
	
	public void onTabChange(TabChangeEvent event){
		String tabTitle = event.getTab().getTitle();
		
		if (tabTitle.equals("Home")){
			onHomeLinkClick();
		}else if (tabTitle.equals("Project management")){
			onProjectManagementLinkClick();
		}else if (tabTitle.equals("User management")){
			onUserManagementLinkClick();
		}else if (tabTitle.equals("Category management")){
			onSkillCategoryLinkClick();
		}else {
			onTasktypeManagementLinkClick();
		}
	}
	
	/**
	 * On home link click
	 */
	public void onHomeLinkClick(){
		this.path = Page.DEFAULT;
		this.topic = Topic.DEFAULT;
	}
	
	/**
	 * On project management link click
	 */
	public void onProjectManagementLinkClick(){
		this.path = Page.PROJECT_MANAGEMENT;
		this.topic = Topic.PROJECT_MANAGEMENT;
	}

	/**
	 * On user management link click
	 */
	public void onUserManagementLinkClick(){
		this.path = Page.USER_MANAGEMENT;
		this.topic = Topic.USER_MANAGEMENT;
	}
	
	/**
	 * On skill category link click
	 */
	public void onSkillCategoryLinkClick(){
		this.path = Page.SKILL_CATEGORY;
		this.topic = Topic.SKILL_CATEGORY;
	}
	
	/**
	 * On tasktype management link click
	 */
	public void onTasktypeManagementLinkClick(){
		this.path = Page.TASK_TYPE_MANAGEMENT;
		this.topic = Topic.TASK_TYPE_MANAGEMENT;
	}
}
